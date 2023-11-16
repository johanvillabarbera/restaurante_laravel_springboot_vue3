import clientConstant from './clientConstant';
import clientMenuService from '../../../services/client/menusService';

export const menu = {
    namespaced: true,
    actions: {
        [clientConstant.INITIALIZE_MENU]: async (store) => {
            try {
                const response = await clientMenuService.GetMenus();
                store.commit(clientConstant.INITIALIZE_MENU, response.data);
            } catch (error) {
                console.error(error);
            }
        },

    },//actions
    mutations: {
        [clientConstant.INITIALIZE_MENU]: (state, payload) => {
            if (payload) {
                state.menu = payload;
            }
        }
    },//mutations
    getters: {
        GetMenus(state) {
            return state.menu;
        }
    }//getters
}//export