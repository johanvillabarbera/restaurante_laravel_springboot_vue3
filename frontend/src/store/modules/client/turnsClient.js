import clientConstant from './clientConstant';
import clientTurnService from '../../../services/client/turnsService';

export const turn = {
    state: {
        turn: [],
    }, 
    namespaced: true,
    actions: {
        [clientConstant.INITIALIZE_TURNS]: async (store) => {
            try {
                const response = await clientTurnService.GetTurns();
                store.commit(clientConstant.INITIALIZE_TURNS, response.data);
            } catch (error) {
                console.error(error);
            }
        }
    },
    mutations: {
        [clientConstant.INITIALIZE_TURNS]: (state, payload) => {
            state.turn = payload;
        }
    },
    getters: {
        GetTurns(state) {
            return state.turn;
        }
    }
};
