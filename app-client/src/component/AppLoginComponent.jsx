import React, {Component} from 'react'


class AppLoginComponent extends Component {

    constructor(props) {
        super(props)

        this.admUserClicked = this.admUserClicked.bind(this)
    }

    admUserClicked(){
        // console.log('admUser clicked')
        this.props.history.push('/users')
    }

    render() {
        return (
            
            <div className="container">
                <center>
                    <h1>Desafio App</h1>
                    <hr/>
                    <h2>Login</h2>
                    {/* Login ainda não implemtado por não fazer parte dos requisitos do desafio */}
                    
                    <p>Escolha um dos perfis abaixo:</p>
                    
                    <button className="btn btn-info" onClick={this.admUserClicked}>Administrador</button>

                    <hr/>
                </center>
               
            </div>
            
        )
    }
}

export default AppLoginComponent