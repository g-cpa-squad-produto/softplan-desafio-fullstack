const userKey = 'token'
const INITIAL_STATE = {
    token: localStorage.getItem(userKey),
    validToken: false
}

export default (state = INITIAL_STATE, action) => {
    switch (action.type) {
        case 'TOKEN_VALIDATED':
            if (action.payload) {
                return { ...state, validToken: true }
            } else {
                localStorage.removeItem(userKey)
                return { ...state, validToken: false, user: null }
            }
        case 'USER_FETCHED':
            localStorage.setItem(userKey, action.payload)
            return { ...state, token: action.payload, validToken: true }
        default:
            return state
    }
}