import { Urls } from '../common/CommonApi'

const url = `${Urls.devUrl}/usuario`

export class UsuarioAPI {

    getAllUsuarios() {
        try {
            return fetch(url).then(res => res.json())
        } catch (error) {
            console.log('Erro ao consumir api do Resource Usuario' + error)
            return undefined
        }
    }

}
