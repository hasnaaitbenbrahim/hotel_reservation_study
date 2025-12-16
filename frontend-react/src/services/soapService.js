const SOAP_URL = '/ws';

const createXml = (method, body) => `
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://example.com/hotel/soap">
   <soapenv:Header/>
   <soapenv:Body>
      <soap:${method}Request>
         ${body}
      </soap:${method}Request>
   </soapenv:Body>
</soapenv:Envelope>
`;

const parseXml = (xml) => {
    const parser = new DOMParser();
    const xmlDoc = parser.parseFromString(xml, "text/xml");
    // Simplified parsing logic for demo
    return xmlDoc;
};

export const soapService = {
    get: async (id) => {
        const body = `<soap:id>${id}</soap:id>`;
        const response = await fetch(SOAP_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'text/xml' },
            body: createXml('getReservation', body)
        });
        const text = await response.text();
        return parseXml(text);
    },
    create: async (reservation) => {
        const body = `
        <soap:client>
            <soap:nom>${reservation.client.nom}</soap:nom>
            <soap:prenom>${reservation.client.prenom}</soap:prenom>
            <soap:email>${reservation.client.email}</soap:email>
            <soap:telephone>${reservation.client.telephone}</soap:telephone>
        </soap:client>
        <soap:chambre>
            <soap:type>${reservation.chambre.type}</soap:type>
            <soap:prix>${reservation.chambre.prix}</soap:prix>
            <soap:disponible>${reservation.chambre.disponible}</soap:disponible>
        </soap:chambre>
        <soap:dateDebut>${reservation.dateDebut}</soap:dateDebut>
        <soap:dateFin>${reservation.dateFin}</soap:dateFin>
        <soap:preferences>${reservation.preferences}</soap:preferences>
    `;
        const response = await fetch(SOAP_URL, {
            method: 'POST',
            headers: { 'Content-Type': 'text/xml' },
            body: createXml('createReservation', body)
        });
        return await response.text();
    }
};
