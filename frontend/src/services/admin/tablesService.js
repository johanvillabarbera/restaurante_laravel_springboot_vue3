import Api from '../Api'

const baseURL = import.meta.env.VITE_URL_LARAVEL;

export default {

    GetTables() {
        return Api(baseURL).get('tables')
    }
}