import Vuex from "vuex";

import { menu } from "./modules/client/menusClient";
import { tables } from "./modules/admin/tablesAdmin";
export default Vuex.createStore({
    modules: {
        menu: menu,
        tables: tables,
    },
});
