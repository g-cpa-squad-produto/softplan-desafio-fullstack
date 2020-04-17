import React, { Component } from 'react'
import './Header.css'

export default class Header extends Component {

    render() {
        return (
            <div className="header-container">
                <h4 className="welcome-label">{JSON.parse(sessionStorage.session_data).role}</h4>
                
            </div>
        )
    }
}