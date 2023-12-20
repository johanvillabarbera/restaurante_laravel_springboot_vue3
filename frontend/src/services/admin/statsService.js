// Service to get stats from the backend laravel
// Path: develop_miguel/frontend/src/services/admin/statsService.js
// Compare this snippet from develop_miguel/frontend/src/services/admin/notifyClientsService.js:
import Api from "../Api";
const baseURL = import.meta.env.VITE_URL_LARAVEL;
const ENDPOINT_STATS = 'userEvents';

export default {
    
    GetStatsPredicts() {
        return Api(baseURL).get(ENDPOINT_STATS);
    }
}


