const { PrismaClient } = require('@prisma/client');
const prisma = new PrismaClient();

const resolvers = {
    Query: {
        reservations: async () => {
            return prisma.reservation.findMany({
                include: { client: true, chambre: true },
            });
        },
        reservation: async (_, { id }) => {
            return prisma.reservation.findUnique({
                where: { id: parseInt(id) },
                include: { client: true, chambre: true },
            });
        },
    },
    Mutation: {
        createReservation: async (_, { client, chambre, dateDebut, dateFin, preferences }) => {
            // In a real app, check if client/chambre exists. Here we create new ones for simplicity or assume IDs if input changed.
            // The schema input implies creating new client/chambre details.

            return prisma.reservation.create({
                data: {
                    dateDebut: new Date(dateDebut),
                    dateFin: new Date(dateFin),
                    preferences,
                    client: {
                        create: client
                    },
                    chambre: {
                        create: {
                            type: chambre.type,
                            prix: chambre.prix,
                            disponible: chambre.disponible
                        }
                    }
                },
                include: { client: true, chambre: true },
            });
        },
        updateReservation: async (_, { id, dateDebut, dateFin, preferences }) => {
            const data = {};
            if (dateDebut) data.dateDebut = new Date(dateDebut);
            if (dateFin) data.dateFin = new Date(dateFin);
            if (preferences) data.preferences = preferences;

            return prisma.reservation.update({
                where: { id: parseInt(id) },
                data,
                include: { client: true, chambre: true },
            });
        },
        deleteReservation: async (_, { id }) => {
            try {
                await prisma.reservation.delete({
                    where: { id: parseInt(id) },
                });
                return true;
            } catch (e) {
                return false;
            }
        },
    },
};

module.exports = resolvers;
