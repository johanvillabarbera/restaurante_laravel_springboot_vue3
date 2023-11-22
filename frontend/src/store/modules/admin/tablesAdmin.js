import adminConstant from './adminConstant';
import adminTablesService from '../../../services/admin/tablesService';

export const tables = {
    state: {
        menu: []
    },
    namespaced: true,
    actions: {
        [adminConstant.INITIALIZE_TABLES]: async (store) => {
            try {
                const response = await adminTablesService.GetTables();
                store.commit(adminConstant.INITIALIZE_TABLES, response.data.data);
            } catch (error) {
                console.error(error);
            }
        }
    },
    mutations: {
        [adminConstant.INITIALIZE_TABLES]: (state, payload) => {
            state.tables = payload;
        }
    },
    getters: {
        GetTables(state) {
            return state.tables;
        }
    }
};