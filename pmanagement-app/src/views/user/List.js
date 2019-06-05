import React, { Component } from 'react';
import axios from 'axios';

export class UserList extends Component {
    constructor(props) {
        super(props)

        this.state = {
            users: []
        };

        axios.get('http://localhost:8080/api/users/all')
            .then(res => {
                const users = res.data;
                this.setState({ users });
            })
    }

    render() {
        return (
            <ul>
                {this.state.persons.map(person => <li>{person.name}</li>)}
            </ul>
        );
    }
}