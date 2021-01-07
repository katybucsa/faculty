import {COMPANY_RIDES_URI} from './consts';

function status(response) {
    console.log('response status ' + response.status);
    if (response.status >= 200 && response.status < 300) {
        return Promise.resolve(response)
    } else {
        return Promise.reject(new Error(response.statusText))
    }
}

function json(response) {
    return response.json()
}

export function GetRides() {
    var headers = new Headers();
    headers.append('Accept', 'application/json');
    var myInit = {
        method: 'GET',
        headers: headers,
        mode: 'cors'
    };
    var request = new Request(COMPANY_RIDES_URI, myInit);

    console.log('Before fetch for ' + COMPANY_RIDES_URI)

    return fetch(request)
        .then(status)
        .then(json)
        .then(data => {
            console.log('Request succeeded with JSON response', data);
            return data;
        }).catch(error => {
            console.log('Request failed', error);
            return error;
        });

}

export function DeleteRide(id) {
    console.log('Before fetch delete')
    var myHeaders = new Headers();
    myHeaders.append("Accept", "application/json");

    var antet = {
        method: 'DELETE',
        headers: myHeaders,
        mode: 'cors'
    };

    var rideDelUrl = COMPANY_RIDES_URI + '/' + id;

    return fetch(rideDelUrl, antet)
        .then(status)
        .then(response => {
            console.log('Delete status ' + response.status);
            return response.text();
        }).catch(e => {
            console.log('error ' + e);
            return Promise.reject(e);
        });

}

export function AddRide(ride) {
    console.log('Before fetch post' + JSON.stringify(ride));

    var headers = new Headers();
    headers.append("Accept", "application/json");
    headers.append("Content-Type", "application/json");

    var antet = {
        method: 'POST',
        headers: headers,
        mode: 'cors',
        body: JSON.stringify(ride)
    };

    return fetch(COMPANY_RIDES_URI, antet)
        .then(status)
        .then(response => {
            return response.text();
        }).catch(error => {
            console.log('Request failed', error);
            return Promise.reject(error);
        });


}

