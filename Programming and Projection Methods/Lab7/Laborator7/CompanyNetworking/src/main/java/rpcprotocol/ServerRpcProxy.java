package rpcprotocol;


import company.model.Client;
import company.model.RBooking;
import company.model.Ride;
import company.model.User;
import company.services.AppException;
import company.services.IObserver;
import company.services.IServer;
import javafx.application.Platform;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;


public class ServerRpcProxy implements IServer {
    private String host;
    private int port;

    private IObserver client;

    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    private BlockingQueue<Response> qresponses;
    private volatile boolean finished;

    public ServerRpcProxy(String host, int port) {
        this.host = host;
        this.port = port;
        qresponses = new LinkedBlockingQueue<Response>();
    }

    public void login(User user, IObserver client) throws AppException {
        initializeConnection();
        Request req = new Request.Builder().type(RequestType.LOGIN).data(user).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.OK) {
            this.client = client;
            return;
        }
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            closeConnection();
            throw new AppException(err);
        }
    }

    public Ride[] findAllRides() {
        Request req = new Request.Builder().type(RequestType.GET_ALL_RIDES).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppException(err);
        }
        Ride[] rides = (Ride[]) response.data();
        return rides;
    }

    @Override
    public String bookPlaces(Ride ride, Client client, int nrplaces) {
        Object[] objs = new Object[3];
        objs[0] = ride;
        objs[1] = client;
        objs[2] = nrplaces;
        Request request = new Request.Builder().type(RequestType.BOOK_PLACES).data(objs).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppException(err);
        }
        String places = (String) response.data();
        return places;
    }

    public void logout(User user, IObserver client) {
        Request req = new Request.Builder().type(RequestType.LOGOUT).data(user).build();
        sendRequest(req);
        Response response = readResponse();
        closeConnection();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppException(err);
        }
    }

    @Override
    public RBooking[] findAllRBookings(String dest, String date, String hour) {
        String[] strs = new String[3];
        strs[0] = dest;
        strs[1] = date;
        strs[2] = hour;
        Request request = new Request.Builder().type(RequestType.GET_ALL_RBOOKINGS).data(strs).build();
        sendRequest(request);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new AppException(err);
        }
        RBooking[] rbs = (RBooking[]) response.data();
        return rbs;
    }


    private void closeConnection() {
        finished = true;
        try {
            input.close();
            output.close();
            connection.close();
            client = null;
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void sendRequest(Request request) {
        try {
            synchronized (output) {
                output.writeObject(request);
                output.flush();
            }
        } catch (IOException e) {
            throw new AppException("Error sending object " + e);
        }

    }

    private Response readResponse() {
        Response response = null;
        try {
            response = qresponses.take();

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return response;
    }

    private void initializeConnection() {
        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            finished = false;
            startReader();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void startReader() {
        Thread tw = new Thread(new ReaderThread());
        tw.start();
    }

    private void handleUpdate(Response response) {
        client.update();
    }


    private boolean isUpdate(Response response) {
        return response.type() == ResponseType.UPDATE;
    }

    @Override
    public void notifyObservers() {

    }

    private class ReaderThread implements Runnable {

        public void run() {
            while (!finished) {
                try {
                    Object response = input.readObject();
                    System.out.println("response received " + response);
                    if (isUpdate((Response) response)) {
                        handleUpdate((Response) response);
                    } else {
                        try {
                            qresponses.put((Response) response);
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                } catch (IOException e) {
                    System.out.println("Reading error " + e);
                } catch (ClassNotFoundException e) {
                    System.out.println("Reading error " + e);
                }
            }
        }
    }
}
