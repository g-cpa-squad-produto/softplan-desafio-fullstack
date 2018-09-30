/**Representa um usuário para o frontend */
export class User {
    constructor(public id: string,
                public email: string,
                public password: string,
                public profile: string) {
    }
}
  