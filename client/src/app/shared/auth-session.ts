import { Injectable } from '@angular/core'
import { Subject } from 'rxjs';

export class UserSession {
    id: string
    username: string
    firstName: string
    lastName: string
    email: string
    token: string
    createdAt: string
}

/**
 * Mantem o estado do usuario logado na session atraves de um Subject
 */
@Injectable()
export class AuthSession {

    private userSessionSubject = new Subject<UserSession>()

    setSession (userSession: UserSession) {
        this.userSessionSubject.next(userSession)
    }

    subscribe (fn: (u: UserSession) => any) {
        this.userSessionSubject.subscribe(fn)
    }

}
