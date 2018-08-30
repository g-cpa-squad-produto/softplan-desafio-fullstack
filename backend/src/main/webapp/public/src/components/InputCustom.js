import React, {Component} from 'react';
import PubSub from 'pubsub-js';

export default class InputCustom extends Component {

    constructor() {
        super();
        this.state = {msgErro: ''};
    }

    componentDidMount() {
        
        PubSub.subscribe('errors-form-autores', function(topic, error) {
            if (error.field === this.props.name) {
                this.setState({msgErro: error.defaultMessage});
            }
        }.bind(this));

        PubSub.subscribe('clean-errors-form-autores', function(topic) {
            this.setState({msgErro: ''});
        }.bind(this));
    }

    render() {
        return (
            <div className="pure-control-group">
                <label htmlFor={this.props.id}>{this.props.label}</label> 
                <input {...this.props} />
                <span className="error">
                    {this.state.msgErro}
                </span>
            </div>
        )
    }
}