import React, {useState} from "react";
import {FormControl, InputLabel, Input, Button, FormHelperText,FormGroup} from "@material-ui/core"
import { useHistory } from "react-router";
import Box from '@mui/material/Box';
function Auth({setCurrentUser}) {
    const [username, setUsername] = useState("")
    const [password, setPassword] = useState("")
    const[counter,setCounter]=useState(0)
    let history = useHistory();
    const handleUsername = (value) => {
        setUsername(value)
    } 

    const handlePassword = (value) => {
        setPassword(value)
    } 

    const sendRequest = (path) => {
      fetch('http://127.0.0.1:8081/auth/login', {
            
        method: 'POST',
        headers: {
          'Content-Type': 'application/json',
        },
        body: JSON.stringify({
          userName: username,
          password: password,
         
        })
  
          })
          .then((res) => res.json())
          .then((result) => {localStorage.setItem("tokenKey",result.message);
                            localStorage.setItem("currentUser",result.userId);
                            localStorage.setItem("userName",username);
                            setCurrentUser(result.userId);                        
                        })
          .catch((err) => {console.log(err);
          setCounter(counter+1);})
    }

    const handleButton = (path) => {
        sendRequest(path)
        setUsername("")
        setPassword("")

    }
    

    return(
        <FormControl>
           
            <InputLabel>Username</InputLabel>
            <Input  onChange = {(i) => handleUsername(i.target.value)}/>
            <InputLabel  style={{top: 80}}>Password</InputLabel>
            <Input  type="password" style={{top: 40}}
            onChange = {(i) => handlePassword(i.target.value)}/>
           
            <FormHelperText style={{margin:20}}></FormHelperText>
            <Button variant = "contained"
                style = {{
                background :'linear-gradient(45deg, #2196F3 30%, #21CBF3 90%)',
                color : 'white'}
                }
                onClick={() => handleButton("login")}>Login</Button>
          {  counter>=3 ?   <Box sx={{ p: 2, bgcolor: 'primary.main', color: 'primary.contrastText'}}  style={{margin:20}}>
          3 kere yanlış deneme yaptınız !!!
      </Box> : ""}
        </FormControl>
    )
}

export default Auth;