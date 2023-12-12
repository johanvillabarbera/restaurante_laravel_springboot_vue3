import adminConstant from './adminConstant';
import adminTablesService from '../../../services/admin/tablesService';
import notifyClientsService from '../../../services/admin/notifyClientsService';
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
        },
        [adminConstant.INITIALIZE_TABLE]: async (store, payload) => {
            try {
                const data = store.state.tables ?? [];
                const index = data.findIndex(item => item.id == payload);
                if (index === -1) {
                    const response = await adminTablesService.GetTable(payload);
                    store.commit(adminConstant.INITIALIZE_TABLE, response.data.data);
                } else {
                    store.commit(adminConstant.INITIALIZE_TABLE, store.state.tables[index]);
                }
            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.CREATE_TABLES]: async (store, payload) => {
            try {
                const response = await adminTablesService.CreateTables(payload);
                store.commit(adminConstant.CREATE_TABLES, response.data.data);

                notifyClientsService.GetNotifyTables();
            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.UPDATE_TABLES]: async (store, payload) => {
            try {
                const response = await adminTablesService.UpdateTables(payload);
                if(response.status === 200){
                    store.commit(adminConstant.UPDATE_TABLES, payload);
                }

                notifyClientsService.GetNotifyTables();
            } catch (error) {
                console.error(error);
            }
        },
        [adminConstant.DELETE_TABLE]: async (store, payload) => {
            try {
                const response = await adminTablesService.DeleteTable(payload);
                if (response.status === 200) {
                    store.commit(adminConstant.DELETE_TABLE, payload);
                    // AVISAMOS AL CLIENTE
                    notifyClientsService.GetNotifyTables();
                }
            } catch (error) {
                console.error(error);
            }
        }
    },
    mutations: {
        [adminConstant.INITIALIZE_TABLES]: (state, payload) => {
            state.tables = payload;
        },
        [adminConstant.INITIALIZE_TABLE]: (state, payload) => {
            if (payload) {
                state.table = payload;
            }
        },
        [adminConstant.CREATE_TABLES]: (state, payload) => {
            if (payload) {
                state.tables.push(payload);
            }
        },
        [adminConstant.UPDATE_TABLES]: (state, payload) => {
            if (payload) {
                const index = (state.tables ?? []).findIndex(item => item.tableID == payload.tableID);
                if (index !== -1) {
                    state.tables[index] = payload;
                }
            }
        },
        [adminConstant.DELETE_TABLE]: (state, payload) => {
            state.tables = state.tables.filter(table => table.tableID !== payload);
        }
    },
    getters: {
        GetTables(state) {
            return state.tables;
        },
        GetTable(state) {
            return state.table;
        }
    }
};