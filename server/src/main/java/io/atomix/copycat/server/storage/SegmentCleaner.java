/*
 * Copyright 2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License
 */
package io.atomix.copycat.server.storage;

import io.atomix.copycat.server.storage.buffer.util.BitArray;
import io.atomix.copycat.util.Assert;

/**
 * Segment index cleaner.
 * <p>
 * The offset predicate tracks the liveness of relative offsets within a segment. Liveness is tracked
 * in a {@link BitArray} that is resized to accommodate offsets as necessary. Each bit in the array
 * represents the liveness of a relative offset in the segment. When an offset is
 * {@link #clean(long) released} from a segment, the bit at that offset is flipped in the bit array.
 * {@link #isClean(long) Testing} the predicate indicates whether an offset is still live in the segment.
 *
 * @author <a href="http://github.com/kuujo>Jordan Halterman</a>
 */
public final class SegmentCleaner implements AutoCloseable {
  private final BitArray bits;

  public SegmentCleaner() {
    this(BitArray.allocate(1024));
  }

  SegmentCleaner(BitArray bits) {
    this.bits = Assert.notNull(bits, "bits");
  }

  /**
   * Transfers the given entry to the cleaner.
   *
   * @param entry The entry to transfer.
   */
  void transfer(long offset, Indexed<?> entry) {
    if (entry.cleaner != null) {
      entry.cleaner.update(offset, this);
    }
  }

  /**
   * Returns a boolean value indicating whether an offset is live.
   *
   * @param offset The offset to check.
   * @return Indicates whether the given offset is live.
   */
  public boolean isClean(long offset) {
    return offset != -1 && (bits.size() <= offset || !bits.get(offset));
  }

  /**
   * Releases an offset from the segment.
   *
   * @param offset The offset to release.
   * @return Indicates whether the offset was newly released.
   */
  public boolean clean(long offset) {
    Assert.argNot(offset < 0, "offset must be positive");
    if (bits.size() <= offset) {
      while (bits.size() <= offset) {
        bits.resize(bits.size() * 2);
      }
    }
    return bits.set(offset);
  }

  /**
   * Returns the number of offsets released from the segment.
   *
   * @return The number of offsets released from the segment.
   */
  public long count() {
    return bits.count();
  }

  /**
   * Copies the offset predicate.
   *
   * @return The copied offset predicate.
   */
  public SegmentCleaner copy() {
    return new SegmentCleaner(bits.copy());
  }

  @Override
  public void close() {
    bits.close();
  }

}
