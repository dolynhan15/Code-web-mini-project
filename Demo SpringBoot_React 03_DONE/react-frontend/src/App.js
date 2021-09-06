import './App.css';
import ListEmployeeComponent from './components/ListEmployeeComponent';
import CreateEmployeeComponent2 from './components/CreateEmployeeComponent2'
import {BrowserRouter as Router, Route, Switch} from 'react-router-dom';
import HeaderComponent from "./components/HeaderComponent";
import FooterComponent from './components/FooterComponent';
import UpdateEmployeeComponent from './components/UpdateEmployeeComponent';
import ViewEmployeeComponent from './components/ViewEmployeeComponent';

function App() {
  return (
    <div>
      <Router>
      <HeaderComponent />
      <div className="container">
        <Switch> 
          {/* <Route path="/" component={ListEmployeeComponent}></Route> */}
          <Route path="/employees" component={ListEmployeeComponent}></Route>
          
          <Route path="/add-employee/:id" component={CreateEmployeeComponent2}></Route> {/* change 1 */}
          <Route path = "/view-employee/:id" component = {ViewEmployeeComponent}></Route>
          {/* <Route path="/add-employee" component={CreateEmployeeComponent}></Route> */}
          {/* <Route path="/update-employee/:id" component={UpdateEmployeeComponent}></Route> */}
        </Switch>
      </div>
      <FooterComponent />
      </Router>
      </div>
    
  );
}

export default App;
