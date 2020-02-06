class Auth {

    saveUser(user) {
        const base64 = btoa(JSON.stringify(user));
        sessionStorage.setItem("auth", base64);
    }

    /**
     * return { email: "", token: "", expiresIn: 0, role: "TRIADOR | FINALIZADOR | ADMIN"}
     */
    getUser() {
        const base64 = sessionStorage.getItem("auth");
        return !!base64 ? JSON.parse(atob(base64)) : null;
    }

}

export default new Auth();