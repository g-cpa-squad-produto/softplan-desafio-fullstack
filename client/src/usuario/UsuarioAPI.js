import { Urls } from '../common/CommonApi'

const url = `${Urls.devUrl}/usuario`

const headers = new Headers()
headers.append('Content-Type', 'application/json')

export class UsuarioAPI {


    getAllUsuarios() {
        try {
            return fetch(url).then(res => res.json())
        } catch (error) {
            console.log('Erro ao consumir api do Resource Usuario' + error)
            return undefined
        }
    }

    saveNovoUsuario(usuario) {
        try {
            let postRequest = fetch(url, {
                method: 'POST',
                body: JSON.stringify(usuario),
                headers: headers
            })
                .then(res => res.json())

            return postRequest
        } catch (error) {
            console.log('Erro ao Salvar um novo Usario' + error)
            return undefined
        }
    }

}
