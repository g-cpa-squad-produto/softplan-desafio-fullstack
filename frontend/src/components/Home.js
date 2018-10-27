import React, {Component} from 'react'

import ContentHeader from './ContentHeader'
import Content from './Content'


class Home extends Component{
    render(){
        return(
            <div>
                <ContentHeader title="Home" subTitle="Versão 1.0"/>
                <Content >
                    
                </Content>
            </div>
        )
    }
}

export default Home;