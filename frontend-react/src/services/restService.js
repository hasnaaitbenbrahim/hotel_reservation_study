import axios from 'axios';

const API_URL = '/api/reservations';

export const restService = {
    getAll: async () => {
        const response = await axios.get(API_URL);
        return response.data;
    },
    create: async (reservation) => {
        const response = await axios.post(API_URL, reservation);
        return response.data;
    },
    update: async (id, reservation) => {
        const response = await axios.put(`${API_URL}/${id}`, reservation);
        return response.data;
    },
    delete: async (id) => {
        await axios.delete(`${API_URL}/${id}`);
    }
};
