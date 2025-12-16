package com.example.hotel.grpc;

import io.grpc.Server;
import io.grpc.ServerBuilder;
import java.io.IOException;

public class GrpcServer {
    public static void main(String[] args) throws IOException, InterruptedException {
        int port = 9091;
        Server server = ServerBuilder.forPort(port)
                .addService(new ReservationGrpcService())
                .build()
                .start();

        System.out.println("Server started, listening on " + port);
        server.awaitTermination();
    }
}
