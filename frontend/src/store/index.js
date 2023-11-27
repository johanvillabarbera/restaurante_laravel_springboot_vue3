import Vuex from "vuex";

// CLIENT
import { menu } from "./modules/client/menusClient";

// ADMIN
import { tables } from "./modules/admin/tablesAdmin";
import { menus } from "./modules/admin/menusAdmin";

export default Vuex.createStore({
    modules: {
        menu: menu,
        tables: tables,
        menus: menus
    },
});
