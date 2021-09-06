import { useParams } from 'react-router';
import React, { Component, useEffect, useState } from 'react';
import { useHistory } from 'react-router';
import EmployeeService from '../services/EmployeeService';


const CreateEmployeeComponent2 = () => {
    const {id} = useParams();
    const [firstName, setFirstName] = useState('');
    const [lastName, setLastName] = useState('');
    const [emailId, setEmailId] = useState('');

    const history = useHistory();
    
    useEffect(() => {
            if (id==='_add') {
                return;
            } else {
                EmployeeService.getEmployeeById(id).then(res => {
                    let employee = res.data;
                    console.log(employee);
                    setFirstName(employee.firstname);
                    setLastName(employee.lastname);
                    setEmailId(employee.emailId);
                })
            }
        }, []);
        
    const changeFirstNameHandler= (event) => {
        setFirstName(event.target.value);
    }

    const changeLastNameHandler= (event) => {
        setLastName(event.target.value);
    }

    const changeEmailHandler = (event) => {
        setEmailId(event.target.value);
    }

    const orUpdate = (e) => {
        e.preventDefault();
        let employee = {firstName: firstName, lastName: lastName, emailId: emailId};
        console.log('employee => '+ JSON.stringify(employee));

        //change 5
        if (id === '_add') {
            EmployeeService.createEmployee(employee).then(res => {
                history.push('/employees')
            })
        } else {
            EmployeeService.updateEmployee(id,employee).then(res => {
                history.push('/employees')
            })
        }

       
    };
    const cancel=()=>{
        history.push('/employees');
    }
    const getTitle=()=>{
        // console.log("ID",id);
        // return <h3 className="text-center">Add Employee</h3>
        /*
        if(id === '_add'){
            console.log("ADD");
            // return <h3 className="text-center">Add Employee</h3>
        } else {
            console.log("UPDATE");
            // return <h3 className="text-center">Update Employee</h3>
        }
        */
    }
    return (
        <div>
            <div className = "container">
                <div className = "row">
                    <div className = "card col-md-6 offset-md-3 offset-md-3">
                            {()=>getTitle}
                        <div className="card-body">
                            <form>
                                <div className = "form-group">
                                    <label> First Name: </label>
                                    <input placeholder="First Name" name="firstName" className="form-control" 
                                        value={firstName} onChange={changeFirstNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label> Last Name: </label>
                                    <input placeholder="Last Name" name="lastName" className="form-control" 
                                        value={lastName} onChange={changeLastNameHandler}/>
                                </div>
                                <div className = "form-group">
                                    <label> Email Id: </label>
                                    <input placeholder="Email Address" name="emailId" className="form-control" 
                                        value={emailId} onChange={changeEmailHandler}/>
                                </div>
                                <button className="btn btn-success" onClick={orUpdate}>Save</button>
                                <button className="btn btn-danger" onClick={cancel} style={{marginLeft: "10px"}}>Cancel</button>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    );
}

export default CreateEmployeeComponent2;

