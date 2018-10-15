import React, { Component } from 'react'
import Select from 'react-select';
import 'react-select/dist/react-select.css';

class SelectMultiple extends Component {
    constructor(props) {
        super(props);
        this.state = {
            value: {},
            name: null,
            col: 'col-sm-3',
            label: '',
            options: [],
            description: 'description',
            placeholder: ''
        };

        this.convertToSelect2 = this.convertToSelect2.bind(this);
    }

    componentDidMount() {
        this.setState((prev, props) => ({
            name: (props.name) ? props.name : prev.name,
            value: (props.value) ? props.value : prev.value,
            label: (props.label) ? props.label : prev.label,
            col: (props.col) ? 'col-sm-' + props.col : prev.col,
            options: (props.options) ? props.options : prev.options,
            description: (props.description) ? props.description : prev.description,
            placeholder: (props.placeholder) ? props.placeholder : prev.placeholder
        }))
    }

    componentWillReceiveProps() {
        this.setState((prev, nextProps) => ({
            name: (nextProps.name) ? nextProps.name : prev.name,
            value: (nextProps.value) ? nextProps.value : prev.value,
            label: (nextProps.label) ? nextProps.label : prev.label,
            col: (nextProps.col) ? 'col-sm-' + nextProps.col : prev.col,
            options: (nextProps.options) ? nextProps.options : prev.options,
            description: (nextProps.description) ? nextProps.description : prev.description,
            placeholder: (nextProps.placeholder) ? nextProps.placeholder : prev.placeholder
        }))
    }

    convertToSelect2() {
        return this.state.options.map((option, key) => {
            return {
                value: option.id,
                label: option[this.state.description]
            }
        });
    }

    render() {
        return (
            <div className={this.state.col}>
                <div className="form-group">
                    <label>{this.state.label}</label>
                    <Select
                        multi={this.props.multi}
                        name={this.state.name}
                        value={this.state.value}
                        onChange={this.props.change}
                        options={this.convertToSelect2()}
                        placeholder= {this.state.placeholder}
                        style={{ height: 'calc(2.25rem + 2px)', borderRadius: 2 }}
                    />
                </div>
            </div>
        )
    }
}

export default SelectMultiple