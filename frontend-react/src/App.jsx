import React, { useState, useEffect } from 'react';
import { restService } from './services/restService';
import { soapService } from './services/soapService';
import { grpcService } from './services/grpcService';
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

const CREATE_RESERVATION = gql`
  mutation CreateReservation($client: ClientInput!, $chambre: ChambreInput!, $dateDebut: String!, $dateFin: String!, $preferences: String) {
    createReservation(client: $client, chambre: $chambre, dateDebut: $dateDebut, dateFin: $dateFin, preferences: $preferences) {
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
    const [createReservation] = useMutation(CREATE_RESERVATION);

    useEffect(() => {
        setData([]);
        if (protocol === 'REST') {
            loadRestData();
        } else if (protocol === 'SOAP') {
            loadSoapData();
        } else if (protocol === 'GraphQL' && gqlData) {
            setData(gqlData.reservations);
        } else if (protocol === 'gRPC') {
            loadGrpcData();
        }
    }, [protocol, gqlData]);

    const loadSoapData = async () => {
        setLoading(true);
        try {
            // Fetch reservation ID 1 for demo purposes as SOAP service is stateless/reset
            const res = await soapService.get(1);
            if (res && res.reservation) {
                // Wrap in array for table
                setData([res.reservation]);
            } else {
                setData([]);
            }
        } catch (e) {
            console.error("SOAP Load Error:", e);
            setData([]);
        } finally {
            setLoading(false);
        }
    };

    const loadGrpcData = async () => {
        setLoading(true);
        try {
            // gRPC service doesn't support getAll, so we show a placeholder to indicate it's working
            // In a real app, we'd implement getAll in proto
            setData([{
                id: 'gRPC-Demo',
                client: { nom: 'AIT BEN BRAHIM', prenom: 'Hasna', email: 'hasna@aitbenbrahim.ma' },
                chambre: { type: 'Demo Room' },
                dateDebut: '2023-10-01',
                dateFin: '2023-10-05',
                preferences: 'gRPC Created Successfully (Placeholder)'
            }]);
        } catch (e) {
            console.error(e);
        } finally {
            setLoading(false);
        }
    };

    const loadRestData = async () => {
        setLoading(true);
        try {
            // Assuming proxy or CORS handles the port, but for now we might need to adjust service if hardcoded
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
            client: { nom: "AIT BEN BRAHIM", prenom: "Hasna", email: "hasna@aitbenbrahim.ma", telephone: "1234567890" },
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
            alert('SOAP Request Sent Successfully! Note: The list might not update immediately as the SOAP service is stateless in this demo.');
        } else if (protocol === 'GraphQL') {
            await createReservation({
                variables: {
                    client: newRes.client,
                    chambre: newRes.chambre,
                    dateDebut: newRes.dateDebut,
                    dateFin: newRes.dateFin,
                    preferences: newRes.preferences
                }
            });
            refetch();
        } else if (protocol === 'gRPC') {
            await grpcService.create(newRes);
            alert('gRPC Request Sent Successfully! Note: The list cannot be updated as the gRPC service does not support listing all reservations.');
            loadGrpcData();
        }
    };

    return (
        <div className="min-h-screen bg-slate-50 text-slate-900 font-sans">
            <header className="bg-indigo-600 text-white shadow-lg">
                <div className="max-w-7xl mx-auto px-4 py-6 sm:px-6 lg:px-8 flex justify-between items-center">
                    <h1 className="text-3xl font-extrabold tracking-tight">
                        Hotel Reservation <span className="text-indigo-200">Manager</span>
                    </h1>
                    <div className="text-sm font-medium bg-indigo-700 px-3 py-1 rounded-full">
                        Multi-Protocol Architecture
                    </div>
                </div>
            </header>

            <main className="max-w-7xl mx-auto px-4 py-8 sm:px-6 lg:px-8">
                <div className="bg-white rounded-xl shadow-sm border border-slate-200 overflow-hidden">
                    <div className="p-6 border-b border-slate-100 bg-slate-50/50 flex flex-col sm:flex-row sm:items-center justify-between gap-4">
                        <div className="flex space-x-2 bg-slate-200 p-1 rounded-lg">
                            {['REST', 'SOAP', 'GraphQL', 'gRPC'].map(p => (
                                <button
                                    key={p}
                                    onClick={() => setProtocol(p)}
                                    className={`px-4 py-2 rounded-md text-sm font-medium transition-all duration-200 ${protocol === p
                                        ? 'bg-white text-indigo-600 shadow-sm'
                                        : 'text-slate-600 hover:text-slate-900 hover:bg-slate-300/50'
                                        }`}
                                >
                                    {p}
                                </button>
                            ))}
                        </div>
                        <button
                            onClick={handleCreate}
                            className="inline-flex items-center px-4 py-2 border border-transparent text-sm font-medium rounded-md shadow-sm text-white bg-indigo-600 hover:bg-indigo-700 focus:outline-none focus:ring-2 focus:ring-offset-2 focus:ring-indigo-500 transition-colors"
                        >
                            <svg className="-ml-1 mr-2 h-5 w-5" xmlns="http://www.w3.org/2000/svg" viewBox="0 0 20 20" fill="currentColor">
                                <path fillRule="evenodd" d="M10 3a1 1 0 011 1v5h5a1 1 0 110 2h-5v5a1 1 0 11-2 0v-5H4a1 1 0 110-2h5V4a1 1 0 011-1z" clipRule="evenodd" />
                            </svg>
                            New Reservation
                        </button>
                    </div>

                    <div className="p-6">
                        <div className="mb-4 flex items-center justify-between">
                            <h2 className="text-lg font-semibold text-slate-800">
                                {protocol} Reservations
                            </h2>
                            {loading && <span className="text-sm text-indigo-600 animate-pulse">Syncing...</span>}
                        </div>

                        {loading || (protocol === 'GraphQL' && gqlLoading) ? (
                            <div className="flex justify-center items-center h-64">
                                <div className="animate-spin rounded-full h-12 w-12 border-b-2 border-indigo-600"></div>
                            </div>
                        ) : (
                            <div className="overflow-hidden ring-1 ring-black ring-opacity-5 rounded-lg">
                                <table className="min-w-full divide-y divide-slate-200">
                                    <thead className="bg-slate-50">
                                        <tr>
                                            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">ID</th>
                                            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">Client</th>
                                            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">Room</th>
                                            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">Dates</th>
                                            <th scope="col" className="px-6 py-3 text-left text-xs font-medium text-slate-500 uppercase tracking-wider">Status</th>
                                        </tr>
                                    </thead>
                                    <tbody className="bg-white divide-y divide-slate-200">
                                        {data.map((res) => (
                                            <tr key={res.id} className="hover:bg-slate-50 transition-colors">
                                                <td className="px-6 py-4 whitespace-nowrap text-sm font-medium text-slate-900">#{res.id}</td>
                                                <td className="px-6 py-4 whitespace-nowrap">
                                                    <div className="flex items-center">
                                                        <div className="h-8 w-8 rounded-full bg-indigo-100 flex items-center justify-center text-indigo-700 font-bold text-xs mr-3">
                                                            {res.client?.nom?.[0]}{res.client?.prenom?.[0]}
                                                        </div>
                                                        <div>
                                                            <div className="text-sm font-medium text-slate-900">{res.client?.prenom} {res.client?.nom}</div>
                                                            <div className="text-xs text-slate-500">{res.client?.email || 'No email'}</div>
                                                        </div>
                                                    </div>
                                                </td>
                                                <td className="px-6 py-4 whitespace-nowrap">
                                                    <span className="px-2 inline-flex text-xs leading-5 font-semibold rounded-full bg-green-100 text-green-800">
                                                        {res.chambre?.type}
                                                    </span>
                                                </td>
                                                <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-500">
                                                    {res.dateDebut} <span className="mx-1">→</span> {res.dateFin}
                                                </td>
                                                <td className="px-6 py-4 whitespace-nowrap text-sm text-slate-500">
                                                    <span className="inline-flex items-center px-2.5 py-0.5 rounded-full text-xs font-medium bg-blue-100 text-blue-800">
                                                        Confirmed
                                                    </span>
                                                </td>
                                            </tr>
                                        ))}
                                    </tbody>
                                </table>
                                {data.length === 0 && (
                                    <div className="text-center py-12">
                                        <svg className="mx-auto h-12 w-12 text-slate-400" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                                            <path strokeLinecap="round" strokeLinejoin="round" strokeWidth={2} d="M9 12h6m-6 4h6m2 5H7a2 2 0 01-2-2V5a2 2 0 012-2h5.586a1 1 0 01.707.293l5.414 5.414a1 1 0 01.293.707V19a2 2 0 01-2 2z" />
                                        </svg>
                                        <h3 className="mt-2 text-sm font-medium text-slate-900">No reservations found</h3>
                                        <p className="mt-1 text-sm text-slate-500">Get started by creating a new reservation.</p>
                                    </div>
                                )}
                            </div>
                        )}
                    </div>
                </div>
            </main>
        </div>
    );
}

export default App;
