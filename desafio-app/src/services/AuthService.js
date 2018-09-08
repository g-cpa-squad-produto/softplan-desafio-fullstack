export const AuthService = {
    isAuthenticated: false,
    login(cb) {
        this.isAuthenticated = true;
        setTimeout(cb, 100);
    },
    logout(cb) {
        this.isAuthenticated = false;
        setTimeout(cb, 100);
    }
};