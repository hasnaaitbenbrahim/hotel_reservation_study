import { GrpcWebFetchTransport } from '@protobuf-ts/grpcweb-transport';
import { ReservationServiceClient } from '../generated/reservation.client';
import { CreateReservationRequest } from '../generated/reservation';

// Create a transport for gRPC-Web
const transport = new GrpcWebFetchTransport({
    baseUrl: 'http://localhost:8080' // Envoy Proxy
});

const client = new ReservationServiceClient(transport);

export const grpcService = {
    getAll: async () => {
        // The proto only has GetReservation(id), so we can't get all.
        // We'll return an empty list for now.
        return [];
    },
    create: async (reservation) => {
        const request = CreateReservationRequest.create({
            client: {
                nom: reservation.client.nom,
                prenom: reservation.client.prenom,
                email: reservation.client.email,
                telephone: reservation.client.telephone
            },
            chambre: {
                type: reservation.chambre.type,
                prix: reservation.chambre.prix,
                disponible: reservation.chambre.disponible
            },
            dateDebut: reservation.dateDebut,
            dateFin: reservation.dateFin,
            preferences: reservation.preferences
        });

        const { response } = await client.createReservation(request);
        return response;
    }
};
