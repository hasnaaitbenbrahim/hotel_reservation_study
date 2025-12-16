const { gql } = require('apollo-server');

const typeDefs = gql`
  type Client {
    id: ID!
    nom: String!
    prenom: String!
    email: String!
    telephone: String!
  }

  type Chambre {
    id: ID!
    type: String!
    prix: Float!
    disponible: Boolean!
  }

  type Reservation {
    id: ID!
    client: Client!
    chambre: Chambre!
    dateDebut: String!
    dateFin: String!
    preferences: String
  }

  input ClientInput {
    nom: String!
    prenom: String!
    email: String!
    telephone: String!
  }

  input ChambreInput {
    type: String!
    prix: Float!
    disponible: Boolean!
  }

  type Query {
    reservations: [Reservation!]!
    reservation(id: ID!): Reservation
  }

  type Mutation {
    createReservation(
      client: ClientInput!
      chambre: ChambreInput!
      dateDebut: String!
      dateFin: String!
      preferences: String
    ): Reservation!

    updateReservation(
      id: ID!
      dateDebut: String
      dateFin: String
      preferences: String
    ): Reservation!

    deleteReservation(id: ID!): Boolean!
  }
`;

module.exports = typeDefs;
