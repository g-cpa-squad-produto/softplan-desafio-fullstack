import React, { Component } from 'react'

class Input extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: '',
            name: null,
            type: 'text',
            col: 'col-sm-3',
            label: 'Enter label property',
            min: 0
        };
    }

    componentDidMount() {
        this.setState((prev, props) => ({
            value: (props.value) ? props.value : prev.value,
            name: (props.name) ? props.name : prev.name,
            type: (props.type) ? props.type : prev.type,
            label: (props.label) ? props.label : prev.label,
            col: (props.col) ? 'col-sm-' + props.col : prev.col,
            min: (props.min) ? 'col-sm-' + props.min : prev.min
        }))
    }

    componentWillReceiveProps(props) {
        this.setState({
            value: props.value
        })
    }

    render() {
        return (
            <div className={this.state.col}>
                <div className="form-group">
                    <label>{this.state.label}</label>
                    <input
                        min={this.state.min}
                        type={this.state.type}
                        className="form-control"
                        key={this.state.name}
                        name={this.state.name}
                        value={this.state.value}
                        onChange={e => this.props.change(this.state.name, e.target.value)}
                    />
                </div>
            </div>
        )
    }
}

export default Input