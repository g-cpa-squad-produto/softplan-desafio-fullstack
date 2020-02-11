import { Component } from "react";
import { logout } from "../../services/auth";

class Logout extends Component {
    render() {
        logout();
        this.props.history.push("/");
        return null;
    }
}

export default Logout;