package services.util;

import services.IService;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * Created on: 19-Dec-19, 22:05
 *
 * @author: Katy Bucșa
 */

public class ConcurrentServer {

    private int serverPort;
    private ServerSocket serverSocket;
    private IService server;
    private ExecutorService threadPool = Executors.newFixedThreadPool(8);


    public ConcurrentServer(int serverPort, IService server) {

        this.serverPort = serverPort;
        this.server = server;
    }

    public void start() throws ServerException {

        try {
            serverSocket = new ServerSocket(serverPort);
            threadPool.execute(new PrinterThread());
            while (true) {
                System.out.println("Waiting for clients ...");
                Socket client = this.serverSocket.accept();
                System.out.println("Client connected ...");
                processRequest(client);
            }
        } catch (IOException e) {
            throw new ServerException("Starting serverSocket error ", e);
        } finally {
            try {
                serverSocket.close();
            } catch (IOException e) {
                throw new ServerException("Closing serverSocket error ", e);
            }
        }
    }

    protected void processRequest(Socket client) {

        Future<Response> responseFuture = threadPool.submit(new ClientWorker(server, client));
        new Thread(new SenderThread(responseFuture, client)).start();
    }

    private class PrinterThread implements Runnable {

        @Override
        public void run() {
            while (true) {
                try {
                    Thread.sleep(15000);
                    server.makeVerification();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    private class SenderThread implements Runnable {

        private Future<Response> response;
        private Socket connection;
        private ObjectOutputStream output;

        public SenderThread(Future<Response> response, Socket connection) {

            this.response = response;
            this.connection = connection;
            try {
                output = new ObjectOutputStream(connection.getOutputStream());
                output.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        @Override
        public void run() {

            try {
                System.out.println("sending response " + response);
                synchronized (output) {
                    Response r = response.get();
                    output.writeObject(r);
                    output.flush();
                    output.close();
                    connection.close();
                }
            } catch (IOException | InterruptedException | ExecutionException e) {
                System.out.println("Exception at sending response!");
            }
        }
    }
}