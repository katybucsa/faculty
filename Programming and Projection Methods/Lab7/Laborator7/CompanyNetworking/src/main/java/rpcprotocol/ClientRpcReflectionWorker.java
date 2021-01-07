package rpcprotocol;


import company.model.Client;
import company.model.RBooking;
import company.model.Ride;
import company.model.User;
import company.services.AppException;
import company.services.IObserver;
import company.services.IServer;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;


public class ClientRpcReflectionWorker implements Runnable, IObserver {
    private static Response okResponse = new Response.Builder().type(ResponseType.OK).build();
    private IServer server;
    private Socket connection;
    private ObjectInputStream input;
    private ObjectOutputStream output;
    private volatile boolean connected;

    public ClientRpcReflectionWorker(IServer server, Socket connection) {
        this.server = server;
        this.connection = connection;
        try {
            output = new ObjectOutputStream(connection.getOutputStream());
            output.flush();
            input = new ObjectInputStream(connection.getInputStream());
            connected = true;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void run() {
        while (connected) {
            try {
                Object request = input.readObject();
                Response response = handleRequest((Request) request);
                if (response != null) {
                    sendResponse(response);
                }
            } catch (IOException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        try {
            input.close();
            output.close();
            connection.close();
        } catch (IOException e) {
            System.out.println("Error " + e);
        }
    }

    //  private static Response errorResponse=new Response.Builder().type(ResponseType.ERROR).build();
    private Response handleRequest(Request request) {
        Response response = null;
        String handlerName = "handle" + (request).type();
        System.out.println("HandlerName " + handlerName);
        try {
            Method method = this.getClass().getDeclaredMethod(handlerName, Request.class);
            response = (Response) method.invoke(this, request);
            System.out.println("Method " + handlerName + " invoked");
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return response;
    }

    private Response handleLOGIN(Request request) {
        System.out.println("Login request ..." + request.type());
        User user = (User) request.data();
        //User user = DTOUtils.getFromDTO(udto);
        try {
            server.login(user, this);
            return okResponse;
        } catch (AppException e) {
            connected = false;
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleLOGOUT(Request request) {
        System.out.println("Logout request...");
        User user = (User) request.data();
        //User user = DTOUtils.getFromDTO(udto);
        try {
            server.logout(user, this);
            connected = false;
            return okResponse;

        } catch (AppException e) {
            return new Response.Builder().type(ResponseType.ERROR).data(e.getMessage()).build();
        }
    }

    private Response handleGET_ALL_RIDES(Request request) {
        System.out.println("GetAllRides Request ...");
        try {
            Ride[] rides = server.findAllRides();
            return new Response.Builder().type(ResponseType.GET_ALL_RIDES).data(rides).build();
        } catch (AppException ae) {
            return new Response.Builder().type(ResponseType.ERROR).data(ae.getMessage()).build();
        }
    }

    private Response handleGET_ALL_RBOOKINGS(Request request){
        System.out.println("GetAllRBookings ...");
        String[] strs=(String[])request.data();
        try{
            RBooking[] rBookings=server.findAllRBookings(strs[0],strs[1],strs[2]);
            return new Response.Builder().type(ResponseType.GET_ALL_RBOOKINGS).data(rBookings).build();
        }catch (AppException ae){
            return new Response.Builder().type(ResponseType.ERROR).data(ae.getMessage()).build();
        }
    }

    private Response handleBOOK_PLACES(Request request) {
        System.out.println("BookPlaces Request ...");
        Object[] objs=(Object[])request.data();
        Ride r=(Ride)objs[0];
        Client c=(Client)objs[1];
        int nrplaces=(int)objs[2];
        try {
            String places = server.bookPlaces(r,c,nrplaces);
            return new Response.Builder().type(ResponseType.BOOKED_PLACES).data(places).build();
        } catch (AppException ae) {
            return new Response.Builder().type(ResponseType.ERROR).data(ae.getMessage()).build();
        }
    }

    private void sendResponse(Response response) {
        try {
            System.out.println("sending response " + response);
            synchronized (output) {
                output.writeObject(response);
                output.flush();
            }
        }catch(IOException e){
            System.out.println("Exception at sending response!");
        }
    }


    @Override
    public void update() {
        sendResponse(new Response.Builder().type(ResponseType.UPDATE).build());
    }
}
