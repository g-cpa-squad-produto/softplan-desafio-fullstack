import React, { Component } from 'react'

class TextArea extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: '',
            name: null,
            type: 'text',
            col: 'col-sm-3',
            rows: '5',
            placeholder: 'Descrição...',
            label: 'Enter label property'
        };
    }

    componentDidMount() {
        this.setState((prev, props) => ({
            value: (props.value) ? props.value : prev.value,
            name: (props.name) ? props.name : prev.name,
            type: (props.type) ? props.type : prev.type,
            label: (props.label) ? props.label : prev.label,
            col: (props.col) ? 'col-sm-' + props.col : prev.col
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
                    <textarea
                        value={this.state.value}
                        onChange={e => this.props.change(this.state.name, e.target.value)}
                        name={this.state.name}
                        key={this.state.name}
                        rows={this.state.rows}
                        placeholder={this.state.placeholder}
                        className="form-control">
                    </textarea>
                </div>
            </div>
        )
    }
}

export default TextArea