import Api from "../Api";

const baseURL = import.meta.env.VITE_URL_SPRING;
const ENDPOINT_MENUS = 'notify/menus';
const ENDPOINT_TABLES = 'notify/tables';
export default {

    GetNotifyMenus() {
        Api(baseURL).get(ENDPOINT_MENUS);
    },

    GetNotifyTables() {
        Api(baseURL).get(ENDPOINT_TABLES);
    }
}