import React, { useState, useEffect } from 'react';
import { restService } from './services/restService';
import { soapService } from './services/soapService';
import { useQuery, useMutation, gql } from '@apollo/client';

const GET_RESERVATIONS = gql`
  query GetReservations {
    reservations {
      id
      client { nom prenom }
      chambre { type }
      dateDebut
      dateFin
    }
  }
`;

function App() {
    const [protocol, setProtocol] = useState('REST');
    const [data, setData] = useState([]);
    const [loading, setLoading] = useState(false);

    // GraphQL Hooks
    const { loading: gqlLoading, error: gqlError, data: gqlData, refetch } = useQuery(GET_RESERVATIONS, {
        skip: protocol !== 'GraphQL'
    });

    useEffect(() => {
        if (protocol === 'REST') {
            loadRestData();
        } else if (protocol === 'GraphQL' && gqlData) {
            setData(gqlData.reservations);
        }
    }, [protocol, gqlData]);

    const loadRestData = async () => {
        setLoading(true);
        try {
            const res = await restService.getAll();
            setData(res);
        } catch (e) {
            console.error(e);
        } finally {
            setLoading(false);
        }
    };

    const handleCreate = async () => {
        const newRes = {
            client: { nom: "Doe", prenom: "John", email: "john@example.com", telephone: "1234567890" },
            chambre: { type: "Double", prix: 100.0, disponible: true },
            dateDebut: "2023-10-01",
            dateFin: "2023-10-05",
            preferences: "Quiet room"
        };

        if (protocol === 'REST') {
            await restService.create(newRes);
            loadRestData();
        } else if (protocol === 'SOAP') {
            await soapService.create(newRes);
            alert('SOAP Request Sent (Check Network Tab)');
        } else if (protocol === 'GraphQL') {
            alert('GraphQL Mutation not implemented in UI demo, use Playground');
        }
    };

    return (
        <div className="min-h-screen bg-gray-100 p-8">
            <header className="mb-8">
                <h1 className="text-3xl font-bold text-gray-800">Hotel Reservation API Comparison</h1>
            </header>

            <div className="bg-white rounded-lg shadow p-6 mb-6">
                <div className="flex space-x-4 mb-6">
                    {['REST', 'SOAP', 'GraphQL', 'gRPC'].map(p => (
                        <button
                            key={p}
                            onClick={() => setProtocol(p)}
                            className={`px-4 py-2 rounded ${protocol === p ? 'bg-blue-600 text-white' : 'bg-gray-200 text-gray-700'}`}
                        >
                            {p}
                        </button>
                    ))}
                </div>

                <div className="mb-4">
                    <button onClick={handleCreate} className="bg-green-500 text-white px-4 py-2 rounded hover:bg-green-600">
                        Create Test Reservation
                    </button>
                </div>

                {loading || (protocol === 'GraphQL' && gqlLoading) ? (
                    <p>Loading...</p>
                ) : (
                    <div className="overflow-x-auto">
                        <table className="min-w-full table-auto">
                            <thead>
                                <tr className="bg-gray-50">
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">ID</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Client</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Chambre</th>
                                    <th className="px-6 py-3 text-left text-xs font-medium text-gray-500 uppercase tracking-wider">Dates</th>
                                </tr>
                            </thead>
                            <tbody className="bg-white divide-y divide-gray-200">
                                {data.map((res) => (
                                    <tr key={res.id}>
                                        <td className="px-6 py-4 whitespace-nowrap">{res.id}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{res.client?.nom} {res.client?.prenom}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{res.chambre?.type}</td>
                                        <td className="px-6 py-4 whitespace-nowrap">{res.dateDebut} - {res.dateFin}</td>
                                    </tr>
                                ))}
                            </tbody>
                        </table>
                        {data.length === 0 && <p className="text-center py-4 text-gray-500">No data found or protocol not fully implemented in UI demo.</p>}
                    </div>
                )}
            </div>
        </div>
    );
}

export default App;
