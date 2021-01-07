import React from 'react';
import RideTable from './Ride';
import './RideApp.css'
import RideForm from "./RideForm";
import {GetRides, DeleteRide, AddRide} from './rest-calls'


class RideApp extends React.Component {
    constructor(props) {
        super(props);
        this.state = {
            //{"destination": "Mardid", "date": "15:06-2019", "hour": "18:45", "places": 18, "id": 5}
            rides: [{
                "destination": "Madrid",
                "date": "15:06-2019",
                "hour": "18:45",
                "places": "0000000000000000000",
                "id": 3
            }],
            deleteFunc: this.deleteFunc.bind(this),
            addFunc: this.addFunc.bind(this),
        };
        console.log('RideApp constructor')
    }

    addFunc(ride) {
        console.log('inside add Func ' + ride);
        AddRide(ride)
            .then(res => GetRides())
            .then(rides => this.setState({rides}))
            .catch(erorr => console.log('eroare add ', erorr));
    }


    deleteFunc(ride) {
        console.log('inside deleteFunc ' + ride);
        DeleteRide(ride)
            .then(res => GetRides())
            .then(rides => this.setState({rides}))
            .catch(error => console.log('eroare delete', error));
    }


    componentDidMount() {
        console.log('inside componentDidMount');
        GetRides().then(rides => this.setState({rides}));
    }

    render() {
        return (
            <div className="RideApp">
                <h1>Curse</h1>
                <RideForm addFunc={this.state.addFunc}/>
                <br/>
                <br/>
                <RideTable rides={this.state.rides} deleteFunc={this.state.deleteFunc}/>
            </div>
        );
    }
}

export default RideApp;