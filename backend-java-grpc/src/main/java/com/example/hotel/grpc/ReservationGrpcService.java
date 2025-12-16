package com.example.hotel.grpc;

import io.grpc.stub.StreamObserver;
import java.sql.*;
import java.time.LocalDate;

public class ReservationGrpcService extends ReservationServiceGrpc.ReservationServiceImplBase {

    private static final String DB_URL = "jdbc:postgresql://localhost:5432/hotel_db";
    private static final String USER = "postgres";
    private static final String PASS = "password";

    @Override
    public void createReservation(CreateReservationRequest request,
            StreamObserver<ReservationResponse> responseObserver) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Simple implementation: Insert Client, Chambre, Reservation
            // Note: In real world, we'd handle transactions and existing entities

            // 1. Insert Client
            String insertClient = "INSERT INTO client (nom, prenom, email, telephone) VALUES (?, ?, ?, ?) RETURNING id";
            PreparedStatement psClient = conn.prepareStatement(insertClient);
            psClient.setString(1, request.getClient().getNom());
            psClient.setString(2, request.getClient().getPrenom());
            psClient.setString(3, request.getClient().getEmail());
            psClient.setString(4, request.getClient().getTelephone());
            ResultSet rsClient = psClient.executeQuery();
            rsClient.next();
            long clientId = rsClient.getLong(1);

            // 2. Insert Chambre
            String insertChambre = "INSERT INTO chambre (type, prix, disponible) VALUES (?, ?, ?) RETURNING id";
            PreparedStatement psChambre = conn.prepareStatement(insertChambre);
            psChambre.setString(1, request.getChambre().getType());
            psChambre.setDouble(2, request.getChambre().getPrix());
            psChambre.setBoolean(3, request.getChambre().getDisponible());
            ResultSet rsChambre = psChambre.executeQuery();
            rsChambre.next();
            long chambreId = rsChambre.getLong(1);

            // 3. Insert Reservation
            String insertRes = "INSERT INTO reservation (client_id, chambre_id, date_debut, date_fin, preferences) VALUES (?, ?, ?, ?, ?) RETURNING id";
            PreparedStatement psRes = conn.prepareStatement(insertRes);
            psRes.setLong(1, clientId);
            psRes.setLong(2, chambreId);
            psRes.setDate(3, Date.valueOf(request.getDateDebut()));
            psRes.setDate(4, Date.valueOf(request.getDateFin()));
            psRes.setString(5, request.getPreferences());
            ResultSet rsRes = psRes.executeQuery();
            rsRes.next();
            long resId = rsRes.getLong(1);

            // Construct Response
            Reservation reservation = Reservation.newBuilder()
                    .setId(resId)
                    .setClient(request.getClient().toBuilder().setId(clientId).build())
                    .setChambre(request.getChambre().toBuilder().setId(chambreId).build())
                    .setDateDebut(request.getDateDebut())
                    .setDateFin(request.getDateFin())
                    .setPreferences(request.getPreferences())
                    .build();

            responseObserver.onNext(ReservationResponse.newBuilder().setReservation(reservation).build());
            responseObserver.onCompleted();

        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    @Override
    public void getReservation(GetReservationRequest request, StreamObserver<ReservationResponse> responseObserver) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "SELECT r.id, r.date_debut, r.date_fin, r.preferences, " +
                    "c.id as c_id, c.nom, c.prenom, c.email, c.telephone, " +
                    "ch.id as ch_id, ch.type, ch.prix, ch.disponible " +
                    "FROM reservation r " +
                    "JOIN client c ON r.client_id = c.id " +
                    "JOIN chambre ch ON r.chambre_id = ch.id " +
                    "WHERE r.id = ?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, request.getId());
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                Client client = Client.newBuilder()
                        .setId(rs.getLong("c_id"))
                        .setNom(rs.getString("nom"))
                        .setPrenom(rs.getString("prenom"))
                        .setEmail(rs.getString("email"))
                        .setTelephone(rs.getString("telephone"))
                        .build();

                Chambre chambre = Chambre.newBuilder()
                        .setId(rs.getLong("ch_id"))
                        .setType(rs.getString("type"))
                        .setPrix(rs.getDouble("prix"))
                        .setDisponible(rs.getBoolean("disponible"))
                        .build();

                Reservation reservation = Reservation.newBuilder()
                        .setId(rs.getLong("id"))
                        .setClient(client)
                        .setChambre(chambre)
                        .setDateDebut(rs.getDate("date_debut").toString())
                        .setDateFin(rs.getDate("date_fin").toString())
                        .setPreferences(rs.getString("preferences"))
                        .build();

                responseObserver.onNext(ReservationResponse.newBuilder().setReservation(reservation).build());
                responseObserver.onCompleted();
            } else {
                responseObserver.onError(new RuntimeException("Reservation not found"));
            }

        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }

    // Implement Update and Delete similarly...
    @Override
    public void deleteReservation(DeleteReservationRequest request,
            StreamObserver<DeleteReservationResponse> responseObserver) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            String sql = "DELETE FROM reservation WHERE id = ?";
            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setLong(1, request.getId());
            int rows = ps.executeUpdate();

            responseObserver.onNext(DeleteReservationResponse.newBuilder().setSuccess(rows > 0).build());
            responseObserver.onCompleted();
        } catch (SQLException e) {
            responseObserver.onError(e);
        }
    }
}
