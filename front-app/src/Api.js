import axios from 'axios'


const api = axios.create(
    {
        baseURL: 'http://localhost:8080/'
    }
)

export const loadGenres = () => api.get('genres')

export const loadUsuarios = () => api.get('usuarios')
export const saveUsuario = (newUsuario) => api.post('usuarios', newUsuario)
export const updateUsuario = (usuario) => api.put('usuarios/' + usuario.id, usuario)
export const deleteUsuario = (id) => api.delete('usuarios/' + id)
/* export const loginByUserAndPass = (email,senha) => api.get('usuarios?email=' + email+'&?senha='+senha)
 */
export const loginByUserAndPass = (email,senha) => api.get('usuarios/login/'+email+"/"+senha)
export const saveProcesso = (newProcesso) => api.post('processos', newProcesso)
export const loadProcessos = () => api.get('processos')
export const loadProcessoById = (id) => api.get('processos/' + id)
export const saveParecer = (newParecer) => api.post('pareceres', newParecer)




export const updateSeries = (series) => api.put('series/' + series.id, series)
export const loadSeriesByGenre = (genre) => api.get('series?genre=' + genre)
export const deleteSeries = (id) => api.delete('series/' + id)
export const loadSeriesById = (id) => api.get('series/' + id)



const apis = {
    loadGenres,

    loadUsuarios,
    saveUsuario,
    updateUsuario,
    deleteUsuario,
    loginByUserAndPass,
    saveProcesso,
    loadProcessos,
    loadProcessoById,
    saveParecer,


    updateSeries,
    loadSeriesByGenre,
    deleteSeries,
    loadSeriesById
}

export default apis