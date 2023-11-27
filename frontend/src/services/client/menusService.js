import Api from '../Api'

const baseURL = import.meta.env.VITE_URL_SPRING;
const ENDPOINT = 'menus';

export default {

    GetMenus() {
        return Api(baseURL).get(ENDPOINT);
    }
}