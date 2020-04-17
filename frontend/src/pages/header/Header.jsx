import React, { Component } from 'react'
import { FontAwesomeIcon } from '@fortawesome/react-fontawesome'
import { faSignOutAlt } from '@fortawesome/free-solid-svg-icons'
import { Link } from 'react-router-dom'
import './Header.css'

export default class Header extends Component {
    render() {
        return (
            <div className="header-container">
                <h4 className="col welcome-label">{JSON.parse(sessionStorage.session_data).role}</h4>
                <div className="col float-right">
                    <Link to="/" className="close">
                        <FontAwesomeIcon icon={faSignOutAlt} className="float-right mr-10" size="lg" color="white"/>
                    </Link>
                </div>
            </div>
        )
    }
}