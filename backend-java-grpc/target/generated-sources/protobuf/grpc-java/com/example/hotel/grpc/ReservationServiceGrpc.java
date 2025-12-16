package com.example.hotel.grpc;

import static io.grpc.MethodDescriptor.generateFullMethodName;

/**
 */
@javax.annotation.Generated(
    value = "by gRPC proto compiler (version 1.54.0)",
    comments = "Source: reservation.proto")
@io.grpc.stub.annotations.GrpcGenerated
public final class ReservationServiceGrpc {

  private ReservationServiceGrpc() {}

  public static final String SERVICE_NAME = "hotel.ReservationService";

  // Static method descriptors that strictly reflect the proto.
  private static volatile io.grpc.MethodDescriptor<com.example.hotel.grpc.CreateReservationRequest,
      com.example.hotel.grpc.ReservationResponse> getCreateReservationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "CreateReservation",
      requestType = com.example.hotel.grpc.CreateReservationRequest.class,
      responseType = com.example.hotel.grpc.ReservationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.hotel.grpc.CreateReservationRequest,
      com.example.hotel.grpc.ReservationResponse> getCreateReservationMethod() {
    io.grpc.MethodDescriptor<com.example.hotel.grpc.CreateReservationRequest, com.example.hotel.grpc.ReservationResponse> getCreateReservationMethod;
    if ((getCreateReservationMethod = ReservationServiceGrpc.getCreateReservationMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getCreateReservationMethod = ReservationServiceGrpc.getCreateReservationMethod) == null) {
          ReservationServiceGrpc.getCreateReservationMethod = getCreateReservationMethod =
              io.grpc.MethodDescriptor.<com.example.hotel.grpc.CreateReservationRequest, com.example.hotel.grpc.ReservationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "CreateReservation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.CreateReservationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.ReservationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("CreateReservation"))
              .build();
        }
      }
    }
    return getCreateReservationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.hotel.grpc.GetReservationRequest,
      com.example.hotel.grpc.ReservationResponse> getGetReservationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "GetReservation",
      requestType = com.example.hotel.grpc.GetReservationRequest.class,
      responseType = com.example.hotel.grpc.ReservationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.hotel.grpc.GetReservationRequest,
      com.example.hotel.grpc.ReservationResponse> getGetReservationMethod() {
    io.grpc.MethodDescriptor<com.example.hotel.grpc.GetReservationRequest, com.example.hotel.grpc.ReservationResponse> getGetReservationMethod;
    if ((getGetReservationMethod = ReservationServiceGrpc.getGetReservationMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getGetReservationMethod = ReservationServiceGrpc.getGetReservationMethod) == null) {
          ReservationServiceGrpc.getGetReservationMethod = getGetReservationMethod =
              io.grpc.MethodDescriptor.<com.example.hotel.grpc.GetReservationRequest, com.example.hotel.grpc.ReservationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "GetReservation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.GetReservationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.ReservationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("GetReservation"))
              .build();
        }
      }
    }
    return getGetReservationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.hotel.grpc.UpdateReservationRequest,
      com.example.hotel.grpc.ReservationResponse> getUpdateReservationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "UpdateReservation",
      requestType = com.example.hotel.grpc.UpdateReservationRequest.class,
      responseType = com.example.hotel.grpc.ReservationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.hotel.grpc.UpdateReservationRequest,
      com.example.hotel.grpc.ReservationResponse> getUpdateReservationMethod() {
    io.grpc.MethodDescriptor<com.example.hotel.grpc.UpdateReservationRequest, com.example.hotel.grpc.ReservationResponse> getUpdateReservationMethod;
    if ((getUpdateReservationMethod = ReservationServiceGrpc.getUpdateReservationMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getUpdateReservationMethod = ReservationServiceGrpc.getUpdateReservationMethod) == null) {
          ReservationServiceGrpc.getUpdateReservationMethod = getUpdateReservationMethod =
              io.grpc.MethodDescriptor.<com.example.hotel.grpc.UpdateReservationRequest, com.example.hotel.grpc.ReservationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "UpdateReservation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.UpdateReservationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.ReservationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("UpdateReservation"))
              .build();
        }
      }
    }
    return getUpdateReservationMethod;
  }

  private static volatile io.grpc.MethodDescriptor<com.example.hotel.grpc.DeleteReservationRequest,
      com.example.hotel.grpc.DeleteReservationResponse> getDeleteReservationMethod;

  @io.grpc.stub.annotations.RpcMethod(
      fullMethodName = SERVICE_NAME + '/' + "DeleteReservation",
      requestType = com.example.hotel.grpc.DeleteReservationRequest.class,
      responseType = com.example.hotel.grpc.DeleteReservationResponse.class,
      methodType = io.grpc.MethodDescriptor.MethodType.UNARY)
  public static io.grpc.MethodDescriptor<com.example.hotel.grpc.DeleteReservationRequest,
      com.example.hotel.grpc.DeleteReservationResponse> getDeleteReservationMethod() {
    io.grpc.MethodDescriptor<com.example.hotel.grpc.DeleteReservationRequest, com.example.hotel.grpc.DeleteReservationResponse> getDeleteReservationMethod;
    if ((getDeleteReservationMethod = ReservationServiceGrpc.getDeleteReservationMethod) == null) {
      synchronized (ReservationServiceGrpc.class) {
        if ((getDeleteReservationMethod = ReservationServiceGrpc.getDeleteReservationMethod) == null) {
          ReservationServiceGrpc.getDeleteReservationMethod = getDeleteReservationMethod =
              io.grpc.MethodDescriptor.<com.example.hotel.grpc.DeleteReservationRequest, com.example.hotel.grpc.DeleteReservationResponse>newBuilder()
              .setType(io.grpc.MethodDescriptor.MethodType.UNARY)
              .setFullMethodName(generateFullMethodName(SERVICE_NAME, "DeleteReservation"))
              .setSampledToLocalTracing(true)
              .setRequestMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.DeleteReservationRequest.getDefaultInstance()))
              .setResponseMarshaller(io.grpc.protobuf.ProtoUtils.marshaller(
                  com.example.hotel.grpc.DeleteReservationResponse.getDefaultInstance()))
              .setSchemaDescriptor(new ReservationServiceMethodDescriptorSupplier("DeleteReservation"))
              .build();
        }
      }
    }
    return getDeleteReservationMethod;
  }

  /**
   * Creates a new async stub that supports all call types for the service
   */
  public static ReservationServiceStub newStub(io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReservationServiceStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReservationServiceStub>() {
        @java.lang.Override
        public ReservationServiceStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReservationServiceStub(channel, callOptions);
        }
      };
    return ReservationServiceStub.newStub(factory, channel);
  }

  /**
   * Creates a new blocking-style stub that supports unary and streaming output calls on the service
   */
  public static ReservationServiceBlockingStub newBlockingStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReservationServiceBlockingStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReservationServiceBlockingStub>() {
        @java.lang.Override
        public ReservationServiceBlockingStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReservationServiceBlockingStub(channel, callOptions);
        }
      };
    return ReservationServiceBlockingStub.newStub(factory, channel);
  }

  /**
   * Creates a new ListenableFuture-style stub that supports unary calls on the service
   */
  public static ReservationServiceFutureStub newFutureStub(
      io.grpc.Channel channel) {
    io.grpc.stub.AbstractStub.StubFactory<ReservationServiceFutureStub> factory =
      new io.grpc.stub.AbstractStub.StubFactory<ReservationServiceFutureStub>() {
        @java.lang.Override
        public ReservationServiceFutureStub newStub(io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
          return new ReservationServiceFutureStub(channel, callOptions);
        }
      };
    return ReservationServiceFutureStub.newStub(factory, channel);
  }

  /**
   */
  public interface AsyncService {

    /**
     */
    default void createReservation(com.example.hotel.grpc.CreateReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getCreateReservationMethod(), responseObserver);
    }

    /**
     */
    default void getReservation(com.example.hotel.grpc.GetReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getGetReservationMethod(), responseObserver);
    }

    /**
     */
    default void updateReservation(com.example.hotel.grpc.UpdateReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getUpdateReservationMethod(), responseObserver);
    }

    /**
     */
    default void deleteReservation(com.example.hotel.grpc.DeleteReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.DeleteReservationResponse> responseObserver) {
      io.grpc.stub.ServerCalls.asyncUnimplementedUnaryCall(getDeleteReservationMethod(), responseObserver);
    }
  }

  /**
   * Base class for the server implementation of the service ReservationService.
   */
  public static abstract class ReservationServiceImplBase
      implements io.grpc.BindableService, AsyncService {

    @java.lang.Override public final io.grpc.ServerServiceDefinition bindService() {
      return ReservationServiceGrpc.bindService(this);
    }
  }

  /**
   * A stub to allow clients to do asynchronous rpc calls to service ReservationService.
   */
  public static final class ReservationServiceStub
      extends io.grpc.stub.AbstractAsyncStub<ReservationServiceStub> {
    private ReservationServiceStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReservationServiceStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReservationServiceStub(channel, callOptions);
    }

    /**
     */
    public void createReservation(com.example.hotel.grpc.CreateReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getCreateReservationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void getReservation(com.example.hotel.grpc.GetReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getGetReservationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void updateReservation(com.example.hotel.grpc.UpdateReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getUpdateReservationMethod(), getCallOptions()), request, responseObserver);
    }

    /**
     */
    public void deleteReservation(com.example.hotel.grpc.DeleteReservationRequest request,
        io.grpc.stub.StreamObserver<com.example.hotel.grpc.DeleteReservationResponse> responseObserver) {
      io.grpc.stub.ClientCalls.asyncUnaryCall(
          getChannel().newCall(getDeleteReservationMethod(), getCallOptions()), request, responseObserver);
    }
  }

  /**
   * A stub to allow clients to do synchronous rpc calls to service ReservationService.
   */
  public static final class ReservationServiceBlockingStub
      extends io.grpc.stub.AbstractBlockingStub<ReservationServiceBlockingStub> {
    private ReservationServiceBlockingStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReservationServiceBlockingStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReservationServiceBlockingStub(channel, callOptions);
    }

    /**
     */
    public com.example.hotel.grpc.ReservationResponse createReservation(com.example.hotel.grpc.CreateReservationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getCreateReservationMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.hotel.grpc.ReservationResponse getReservation(com.example.hotel.grpc.GetReservationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getGetReservationMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.hotel.grpc.ReservationResponse updateReservation(com.example.hotel.grpc.UpdateReservationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getUpdateReservationMethod(), getCallOptions(), request);
    }

    /**
     */
    public com.example.hotel.grpc.DeleteReservationResponse deleteReservation(com.example.hotel.grpc.DeleteReservationRequest request) {
      return io.grpc.stub.ClientCalls.blockingUnaryCall(
          getChannel(), getDeleteReservationMethod(), getCallOptions(), request);
    }
  }

  /**
   * A stub to allow clients to do ListenableFuture-style rpc calls to service ReservationService.
   */
  public static final class ReservationServiceFutureStub
      extends io.grpc.stub.AbstractFutureStub<ReservationServiceFutureStub> {
    private ReservationServiceFutureStub(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      super(channel, callOptions);
    }

    @java.lang.Override
    protected ReservationServiceFutureStub build(
        io.grpc.Channel channel, io.grpc.CallOptions callOptions) {
      return new ReservationServiceFutureStub(channel, callOptions);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.hotel.grpc.ReservationResponse> createReservation(
        com.example.hotel.grpc.CreateReservationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getCreateReservationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.hotel.grpc.ReservationResponse> getReservation(
        com.example.hotel.grpc.GetReservationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getGetReservationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.hotel.grpc.ReservationResponse> updateReservation(
        com.example.hotel.grpc.UpdateReservationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getUpdateReservationMethod(), getCallOptions()), request);
    }

    /**
     */
    public com.google.common.util.concurrent.ListenableFuture<com.example.hotel.grpc.DeleteReservationResponse> deleteReservation(
        com.example.hotel.grpc.DeleteReservationRequest request) {
      return io.grpc.stub.ClientCalls.futureUnaryCall(
          getChannel().newCall(getDeleteReservationMethod(), getCallOptions()), request);
    }
  }

  private static final int METHODID_CREATE_RESERVATION = 0;
  private static final int METHODID_GET_RESERVATION = 1;
  private static final int METHODID_UPDATE_RESERVATION = 2;
  private static final int METHODID_DELETE_RESERVATION = 3;

  private static final class MethodHandlers<Req, Resp> implements
      io.grpc.stub.ServerCalls.UnaryMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ServerStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.ClientStreamingMethod<Req, Resp>,
      io.grpc.stub.ServerCalls.BidiStreamingMethod<Req, Resp> {
    private final AsyncService serviceImpl;
    private final int methodId;

    MethodHandlers(AsyncService serviceImpl, int methodId) {
      this.serviceImpl = serviceImpl;
      this.methodId = methodId;
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public void invoke(Req request, io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        case METHODID_CREATE_RESERVATION:
          serviceImpl.createReservation((com.example.hotel.grpc.CreateReservationRequest) request,
              (io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse>) responseObserver);
          break;
        case METHODID_GET_RESERVATION:
          serviceImpl.getReservation((com.example.hotel.grpc.GetReservationRequest) request,
              (io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse>) responseObserver);
          break;
        case METHODID_UPDATE_RESERVATION:
          serviceImpl.updateReservation((com.example.hotel.grpc.UpdateReservationRequest) request,
              (io.grpc.stub.StreamObserver<com.example.hotel.grpc.ReservationResponse>) responseObserver);
          break;
        case METHODID_DELETE_RESERVATION:
          serviceImpl.deleteReservation((com.example.hotel.grpc.DeleteReservationRequest) request,
              (io.grpc.stub.StreamObserver<com.example.hotel.grpc.DeleteReservationResponse>) responseObserver);
          break;
        default:
          throw new AssertionError();
      }
    }

    @java.lang.Override
    @java.lang.SuppressWarnings("unchecked")
    public io.grpc.stub.StreamObserver<Req> invoke(
        io.grpc.stub.StreamObserver<Resp> responseObserver) {
      switch (methodId) {
        default:
          throw new AssertionError();
      }
    }
  }

  public static final io.grpc.ServerServiceDefinition bindService(AsyncService service) {
    return io.grpc.ServerServiceDefinition.builder(getServiceDescriptor())
        .addMethod(
          getCreateReservationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.hotel.grpc.CreateReservationRequest,
              com.example.hotel.grpc.ReservationResponse>(
                service, METHODID_CREATE_RESERVATION)))
        .addMethod(
          getGetReservationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.hotel.grpc.GetReservationRequest,
              com.example.hotel.grpc.ReservationResponse>(
                service, METHODID_GET_RESERVATION)))
        .addMethod(
          getUpdateReservationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.hotel.grpc.UpdateReservationRequest,
              com.example.hotel.grpc.ReservationResponse>(
                service, METHODID_UPDATE_RESERVATION)))
        .addMethod(
          getDeleteReservationMethod(),
          io.grpc.stub.ServerCalls.asyncUnaryCall(
            new MethodHandlers<
              com.example.hotel.grpc.DeleteReservationRequest,
              com.example.hotel.grpc.DeleteReservationResponse>(
                service, METHODID_DELETE_RESERVATION)))
        .build();
  }

  private static abstract class ReservationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoFileDescriptorSupplier, io.grpc.protobuf.ProtoServiceDescriptorSupplier {
    ReservationServiceBaseDescriptorSupplier() {}

    @java.lang.Override
    public com.google.protobuf.Descriptors.FileDescriptor getFileDescriptor() {
      return com.example.hotel.grpc.ReservationProto.getDescriptor();
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.ServiceDescriptor getServiceDescriptor() {
      return getFileDescriptor().findServiceByName("ReservationService");
    }
  }

  private static final class ReservationServiceFileDescriptorSupplier
      extends ReservationServiceBaseDescriptorSupplier {
    ReservationServiceFileDescriptorSupplier() {}
  }

  private static final class ReservationServiceMethodDescriptorSupplier
      extends ReservationServiceBaseDescriptorSupplier
      implements io.grpc.protobuf.ProtoMethodDescriptorSupplier {
    private final String methodName;

    ReservationServiceMethodDescriptorSupplier(String methodName) {
      this.methodName = methodName;
    }

    @java.lang.Override
    public com.google.protobuf.Descriptors.MethodDescriptor getMethodDescriptor() {
      return getServiceDescriptor().findMethodByName(methodName);
    }
  }

  private static volatile io.grpc.ServiceDescriptor serviceDescriptor;

  public static io.grpc.ServiceDescriptor getServiceDescriptor() {
    io.grpc.ServiceDescriptor result = serviceDescriptor;
    if (result == null) {
      synchronized (ReservationServiceGrpc.class) {
        result = serviceDescriptor;
        if (result == null) {
          serviceDescriptor = result = io.grpc.ServiceDescriptor.newBuilder(SERVICE_NAME)
              .setSchemaDescriptor(new ReservationServiceFileDescriptorSupplier())
              .addMethod(getCreateReservationMethod())
              .addMethod(getGetReservationMethod())
              .addMethod(getUpdateReservationMethod())
              .addMethod(getDeleteReservationMethod())
              .build();
        }
      }
    }
    return result;
  }
}
