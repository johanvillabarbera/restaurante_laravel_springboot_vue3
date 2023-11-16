import Vuex from "vuex";

import { menu } from "./modules/client/menusClient";

export default Vuex.createStore({
    modules: {
        menu: menu,
    },
});
