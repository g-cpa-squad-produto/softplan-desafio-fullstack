export const CREATE_SESSION = 'CREATE_SESSION';
export const LOAD_SESSION = 'LOAD_SESSION';
export const DELETE_SESSION = 'DELETE_SESSION';

export const loadSession = () => {
    return {
        type: LOAD_SESSION
    };
}

export const signout = () => {
    return (dispatch) => {
        return Promise.resolve({
            type: DELETE_SESSION
        });
    }
}