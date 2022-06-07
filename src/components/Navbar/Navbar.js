import { makeStyles } from "@material-ui/core";
import React from "react";
import {Link,useHistory} from "react-router-dom";
import AppBar from '@material-ui/core/AppBar';
import Toolbar from '@material-ui/core/Toolbar';
import Typography from '@material-ui/core/Typography';
import IconButton from '@material-ui/core/IconButton';
import MenuIcon from '@material-ui/icons/Menu';
import { LockOpen } from "@material-ui/icons";
const useStyles = makeStyles((theme) => ({
  root: {
    flexGrow: 1,
  },
  menuButton: {
    marginRight: theme.spacing(2),
  },
  title: {
    flexGrow: 1,
    textAlign:"left"
  },link:{
      textDecoration:"none",
      boxShadow:"none",
      color:"white"

  }
}));
function Navbar(){
  let history = useHistory();
  const onClick = () => {
    localStorage.removeItem("tokenKey")
    localStorage.removeItem("currentUser")
    localStorage.removeItem("userName")
    history.go(0)
  }

    const classes=useStyles();
return(
    <div>
        <AppBar position="static">
  <Toolbar>
    <IconButton edge="start" className={classes.menuButton} color="inherit" aria-label="menu">
      <MenuIcon />
    </IconButton>
    <Typography variant="h6" className={classes.title}>
    <Link className={classes.link} to="/">Home</Link>
    </Typography>
    <Typography variant="h6">
            {localStorage.getItem("currentUser") == null ? <Link  className={classes.link} to="/auth">Login</Link>:
             <div><IconButton className={classes.link} onClick = {onClick}><LockOpen></LockOpen></IconButton>
            <Link  className={classes.link} to={{pathname : '/' + localStorage.getItem("currentUser")}} >Log out</Link>
            </div>}
    </Typography>
  </Toolbar>
</AppBar>
        
    </div>
)

}
export default Navbar;