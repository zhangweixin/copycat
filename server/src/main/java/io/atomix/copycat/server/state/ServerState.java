/*
 * Copyright 2016 the original author or authors.
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
package io.atomix.copycat.server.state;

import io.atomix.copycat.protocol.ProtocolServerConnection;
import io.atomix.copycat.protocol.request.*;
import io.atomix.copycat.protocol.response.*;
import io.atomix.copycat.server.CopycatServer;
import io.atomix.copycat.server.protocol.request.*;
import io.atomix.copycat.server.protocol.response.*;
import io.atomix.copycat.util.Managed;

import java.util.concurrent.CompletableFuture;

/**
 * Server state interface.
 *
 * @author <a href="http://github.com/kuujo>Jordan Halterman</a>
 */
public interface ServerState extends Managed<ServerState> {

  /**
   * Returns the server state type.
   *
   * @return The server state type.
   */
  CopycatServer.State type();

  /**
   * Handles a register request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<RegisterResponse> onRegister(RegisterRequest request, RegisterResponse.Builder builder);

  /**
   * Handles a connect request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<ConnectResponse> onConnect(ConnectRequest request, ConnectResponse.Builder builder, ProtocolServerConnection connection);

  /**
   * Handles an accept request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<AcceptResponse> onAccept(AcceptRequest request, AcceptResponse.Builder builder);

  /**
   * Handles a keep alive request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<KeepAliveResponse> onKeepAlive(KeepAliveRequest request, KeepAliveResponse.Builder builder);

  /**
   * Handles an unregister request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<UnregisterResponse> onUnregister(UnregisterRequest request, UnregisterResponse.Builder builder);

  /**
   * Handles a publish request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<PublishResponse> onPublish(PublishRequest request, PublishResponse.Builder builder);

  /**
   * Handles a configure request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<ConfigureResponse> onConfigure(ConfigureRequest request, ConfigureResponse.Builder builder);

  /**
   * Handles an install request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<InstallResponse> onInstall(InstallRequest request, InstallResponse.Builder builder);

  /**
   * Handles a join request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<JoinResponse> onJoin(JoinRequest request, JoinResponse.Builder builder);

  /**
   * Handles a configure request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<ReconfigureResponse> onReconfigure(ReconfigureRequest request, ReconfigureResponse.Builder builder);

  /**
   * Handles a leave request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<LeaveResponse> onLeave(LeaveRequest request, LeaveResponse.Builder builder);

  /**
   * Handles an append request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<AppendResponse> onAppend(AppendRequest request, AppendResponse.Builder builder);

  /**
   * Handles a poll request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<PollResponse> onPoll(PollRequest request, PollResponse.Builder builder);

  /**
   * Handles a vote request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<VoteResponse> onVote(VoteRequest request, VoteResponse.Builder builder);

  /**
   * Handles a command request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<CommandResponse> onCommand(CommandRequest request, CommandResponse.Builder builder);

  /**
   * Handles a query request.
   *
   * @param request The request to handle.
   * @param builder The response builder.
   * @return A completable future to be completed with the request response.
   */
  CompletableFuture<QueryResponse> onQuery(QueryRequest request, QueryResponse.Builder builder);

}
