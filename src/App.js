import './App.css';
import{ BrowserRouter} from "react-router-dom";
import{ Switch,Route,Redirect} from "react-router-dom";
import Navbar from './components/Navbar/Navbar';
import Home from './components/Home/Home';
import User from './components/User/User';
import Auth from './components/Auth/Auth';
import { useState } from 'react';
function App() {
  const [currentUser,setCurrentUser] = useState(localStorage.getItem('currentUser'))
  return (
    <div className="App">
      <BrowserRouter>
        <Navbar/>
        <Switch>
          <Route  exact path="/" component={Home}></Route>
          <Route exact path="/users/:usersId"component={User}></Route>
         
          <Route exact path="/auth">
            { currentUser ? <Redirect to="/"/> : <Auth setCurrentUser={setCurrentUser}/>}
            </Route>
        </Switch>
      </BrowserRouter>
    </div>
  );
}

export default App;
