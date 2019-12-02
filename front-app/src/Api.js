import axios from 'axios'


const api = axios.create(
    {
        baseURL: 'http://localhost:3002/'
    }
)

export const loadGenres = () => api.get('genres')
export const saveUsuario = (newUsuario) => api.post('usuario', newUsuario)
export const updateSeries = (series) => api.put('series/'+series.id, series)
export const loadSeriesByGenre = (genre) => api.get('series?genre=' + genre)
export const deleteSeries = (id) => api.delete('series/' + id)
export const loadSeriesById = (id) => api.get('series/' + id)



const apis = {
    loadGenres,
    saveUsuario,
    updateSeries,
    loadSeriesByGenre,
    deleteSeries,
    loadSeriesById
}

export default apis