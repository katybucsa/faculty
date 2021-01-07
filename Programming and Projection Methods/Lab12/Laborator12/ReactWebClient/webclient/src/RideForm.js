import React from 'react';
import './RideApp.css'

class RideForm extends React.Component {

    constructor(props) {
        super(props);
        this.state = {destination: '', date: '', hour: ''};
    }

    handleDestinationChange = (event) => {
        this.setState({destination: event.target.value});
    };

    handleDateChange = (event) => {
        this.setState({date: event.target.value});
    };

    handleHourChange = (event) => {
        this.setState({hour: event.target.value});
    };
    handleSubmit = (event) => {

        var ride = {
            destination: this.state.destination,
            date: this.state.date,
            hour: this.state.hour
        };
        console.log(ride);
        this.props.addFunc(ride);
        event.preventDefault();
    };

    render() {
        return (
            <form id="rideForm" onSubmit={this.handleSubmit}>
                <label>
                    Destinatia:&nbsp;&nbsp;
                    <input type="text" value={this.state.destination} onChange={this.handleDestinationChange}/>
                </label><br/><br/>
                <label>
                    Data:&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                    <input type="text" value={this.state.date} onChange={this.handleDateChange}/>
                </label><br/><br/>
                <label>
                    Ora plecare:
                    <input type="text" value={this.state.hour} onChange={this.handleHourChange}/>
                </label><br/><br/>
                <input type="submit" value="Adauga" style = {{color:'purple'}}/>
            </form>
        );
    }
}

export default RideForm;