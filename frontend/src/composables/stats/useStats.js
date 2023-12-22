import { ref } from 'vue';
import statsService from '../../services/admin/statsService';

export const useStats = () => {
    const stats = ref([]);
    const error = ref(null);

    const loadStatsPredict = async () => {
        try {
            const response = await statsService.GetStatsPredicts();
            stats.value = response.data;
        } catch (err) {
            error.value = err;
        }
    }

    return { stats, error, loadStatsPredict }
}