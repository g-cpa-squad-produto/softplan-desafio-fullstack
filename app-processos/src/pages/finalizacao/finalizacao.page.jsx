import React, { Component } from 'react';
import { Link } from 'react-router-dom';
import Moment from 'react-moment';

import { ApplicationContext } from '../../components/application-context';
import { ProcessoAtribuicaoService } from '../../services/processo-atribuicao.service'

class FinalizacaoPage extends Component {

    constructor(props) {
        super(props);

        this.state = {
            user: ApplicationContext.getUser(),
            items: [],
            isLoaded: false
        }
    }

    componentDidMount() {
        const { user } = this.state;
        ProcessoAtribuicaoService.getByUsuario(user.id)
            .then(res => res.json())
            .then(json => {
                this.setState({
                    items: json.content,
                    isLoaded: true
                })
            });
    }

    render() {
        var { user, items, isLoaded } = this.state;

        if (!isLoaded) {
            return <div>Loading...</div>
        } else {
            return(
                <div className="card">
                    <div className="card-header">
                        <strong>Painel do Finalizador</strong>
                    </div>
                    <div className="card-body">
                            <table className="table table-sm table-bordered table-striped table-hover">
                            <thead>
                                <tr>
                                    <th>Processo</th>
                                    <th>Súmula</th>
                                    <th>Abertura</th>
                                    <th>Atribuição</th>
                                </tr>
                            </thead>
                            <tbody>
                            {items.map(item => (
                                <tr key={item.idAtribuicao}>
                                    <td>
                                        {item.idProcesso}
                                    </td>
                                    <td>
                                        {item.sumula}
                                    </td>
                                    <td>
                                        <Moment format="DD/MM/YYYY HH:mm" date={item.dhAbertura} />
                                    </td>
                                    <td>
                                        <Moment format="DD/MM/YYYY HH:mm" date={item.dhAtribuicao} />
                                    </td>
                                </tr>
                            ))}
                            </tbody>
                        </table>
                    </div>
                </div>
            );
        }
    }

}

export { FinalizacaoPage };