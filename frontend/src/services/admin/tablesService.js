import Api from '../Api'

const baseURL = import.meta.env.VITE_URL_LARAVEL;
const ENDPOINT = 'tables';
export default {

    GetTables() {
        return Api(baseURL).get(ENDPOINT);
    },
    GetTable(id) {
        return Api(baseURL).get(`${ENDPOINT}/${id}`);
    },//GetCategory
    CreateTables(data) {
        return Api(baseURL).post(ENDPOINT, data);
    },
    UpdateTables(data) {
        return Api(baseURL).put(`${ENDPOINT}/${data.tableID}`, data);
    },
    DeleteTable(id) {
        return Api(baseURL).delete(`${ENDPOINT}/${id}`);
    }
}