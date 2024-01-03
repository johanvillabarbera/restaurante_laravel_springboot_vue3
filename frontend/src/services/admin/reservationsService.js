import Api from '../Api';

const baseURL = import.meta.env.VITE_URL_LARAVEL;
const ENDPOINT = 'reservations';

export default {
    
    GetReservations() {
        return Api(baseURL).get(ENDPOINT);
    },
    UpdateReservation(data) {
        return Api(baseURL).put(`${ENDPOINT}/${data.bookingID}`, data);
    },
}