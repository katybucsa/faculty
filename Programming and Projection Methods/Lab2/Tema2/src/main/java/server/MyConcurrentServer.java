package server;

import controller.Controller;
import domain.User;
import repository.RepositoryException;
import service.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class MyConcurrentServer extends ConcurrentServer {
    private Service serv;
    public MyConcurrentServer(int port,Service s) {

        super(port);
        this.serv=s;
    }

    protected Thread createWorker(Socket client){
        Worker worker=new Worker(client);
        Thread tw=new Thread(worker);
        return tw;
    }
    class Worker implements Runnable{
        private Socket client;
        Worker(Socket client) {
            this.client=client;
        }


        public void run() {
            System.out.println("Starting to process request ...");
            try(BufferedReader br=new BufferedReader(new InputStreamReader(client.getInputStream()));
                PrintWriter writer=new PrintWriter(client.getOutputStream())) {


                //read message from client
                String line=br.readLine();

                String[] r=line.split("[|]");

                //send result back to client
                try {
                    User u = serv.findUser(r[0],r[1]);
                    writer.println("ok\n");
                }catch(RepositoryException re){
                    writer.println("Utilizator inexistent!\n");
                }
                writer.flush();

                System.out.println("Finished  processing request ...");
            } catch (IOException e) {
                System.out.println("Error in processing client request "+e);
            }finally {
                try {
                    client.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
