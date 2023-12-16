import axios from 'axios'
import { useRouter } from "vue-router";
import { useStore } from "vuex";
import authConstant from "../store/modules/auth/authConstant";
const baseURLLaravel = import.meta.env.VITE_URL_LARAVEL;

export default (URL) => {
    const store = useStore();
    const router = useRouter();

    const api = axios.create({
        baseURL: URL
    })

    const token = URL === baseURLLaravel ? localStorage.getItem('token_admin') : localStorage.getItem('token');
    if (token) {
        api.defaults.headers.common.Authorization = `Bearer ${token}`;
    }

    // api.interceptors.response.use(
    //     (response) => response,
    //     (error) => {
    //         if (error.response.status === 401) {
    //             toaster.error('Forced logout. Unauthorized action');
    //             store.dispatch(`auth/${authConstant.LOGOUT}`);
    //             router.push({ name: "home" });
    //         }
    //         return Promise.reject(error);
    //     }
    // );

    return api
}