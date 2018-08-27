
import { Urls } from '../common/CommonApi'

const urlProcesso = `${Urls.devUrl}/processo`
const headers = new Headers()
headers.append('Content-Type', 'application/json')

export class ProcessoAPI {

    getAllProcessos() {
        let requestProcessos =
            fetch(urlProcesso)
                .then(response => response.json())
        return requestProcessos;
    }

    saveNovoProcesso(processo) {
        let requestProcessos =
            fetch(urlProcesso, {
                method: 'POST',
                body: JSON.stringify(processo),
                headers: headers
            })
                .then(response => response.json())
        return requestProcessos;
    }

}