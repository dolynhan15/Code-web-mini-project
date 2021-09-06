import React, { Component } from 'react';
import EmployeeService from '../services/EmployeeService';

class CreateEmployeeComponent extends Component {
    constructor(props) {
        super(props)
        this.state = {
            //change 2
            id: this.props.match.params.id, //get from url
            firstName: '',
            lastName: '',
            emailId: ''
        }
        this.changeFirstNameHandler = this.changeFirstNameHandler.bind(this);
        this.changeLastNameHandler = this.changeLastNameHandler.bind(this);
        this.saveorUpdateEmployee = this.orUpdate.bind(this);
    }
    //change 3
    componentDidMount(){

        //change 4
        if (this.state.id === '_add') {
            return;
        } else {
            EmployeeService.getEmployeeById(this.state.id).then(res => {
                let employee = res.data;
                console.log(employee);
                this.setState({
                    firstName: employee.firstname,
                    lastName: employee.lastname,
                    emailId: employee.emailId
                })
            })
        }        
    }

    changeFirstNameHandler= (event) => {
        this.setState({firstName: event.target.value});
    }

    changeLastNameHandler= (event) => {
        this.setState({lastName: event.target.value});
    }

    changeEmailHandler = (event) => {
        this.setState({emailId: event.target.value});
    }
    orUpdate = (e) => {
        e.preventDefault();
        let employee = {firstname: this.state.firstName, lastname: this.state.lastName, emailId: this.state.emailId};
        console.log('employee => '+ JSON.stringify(employee));

        //change 5
        if (this.state.id === '_add') {
            EmployeeService.createEmployee(employee).then(res => {
                this.props.history.push("/employees")
            })
        } else {
            EmployeeService.updateEmployee(this.state.id,employee).then(res => {
                this.props.history.push("/employees")
            })
        }

       
    }
    cancel(){
        this.props.history.push('/employees');
    }
    getTitle(){
        if(this.state.id === '_add'){
            return <h3 className="text-center">Add Employee</h3>
        } else {
            return <h3 className="text-center">Update Employee</h3>
        }
    }
    render() {
        return (
            <div>
                 <div className = "container">
                    <div className = "row">
                        <div className = "card col-md-6 offset-md-3 offset-md-3">
                            {this.getTitle()}
                            <div className="card-body">
                                <form>
                                <div className = "form-group">
                                            <label> First Name: </label>
                                            <input placeholder="First Name" name="firstName" className="form-control" 
                                                value={this.state.firstName} onChange={this.changeFirstNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Last Name: </label>
                                            <input placeholder="Last Name" name="lastName" className="form-control" 
                                                value={this.state.lastName} onChange={this.changeLastNameHandler}/>
                                        </div>
                                        <div className = "form-group">
                                            <label> Email Id: </label>
                                            <input placeholder="Email Address" name="emailId" className="form-control" 
                                                value={this.state.emailId} onChange={this.changeEmailHandler}/>
                                        </div>
                                        <button className="btn btn-success" onClick={this.orUpdate}>Save</button>
                                        <button className="btn btn-danger" onClick={this.cancel.bind(this)} style={{marginLeft: "10px"}}>Cancel</button>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        );
    }
}

export default CreateEmployeeComponent;