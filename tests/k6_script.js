import http from 'k6/http';
import { check, sleep } from 'k6';

export const options = {
    stages: [
        { duration: '30s', target: 10 }, // Ramp to 10 users
        { duration: '1m', target: 100 }, // Ramp to 100 users
        { duration: '30s', target: 0 },  // Scale down
    ],
};

const BASE_URL = 'http://localhost:8080'; // Spring Boot
const GRAPHQL_URL = 'http://localhost:4000'; // Apollo

export default function () {
    // 1. Test REST API
    const restRes = http.get(`${BASE_URL}/api/reservations`);
    check(restRes, { 'REST status was 200': (r) => r.status == 200 });

    // 2. Test SOAP API
    const soapBody = `
    <soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:soap="http://example.com/hotel/soap">
       <soapenv:Header/>
       <soapenv:Body>
          <soap:getReservationRequest>
             <soap:id>1</soap:id>
          </soap:getReservationRequest>
       </soapenv:Body>
    </soapenv:Envelope>`;

    const soapRes = http.post(`${BASE_URL}/ws`, soapBody, {
        headers: { 'Content-Type': 'text/xml' },
    });
    check(soapRes, { 'SOAP status was 200': (r) => r.status == 200 });

    // 3. Test GraphQL API
    const query = `
    query {
      reservations {
        id
        client { nom }
      }
    }
  `;
    const gqlRes = http.post(GRAPHQL_URL, JSON.stringify({ query }), {
        headers: { 'Content-Type': 'application/json' },
    });
    check(gqlRes, { 'GraphQL status was 200': (r) => r.status == 200 });

    sleep(1);
}
