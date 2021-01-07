package services.util;


import model.Bilet;
import model.Loc;
import services.IService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class ServerProxy implements IService {
    private String host;
    private int port;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private Socket connection;

    public ServerProxy(String host, int port) {
        this.host = host;
        this.port = port;
    }

    @Override
    public List<Loc> findAllLocuri() {

        initializeConnection();
        Request req = new Request.Builder().type(RequestType.GET_ALL_LOCURI).build();
        sendRequest(req);
        Response response = readResponse();
        if (response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new RuntimeException(err);
        }
        return new ArrayList<>(Arrays.asList((Loc[]) response.data()));
    }

    @Override
    public Bilet buyTicket(int idSpectacol, int idCategorie) {

        initializeConnection();
        String[] strings = new String[2];
        strings[0] = idSpectacol + "";
        strings[1] = idCategorie + "";
        Request req = new Request.Builder().type(RequestType.BUY_TICKET).data(strings).build();
        sendRequest(req);
        Response response = readResponse();
        if (!Objects.isNull(response) && response.type() == ResponseType.ERROR) {
            String err = response.data().toString();
            throw new RuntimeException(err);
        }
        return (Bilet) response.data();
    }

    @Override
    public void makeVerification() {

    }

    private void sendRequest(Request request) {

        try {
            synchronized (output) {
                output.writeObject(request);
                output.flush();
            }
        } catch (IOException e) {
            throw new RuntimeException("Error sending object " + e);
        }

    }

    private Response readResponse() {

        try {
            Object response = input.readObject();
            System.out.println("response received " + response);
            return (Response) response;
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    private void initializeConnection() {

        try {
            connection = new Socket(host, port);
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}