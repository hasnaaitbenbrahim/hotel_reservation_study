package com.example.hotel.soap;

import com.example.hotel.model.Chambre;
import com.example.hotel.model.Client;
import com.example.hotel.model.Reservation;
import com.example.hotel.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.math.BigDecimal;
import java.time.LocalDate;

@Endpoint
public class ReservationSoapEndpoint {

    private static final String NAMESPACE_URI = "http://example.com/hotel/soap";

    @Autowired
    private ReservationService reservationService;

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "getReservationRequest")
    @ResponsePayload
    public GetReservationResponse getReservation(@RequestPayload GetReservationRequest request) {
        GetReservationResponse response = new GetReservationResponse();
        Reservation reservation = reservationService.getReservationById(request.getId())
                .orElseThrow(() -> new RuntimeException("Reservation not found"));

        response.setReservation(mapToReservationInfo(reservation));
        return response;
    }

    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "createReservationRequest")
    @ResponsePayload
    public CreateReservationResponse createReservation(@RequestPayload CreateReservationRequest request) {
        CreateReservationResponse response = new CreateReservationResponse();

        Client client = new Client(
                request.getClient().getNom(),
                request.getClient().getPrenom(),
                request.getClient().getEmail(),
                request.getClient().getTelephone());

        Chambre chambre = new Chambre(
                request.getChambre().getType(),
                request.getChambre().getPrix(),
                request.getChambre().isDisponible());

        Reservation reservation = new Reservation(
                client,
                chambre,
                LocalDate.parse(request.getDateDebut()),
                LocalDate.parse(request.getDateFin()),
                request.getPreferences());

        // Note: In a real app, we would look up existing clients/chambres or cascade
        // persist
        // For simplicity, we assume cascade persist or new entities
        Reservation saved = reservationService.createReservation(reservation);

        response.setReservation(mapToReservationInfo(saved));
        return response;
    }

    private ReservationInfo mapToReservationInfo(Reservation reservation) {
        ReservationInfo info = new ReservationInfo();
        info.setId(reservation.getId());
        info.setDateDebut(reservation.getDateDebut().toString());
        info.setDateFin(reservation.getDateFin().toString());
        info.setPreferences(reservation.getPreferences());

        ClientInfo clientInfo = new ClientInfo();
        clientInfo.setId(reservation.getClient().getId());
        clientInfo.setNom(reservation.getClient().getNom());
        clientInfo.setPrenom(reservation.getClient().getPrenom());
        clientInfo.setEmail(reservation.getClient().getEmail());
        clientInfo.setTelephone(reservation.getClient().getTelephone());
        info.setClient(clientInfo);

        ChambreInfo chambreInfo = new ChambreInfo();
        chambreInfo.setId(reservation.getChambre().getId());
        chambreInfo.setType(reservation.getChambre().getType());
        chambreInfo.setPrix(reservation.getChambre().getPrix());
        chambreInfo.setDisponible(reservation.getChambre().isDisponible());
        info.setChambre(chambreInfo);

        return info;
    }
}
