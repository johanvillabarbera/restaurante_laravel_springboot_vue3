import Api from '../Api'

const baseURLSpring = import.meta.env.VITE_URL_SPRING;
const baseURLLaravel = import.meta.env.VITE_URL_LARAVEL;

const ENDPOINTS = {
    "ENDPOINT_LOGIN_SPRING": "user/login",
    "ENDPOINT_LOGIN_LARAVEL": "login",
    "ENDPOINT_IS_ADMIN": "isAdmin",
    "ENDPOINT_PROFILE": "user/profile",
    "ENDPOINT_LOGOUT_SPRING": "user/logout",
    "ENDPOINT_LOGOUT_LARAVEL": "logout",
    "ENDPOINT_REGISTER": "user/register",
};

export default {

    Login(data) {
        return Api(baseURLSpring).post(ENDPOINTS.ENDPOINT_LOGIN_SPRING, data);
    },

    LoginAdmin(data) {
        return Api(baseURLLaravel).post(ENDPOINTS.ENDPOINT_LOGIN_LARAVEL, data);
    },

    isAdmin() {
        return Api(baseURLLaravel).get(ENDPOINTS.ENDPOINT_IS_ADMIN);
    },

    Profile() {
        return Api(baseURLSpring).get(ENDPOINTS.ENDPOINT_PROFILE);
    },//Profile

    Logout() {
        return Api(baseURLSpring).post(ENDPOINTS.ENDPOINT_LOGOUT_SPRING);
    },

    LogoutAdmin() {
        return Api(baseURLLaravel).get(ENDPOINTS.ENDPOINT_LOGOUT_LARAVEL);
    },

    Register(data) {
        return Api(baseURLSpring).post(ENDPOINTS.ENDPOINT_REGISTER, data);
    },//Register
}