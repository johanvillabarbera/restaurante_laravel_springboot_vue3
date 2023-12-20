import { ref } from 'vue';
import statsService from '../../services/admin/statsService';

export default function useStats() {
    const stats = ref([]);
    const error = ref(null);

    const load = async () => {
        try {
            const response = await statsService.GetStatsPredicts();
            stats.value = response.data;
        } catch (err) {
            error.value = err;
        }
    }

    return { stats, error, load }
}