import authConstant from './authConstant.js';
import AuthService from '../../../services/auth/authService.js';
import router from '../../../router/index.js'
import { Notify } from 'quasar';

export const auth = {
    namespaced: true,
    state: {
        user: {},
        isAuth: false,
        isAdmin: false,
        isAccountVerified: false
    },
    actions: {
        [authConstant.LOGIN]: async (store, payload, state) => {
            try {
                const response = await AuthService.Login(payload);
                if (response.status === 200) {
                    store.commit(authConstant.LOGIN, response.data);
                    if (response.data.user.idrol == 2) {
                        const response_admin = await AuthService.LoginAdmin(payload);
                        if (response_admin.status === 200) {
                            store.commit(authConstant.LOGIN_ADMIN, response_admin.data);
                        }
                    }
                }
            } catch (error) {
                
                if (error.response.status === 403) {
                    Notify.create({
                        type: 'negative',
                        message: 'Accede a tu correo para verificar tu cuenta'
                    });
                } else if (error.response.status === 401) {
                    Notify.create({
                        type: 'negative',
                        message: 'Usuario o contraseña incorrectos'
                    });
                }
            }
        },//LOGIN

        [authConstant.LOGOUT]: async (store, payload) => {
            try {
                const response = await AuthService.Logout();
                let data = { status: response.status };
                console.log(store.state.isAdmin);
                if (store.state.isAdmin) {
                    const response_admin = await AuthService.LogoutAdmin();
                    data.status_admin = response_admin.status;
                }
                store.commit(authConstant.LOGOUT, data);
            } catch (error) {
                console.error('Logout error');
                store.commit(authConstant.LOGOUT, { status: null, status_admin: null });
            }
        },//LOGOUT

        [authConstant.ADD_USER]: async (store, payload) => {
            try {
                const response = await AuthService.Register(payload);
                if (response.status === 201) {
                    store.commit(authConstant.ADD_USER, true);
                    Notify.create({
                        type: 'positive',
                        message: 'Cuenta creada, revisa tu correo para confirmarla'
                    });
                }
            } catch (error) {
                console.error('Register error');
            }
        },//ADD_USER

        [authConstant.INITIALIZE_PROFILE]: async (store) => {
            try {
                const response = await AuthService.Profile();
                console.log(response);
                if (response.status === 200) {

                    store.commit(authConstant.INITIALIZE_PROFILE, response.data);
                }
            } catch (error) {
                console.error(error);
            }
        },//INITIALIZE_PROFILE

        // COnfirm account

        [authConstant.CONFIRM_ACCOUNT]: async (store, payload) => {
            try {
                const response = await AuthService.ConfirmAccount(payload);
                if (response.status === 200) {
                    Notify.create({
                        type: 'positive',
                        message: 'Cuenta confirmada'
                    });
                }
            } catch (error) {
                console.error(error);
            }
        },

        // Bookings history

        [authConstant.BOOKINGS_HISTORY]: async (store) => {
            try {
                const response = await AuthService.BookingsHistory();
                if (response.status === 200) {
                    store.commit(authConstant.BOOKINGS_HISTORY, response.data);
                }
            } catch (error) {
                console.error(error);
            }
        }

    },//actions
    mutations: {
        [authConstant.LOGIN]: (state, payload) => {
            if (payload) {
                localStorage.setItem("token", payload.token);
                localStorage.setItem("isAuth", true);
                state.user = payload.user;
                state.isAuth = true;
                router.push({ name: 'home' });
            }
        },//LOGIN
        [authConstant.LOGIN_ADMIN]: (state, payload) => {
            if (payload) {
                localStorage.setItem("token_admin", payload.token);
                localStorage.setItem("isAdmin", true);
                state.user = payload.user;
                state.isAdmin = true;
                router.push({ name: 'home' });
            }
        },//LOGIN_ADMIN
        [authConstant.ADD_USER]: (state, payload) => {
            if (payload) {
                router.push({ name: 'login' });
            }
        },//ADD_USER
        [authConstant.INITIALIZE_PROFILE]: (state, payload) => {
            if (payload) {
                state.user = payload;
                state.isAuth = true;
                state.isAdmin = payload.idrol == 2 ? true : false;
                localStorage.setItem("isAuth", true);
                localStorage.setItem("isAdmin", state.isAdmin);
            }
        },//INITIALIZE_PROFILE

        [authConstant.LOGOUT]: (state, payload) => {
            if (payload.status === 200) {
                console.log('logout spring ok');
                localStorage.removeItem('token');
                localStorage.removeItem('isAdmin');
            }
            if (payload.status_admin === 200) {
                console.log('logout laravel ok');
                localStorage.removeItem('token_admin');
                localStorage.removeItem('isAdmin');
            }
            state.user = {};
            state.isAuth = false;
            localStorage.removeItem('isAuth');
            router.push({ name: 'home' });
            

        },//LOGOUT

        // Confirm account

        // [authConstant.CONFIRM_ACCOUNT]: (state, payload) => {
        //     if (payload) {
        //         router.push({ name: 'login' });
        //     }
        // }

        // Bookings history

        [authConstant.BOOKINGS_HISTORY]: (state, payload) => {
            if (payload) {
                state.bookings_history = payload;
            }
        }
        

    },//mutations
    getters: {
        GetProfile: (state) => {
            return state.user;
        },//GetProfile
        GetIsAuth: (state) => {
            return state.isAuth;
        },//GetIsAuth
        GetIsAdmin: (state) => {
            return state.isAdmin;
        },//GetIsAdmin
        GetBookingsHistory: (state) => {
            return state.bookings_history;
        }

    }//getters
}//export