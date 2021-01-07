package services.util;


import model.Bilet;
import model.Loc;
import services.IService;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;
import java.util.List;
import java.util.concurrent.Callable;


public class ClientWorker implements Callable<Response> {
    private IService server;
    private ObjectInputStream input;

    public ClientWorker(IService server, Socket connection) {
        this.server = server;
        try {
            input = new ObjectInputStream(connection.getInputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Response call() throws Exception {
        Object request = input.readObject();
        return handleRequest((Request) request);
    }

    private Response handleRequest(Request request) {

        Response response = null;
        String handlerName = "handle" + (request).type();
        System.out.println("HandlerName " + handlerName);
        try {
            Method method = this.getClass().getDeclaredMethod(handlerName, Request.class);
            response = (Response) method.invoke(this, request);
            System.out.println("Method " + handlerName + " invoked");
        } catch (NoSuchMethodException | InvocationTargetException | IllegalAccessException e) {
            e.printStackTrace();
        }
        return response;
    }

    private Response handleGET_ALL_LOCURI(Request request) {
        System.out.println("GetAllLocuri Request ...");
        try {
            List<Loc> locList = server.findAllLocuri();
            Loc[] locs = locList.toArray(new Loc[locList.size()]);
            return new Response.Builder().type(ResponseType.GET_ALL_LOCURI).data(locs).build();
        } catch (Exception ae) {
            return new Response.Builder().type(ResponseType.ERROR).data(ae.getMessage()).build();
        }
    }

    private Response handleBUY_TICKET(Request request) {
        System.out.println("BuyTicket Request ...");
        try {
            String[] s = (String[]) request.data();
            Bilet b = server.buyTicket(Integer.parseInt(s[0]), Integer.parseInt(s[1]));
            return new Response.Builder().type(ResponseType.BUY_TICKET).data(b).build();
        } catch (Exception ae) {
            return new Response.Builder().type(ResponseType.ERROR).data(ae.getMessage()).build();
        }
    }
}