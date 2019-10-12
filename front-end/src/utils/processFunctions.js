import {manipulateData} from "./api";

export function removeProcess(id,context) {
    let data = {
        url: '/processes/' + id,
        method: 'DELETE'
    };
    manipulateData(data).then(() => {
        let updatedProcess = [...context.state.process].filter(i => i.id !== id);
        context.setState({process: updatedProcess});
    });
}

export function getProcessesByUser(userId, context) {
    let data = {
        url: '/processes/user/' + userId,
        method: 'GET'
    };
    manipulateData(data).then(data => {
        return context.setState({process: data, isLoading: false})
    });
}

export function getInfoAboutProcess(idProcess, context) {
    let data = {
        url: "/processes/" + idProcess,
        method: "GET"
    };
    manipulateData(data).then(data => {
        data.opinions.map(opinion => {
            //CASO FINZALIZADOR, COLOCAR PARECER DELE NO TEXTAREA
            if (context.state.currentUser.type === 'FINALIZADOR') {
                let idUsuario = opinion.userSystem.id;
                if (idUsuario === context.state.currentUser.id) {
                    return context.setState({opinionText: opinion.text})
                }
            }
            return null;
        });
        return context.setState({item: data, isLoading: false})
    });
}

export function saveProcess(item, context) {
    if (!item.creator) item.creator = {};
    if (item.type) item.type = item.type.toUpperCase();
    if (!context.state.editando) item.creator.id = context.state.currentUser.id;

    let endpoint = '/processes';
    if (item && item.id) {
        endpoint += "/" + item.id;
    }

    let data = {
        url: endpoint,
        method: (item.id) ? 'PUT' : 'POST',
        body: JSON.stringify(item)
    }

    manipulateData(data).then(data => {
        if (!data.success) {
            context.setState({messageResult: "error"});
        }
    }).catch(error => context.setState({messageResult: "error"}))
        .finally(() => {
            context.setState({redirect: "/process?edit=" + context.state.messageResult});
        });
}

export function saveOpinion(opinionToSave, idProcess, context) {
    const data = {
        url: '/opinions/' + opinionToSave.id + '/process/' + idProcess,
        method: 'PUT',
        body: JSON.stringify(opinionToSave)
    }

    manipulateData(data).then(data => {
        console.log(data)
    }).catch(error => context.setState({messageResult: "error"}))
        .finally(() => {
            context.setState({redirect: "/process?edit=" + context.state.messageResult});
        });
}
