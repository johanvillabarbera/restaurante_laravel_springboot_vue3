import Api from '../Api';

const baseURL = import.meta.env.VITE_URL_SPRING;
const ENDPOINT = 'tables/filters';

// I need pass filters in params to url baseURL + ENDPOINT

export default {
    
    GetTables(filters) {
        return Api(baseURL).get(ENDPOINT, { params: filters });
    }
}