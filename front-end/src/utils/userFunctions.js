import {manipulateData} from "./api";

export function getUsersFinalizing(context) {
    let data = {
        url: "/users/finalizadores",
        method: "GET"
    };
    manipulateData(data).then(data => context.setState({usersInitials: data, isLoading: false}));
}

export function getAllUsers(context) {
    let data = {
        url: '/users',
        method: "GET"
    }
    manipulateData(data).then(data => {
        context.setState({users: data, isLoading: false})
    });
}

export function removeUser(id, context) {
    let data = {
        url: '/users/' + id,
        method: 'DELETE'
    }
    manipulateData(data).then(() => {
        let updatedUsers = [...context.state.users].filter(i => i.id !== id);
        context.setState({users: updatedUsers});
    });
}

export function getUser(context) {
    let data = {
        url: "/users/" + context.props.match.params.id,
        method: 'GET'
    };
    manipulateData(data).then(data => {
        //NÃO INICIA O CADASTRO COM O CAMPO SENHA PREENCHIDO
        if(data.password) delete (data.password);
        context.setState({item: data, isLoading: false})
    });
}

export function saveUser(item, context) {
    if (item.type) {
        item.type = item.type.toUpperCase();
    }

    //REMOVE O CAMPO DE SENHA CASO ESTEJA VAZIO
    if (!item.password || item.password === "") {
        delete (item.password);
    }

    let endpoint = '/users';
    if (item && item.id) {
        endpoint += "/" + item.id;
    }

    let data = {
        url: endpoint,
        method: (item.id) ? 'PUT' : 'POST',
        body: JSON.stringify(item)
    };

    manipulateData(data).then(data => {
        console.log(data)
    }).catch(error => context.setState({messageResult: "error"}))
    .finally(() => {
        context.setState({redirect: "/users?edit=" + context.state.messageResult});
    });
}
