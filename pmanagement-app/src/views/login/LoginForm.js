import React, { useState } from 'react';
import axios from 'axios';
import Button from '@material-ui/core/Button';
import TextField from '@material-ui/core/TextField';
import { ThemeProvider } from '@material-ui/styles';
import { createMuiTheme } from '@material-ui/core/styles';
import green from '@material-ui/core/colors/green';

function LoginForm(props) {
    const [login, setLogin] = useState('');
    const [password, setPassword] = useState('');
    const theme = createMuiTheme({
        palette: {
            primary: green,
        },
    });

    function handleSubmit(event) {
        event.preventDefault();

        axios.post('http://localhost:8080/api/login?username=' + login + "&password=" + password)
                .then((result) => {

                    console.log('====================================');
                    console.log(result);
                    console.log('====================================');
                    console.log(result.headers['Authentication'])
                })
                .catch((err) =>
                    console.error(err)
                );
    }

    return (
            <form>            
                <ThemeProvider theme={theme}>
                    <TextField 
                        placeholder="Username" 
                        label="Username" 
                        type="text" 
                        value={login} 
                        onChange={(e) => setLogin(e.target.value)} 
                        name="login" 
                        margin="normal"
                        variant="outlined"
                        id="custom-css-outlined-input" />
                    <TextField 
                        placeholder="Password" 
                        label="Password" 
                        type="password" 
                        margin="normal"
                        value={password} 
                        onChange={(e) => setPassword(e.target.value)} 
                        variant="outlined"
                        id="custom-css-outlined-input"
                        name="password" />
                </ThemeProvider>
                <Button 
                    variant="contained" 
                    color="primary" 
                    margin="normal"
                    type="button" 
                    onClick={(e) => handleSubmit(e)}>Sign in</Button>
            </form>
            );
}

export default LoginForm;