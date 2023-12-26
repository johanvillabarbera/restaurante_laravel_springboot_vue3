import clientConstant from './clientConstant';
import reservationService from '../../../services/client/reservationService';

export const reservation = {
    state: {
        reservation: [],
    }, 
    namespaced: true,
    actions: {
        [clientConstant.CREATE_RESERVATION]: async (store, payload) => {
            try {
                console.log(payload);
                const response = await reservationService.CreateReservation(payload);
                store.commit(clientConstant.CREATE_RESERVATION, response.data);
            } catch (error) {
                console.error(error);
            }
        }
    },
    mutations: {
        [clientConstant.CREATE_RESERVATION]: (state, payload) => {
            state.turn = payload;
        }
    }
};