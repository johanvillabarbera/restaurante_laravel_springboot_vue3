import adminConstant from './adminConstant';
import adminMenusService from '../../../services/admin/menusService';

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
                store.commit(adminConstant.UPDATE_MENUS, response.data.data);
            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.CREATE_MENU]: async (store, payload) => {
            try {
                const response = await adminMenusService.CreateMenus(payload);
                store.commit(adminConstant.CREATE_MENU, response.data.data);
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
                state.menus.push(payload);
            }
        },
        [adminConstant.CREATE_MENU]: (state, payload) => {
            if (payload) {
                state.menus.push(payload);
            }
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