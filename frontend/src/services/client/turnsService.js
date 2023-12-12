import Api from '../Api';

const baseURL = import.meta.env.VITE_URL_SPRING;
const ENDPOINT = 'turns';

export default {
    GetTurns() {
        return Api(baseURL).get(ENDPOINT);
    }
}