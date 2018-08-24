export class Profile {
    id: string
    nome: string
}

export class User {
    id: string
    nome: string
    login: string
    profile: Profile
}
