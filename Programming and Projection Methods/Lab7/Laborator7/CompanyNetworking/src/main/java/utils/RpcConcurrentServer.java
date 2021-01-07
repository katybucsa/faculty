package utils;

import company.services.IServer;
import rpcprotocol.ClientRpcReflectionWorker;

import java.net.Socket;

public class RpcConcurrentServer extends AbstractConcurrentServer {
    private IServer server;

    public RpcConcurrentServer(int port, IServer server) {
        super(port);
        this.server = server;
        System.out.println("Chat- ChatRpcConcurrentServer");
    }

    @Override
    protected Thread createWorker(Socket client) {
         //ClientRpcWorker worker=new ClientRpcWorker(server, client);
        ClientRpcReflectionWorker worker = new ClientRpcReflectionWorker(server, client);

        Thread tw = new Thread(worker);
        return tw;
    }
}
