import React from 'react'
import If from '../operators/If'

export default props => (
    <If test={!props.hide}>
        <button className={'btn btn-'+ props.styleButton} 
            onClick={props.onClick}>
            <i className={'fa fa-'+ props.icon}></i>
            {props.label}
        </button>
    </If>
)