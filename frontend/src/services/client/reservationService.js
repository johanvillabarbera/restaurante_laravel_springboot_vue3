import Api from '../Api'

const baseURL = import.meta.env.VITE_URL_SPRING;
const ENDPOINT = 'booking';

export default {

    CreateReservation(filters) {
        return Api(baseURL).post(ENDPOINT, filters);
    }
}