import React from 'react'

import MenuItem from './MenuItem'
import MenuItemTree from './MenuItemTree'
import MenuTree from './MenuTree'

export default props =>(

    
    <ul className='sidebar-menu' data-widget="tree">
        <li className="header">NAVEGAÇÃO PRINCIPAL</li>
        <MenuItem path='#' label='Home' icon='th'/>


        <MenuTree label='Acesso' icon='edit'>
            <MenuItemTree path='#usuario' label='Usuarios'/>
        </MenuTree>
    </ul>
)

