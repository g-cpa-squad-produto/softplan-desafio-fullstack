import React from 'react'

export default props =>(
    <div className="user-panel">
        <div className="pull-left image">
            <img src={props.image} className="img-circle" alt="User" />
        </div>
        <div className="pull-left info">
            <p>{props.userDesc}</p>
        </div>
    </div>
)
