import axios from 'axios';

const API_URL = 'http://localhost:8080'
const PROCESS_API_URL = `${API_URL}/process`

class ProcessDataService {

    retrieveAllProcess(){
        console.log('retrieve all process - service')
        return axios.get(`${PROCESS_API_URL}`)
    }

    retrieveProcessById(id){
        console.log('retrieve process by id - service')
        return axios.get(`${PROCESS_API_URL}/${id}`)
    }

    createProcess(process){
        console.log('create process - service')
        return axios.post(`${PROCESS_API_URL}`, process)
    }

    updateProcess(id, process){
        console.log('update process - service')
        return axios.put(`${PROCESS_API_URL}/${id}`, process)
    }

    deleteProcess(id) {
        console.log('delete process - service')
        return axios.delete(`${PROCESS_API_URL}/${id}`);
    }

}

export default new ProcessDataService()