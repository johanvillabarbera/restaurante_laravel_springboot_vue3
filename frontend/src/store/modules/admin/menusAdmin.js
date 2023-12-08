import adminConstant from './adminConstant';
import adminMenusService from '../../../services/admin/menusService';
// IMPORTAMOS EL SERVICIO PARA LANZAR LA PETICION QUE AVISARA A LOS USUARIOS MEDIANTE SPRINGBOOT
import notifyClientsService from '../../../services/admin/notifyClientsService';


export const menus = {
    state: {
        menu: []
    },
    namespaced: true,
    actions: {
        [adminConstant.INITIALIZE_MENUS]: async (store) => {
            try {
                const response = await adminMenusService.GetMenus();
                store.commit(adminConstant.INITIALIZE_MENUS, response.data.data);
            } catch (error) {
                console.log(error)
            }
        },
        [adminConstant.INITIALIZE_MENU]: async (store, payload) => {
            try {
                const data = store.state.menus ?? [];
                const index = data.findIndex(item => item.id == payload);
                if (index === -1) {
                    const response = await adminMenusService.GetMenu(payload);
                    store.commit(adminConstant.INITIALIZE_MENU, response.data.data);
                } else {
                    store.commit(adminConstant.INITIALIZE_MENU, store.state.menus[index]);
                }
            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.UPDATE_MENUS]: async (store, payload) => {
            try {
                const response = await adminMenusService.UpdateMenus(payload);
                if (response.status === 200) {
                    store.commit(adminConstant.UPDATE_MENUS, payload);
                }

                // AVISAMOS AL CLIENTE
                notifyClientsService.GetNotifyMenus();
                
            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.CREATE_MENU]: async (store, payload) => {
            try {
                const response = await adminMenusService.CreateMenus(payload);
                store.commit(adminConstant.CREATE_MENU, response.data.data);
                
                // AVISAMOS AL CLIENTE
                notifyClientsService.GetNotifyMenus();

            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.DELETE_MENU]: async (store, payload) => {
            try {
                const response = await adminMenusService.DeleteMenu(payload);
                if (response.status === 200) {
                    store.commit(adminConstant.DELETE_MENU, payload);
                    // AVISAMOS AL CLIENTE
                    notifyClientsService.GetNotifyMenus();
                }
            } catch (error) {
                console.error(error);
            }
        }
    },
    mutations: {
        [adminConstant.INITIALIZE_MENUS]: (state, payload) => {
            state.menus = payload;
        },
        [adminConstant.INITIALIZE_MENU]: (state, payload) => {
            if (payload) {
                state.menu = payload;
            }
        },
        [adminConstant.UPDATE_MENUS]: (state, payload) => {
            if (payload) {
                const index = (state.menus ?? []).findIndex(item => item.menuID == payload.menuID);
                if (index !== -1) {
                    state.menus[index] = payload;
                }
            }
        },
        [adminConstant.CREATE_MENU]: (state, payload) => {
            if (payload) {
                state.menus.push(payload);
            }
        },
        [adminConstant.DELETE_MENU]: (state, payload) => {
            state.menus = state.menus.filter(menu => menu.menuID !== payload);
        }
    },
    getters: {
        GetMenus(state) {
            return state.menus;
        },
        GetMenu(state) {
            return state.menu;
        }
    }
}