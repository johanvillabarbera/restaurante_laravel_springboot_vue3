import Vuex from "vuex";

// CLIENT
import { menu } from "./modules/client/menusClient";
import { turn } from "./modules/client/turnsClient";
import { reservation } from "./modules/client/reservationClient";
// ADMIN
import { tables } from "./modules/admin/tablesAdmin";
import { menus } from "./modules/admin/menusAdmin";
import { auth } from "./modules/auth/auth";
import { reservations } from "./modules/admin/reservationsAdmin";

export default Vuex.createStore({
    modules: {
        menu: menu,
        tables: tables,
        menus: menus,
        turns: turn,
        auth: auth,
        reservation: reservation,
        reservations: reservations
    },
});
