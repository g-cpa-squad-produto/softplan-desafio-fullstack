import React, {Component} from 'react'
import Menu from './Menu'
import PanelUser from './PanelUser'

export default class SideBar extends Component {
    render(){
        return (
            <aside className="main-sidebar">
                <section className="sidebar">
                   <PanelUser userDesc="Jean" image="img/user2-160x160.jpg" />
                   <Menu />
                </section>
            </aside> 
        )
    }
}