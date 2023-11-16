import Api from '../Api'

const baseURL = import.meta.env.VITE_URL_SPRING;

export default {

    GetMenus() {
        return Api(baseURL).get('menus')
    }
}