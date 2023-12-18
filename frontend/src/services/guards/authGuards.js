import store from '../../store';
import authConstant from '../auth/authService.js';
import AuthService from '../../services/auth/authService.js';

export default {

    async authGuardAdmin(to, from, next) {

        try {
            if (localStorage.getItem('isAdmin')) {
                const response = await AuthService.isAdmin();
                if (response.status === 200) {
                    next();
                }
            } else {
                next('/login');
            }
        } catch (error) {
            store.dispatch(`user/${authConstant.LOGOUT}`);

        }
    },//authGuardAdmin

    async AuthGuard(to, from, next) {
        if (localStorage.getItem('isAuth')) {
            await store.dispatch(`user/${authConstant.INITIALIZE_PROFILE}`);
        }
        if (store.getters['user/GetIsAuth'] && localStorage.getItem('isAuth')) {
            next();
        } else {
            next('/home');
        }
    },//noAuthGuard

    noAuthGuard(to, from, next) {
        if (!store.getters['auth/GetIsAuth'] && !localStorage.getItem('isAuth')) {
            next();
        } else {
            next('/home');
        }
    },//noAuthGuard
}//guards