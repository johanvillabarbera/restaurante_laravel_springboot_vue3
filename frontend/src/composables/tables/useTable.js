import { ref } from 'vue';
import tablesService from '../../services/client/tablesService';

// I need to pass filters to useTableFilters

export const useTableFilters = async (filters) => {
    console.log(filters);
    const tables = ref([]);
    const error = ref(null);

    await tablesService.GetTables(filters)
    .then(res => { tables.value = res.data })
    .catch(error => console.error(error))
    console.log(tables);
    
    return tables;
    const loadTables = async () => {
        try {
            const response = await tablesService.GetTables(filters);
            console.log(response);
            tables.value = response.data;
        } catch (err) {
            error.value = err.message;
        }
    }
    
    return { tables, error, loadTables };
};