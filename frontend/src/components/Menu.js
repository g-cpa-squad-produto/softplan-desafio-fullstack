import React from 'react'

import MenuItem from './MenuItem'
import MenuItemTree from './MenuItemTree'
import MenuTree from './MenuTree'

export default props =>(

    
    <ul className='sidebar-menu' data-widget="tree">
        <MenuItem path='#' label='Home' icon='th'/>


        <MenuTree label='Acesso' icon='edit'>
            <MenuItemTree path='#usuario' label='Usuarios'/>
        </MenuTree>

        <MenuTree label='Processo' icon='edit'>
            <MenuItemTree path='#processo' label='Processo'/>
        </MenuTree>

    </ul>
)

