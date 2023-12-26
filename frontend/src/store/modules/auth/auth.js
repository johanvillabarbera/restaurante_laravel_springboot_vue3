import authConstant from './authConstant.js';
import AuthService from '../../../services/auth/authService.js';
import router from '../../../router/index.js'

export const auth = {
    namespaced: true,
    state: {
        user: {},
        isAuth: false,
        isAdmin: false
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
                console.error('Username or password incorrect');
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
                    store.commit(Constant.ADD_USER, true);
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

    }//getters
}//export