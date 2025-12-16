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
    const reservation = xmlDoc.getElementsByTagName("ns2:reservation")[0]; // Assuming ns2 based on Spring Boot default

    if (!reservation) return null;

    const getText = (tag) => {
        const el = reservation.getElementsByTagName("ns2:" + tag)[0];
        return el ? el.textContent : "";
    };

    const clientEl = reservation.getElementsByTagName("ns2:client")[0];
    const chambreEl = reservation.getElementsByTagName("ns2:chambre")[0];

    return {
        reservation: {
            id: getText("id"),
            client: {
                nom: clientEl ? clientEl.getElementsByTagName("ns2:nom")[0]?.textContent : "",
                prenom: clientEl ? clientEl.getElementsByTagName("ns2:prenom")[0]?.textContent : "",
                email: clientEl ? clientEl.getElementsByTagName("ns2:email")[0]?.textContent : "",
                telephone: clientEl ? clientEl.getElementsByTagName("ns2:telephone")[0]?.textContent : ""
            },
            chambre: {
                type: chambreEl ? chambreEl.getElementsByTagName("ns2:type")[0]?.textContent : "",
                prix: chambreEl ? chambreEl.getElementsByTagName("ns2:prix")[0]?.textContent : "",
                disponible: chambreEl ? chambreEl.getElementsByTagName("ns2:disponible")[0]?.textContent === 'true' : false
            },
            dateDebut: getText("dateDebut"),
            dateFin: getText("dateFin"),
            preferences: getText("preferences")
        }
    };
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
