export class User {
    userId?:  number;
    name?: string;
    email?: string;
    password?: string;
    roles?: string;

    constructor(user) {
        this.userId = user.userId || '',
        this.name = user.name || '';
        this.email = user.email || '';
        this.password = user.password || '';
        this.roles = user.roles || '';
    }

    isAdmin(): boolean {
        return (this.roles === 'ADMIN');
    }

    isTriador(): boolean {
        return (this.roles === 'TRIADOR');
    }

    isFinalizador(): boolean {
        return (this.roles === 'FINALIZADOR');
    }
}