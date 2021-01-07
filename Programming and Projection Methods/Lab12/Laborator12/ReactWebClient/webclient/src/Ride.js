import React from 'react';
import './RideApp.css'

class RideRow extends React.Component {
    handleClicke = (event) => {
        console.log('delete button pentru ' + this.props.ride.id);
        this.props.deleteFunc(this.props.ride.id);
    };

    render() {
        return (
            <tr>
                <td>{this.props.ride.destination}</td>
                <td>{this.props.ride.date}</td>
                <td>{this.props.ride.hour}</td>
                <td>{this.props.ride.places.split("0").length - 2}</td>
                <td>
                    <button onClick={this.handleClicke}>Sterge</button>
                </td>
            </tr>
        );
    }
}

class RideTable extends React.Component {
    render() {
        var rows = [];
        var delFunction = this.props.deleteFunc;
        this.props.rides.forEach(function (ride) {
            console.log("ride:" + ride);
            rows.push(<RideRow ride={ride} key={ride.id} deleteFunc={delFunction}/>);
        });
        return (<div className="RideTable">

                <table className="center">
                    <thead>
                    <tr>
                        <th>Destinatia</th>
                        <th>Data</th>
                        <th>Ora</th>
                        <th>Nr. locuri disponibile</th>

                        <th></th>
                    </tr>
                    </thead>
                    <tbody>{rows}</tbody>
                </table>

            </div>
        );
    }
}

export default RideTable;