import React, {Component} from 'react';


class AppLoginComponent extends Component {
    constructor(props) {
        super(props)
        
        this.admUserClicked = this.admUserClicked.bind(this)
        this.triadorClicked = this.triadorClicked.bind(this)
        this.finalizadorClicked = this.finalizadorClicked.bind(this)
    }
    
    admUserClicked(){
        this.props.history.push('/users')
    }

    triadorClicked(){
        this.props.history.push('/process')
    }

    finalizadorClicked(){
        this.props.history.push('/review')
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
                    
                    <button className="btn btn-login" onClick={this.admUserClicked}>Administrador</button>
                    <button className="btn btn-login" onClick={this.triadorClicked}>Triador</button>
                    <button className="btn btn-login" onClick={this.finalizadorClicked}>Finalizador</button> 
                    <hr/>
                </center>
               
            </div>
            
        )
    }
}

export default AppLoginComponent