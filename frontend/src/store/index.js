import Vuex from "vuex";

// CLIENT
import { menu } from "./modules/client/menusClient";
import { turn } from "./modules/client/turnsClient";
// ADMIN
import { tables } from "./modules/admin/tablesAdmin";
import { menus } from "./modules/admin/menusAdmin";
import { auth } from "./modules/auth/auth";
export default Vuex.createStore({
    modules: {
        menu: menu,
        tables: tables,
        menus: menus,
        turns: turn,
        auth: auth,
    },
});
