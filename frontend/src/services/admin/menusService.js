import Api from '../Api';

const baseURL = import.meta.env.VITE_URL_LARAVEL;
const ENDPOINT = 'menus';

export default {
    
    GetMenus() {
        return Api(baseURL).get(ENDPOINT);
    },
    GetMenu(id) {
        return Api(baseURL).get(`${ENDPOINT}/${id}`);
    },
    CreateMenus(data) {
        return Api(baseURL).post(ENDPOINT, data);
    },
    UpdateMenus(data) {
        return Api(baseURL).put(`${ENDPOINT}/${data.menuID}`, data);
    }
}