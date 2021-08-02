package org.fangsoft.net.server;

import org.fangsoft.util.SocketUtil;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;

import static org.fangsoft.util.Console_util.output;

public class AdminSocketAcceptor  implements Runnable{
    public static final String EXIT = "exit";
    private int port;
    public AdminSocketAcceptor(int port) {
        this.port=port;
    }


    private ArrayList<SocketAcceptor> acceptorList =
            new ArrayList<SocketAcceptor>();
    public void addAcceptor(SocketAcceptor sa) {
        acceptorList.add(sa);
    }

    public void start() {
        if(acceptorList!=null){
            for(SocketAcceptor sa:acceptorList){
                output(sa.getServerName()+" is starting...");
                sa.start();
                output(sa.getServerName()+" has started");
            }
        }
        new Thread(this).start();
    }

    protected void shutdown() {
        if(acceptorList!=null){
            for (SocketAcceptor sa : acceptorList) {
                output(sa.getServerName()+" is shutdowning...");
                sa.shutdown();
                output(sa.getServerName()+" has shutdown");
            }
        }
    }

    private boolean isShutdownCommand(Socket s) throws IOException {
        BufferedReader br = new BufferedReader(
                new InputStreamReader(s.getInputStream()));
        String input=br.readLine();
        if (EXIT.equalsIgnoreCase(input)) {
            s.shutdownInput();
            return true;
        }
        return false;
    }

    private void sendMessage(Socket s ,String message) throws IOException{
        Writer w=new OutputStreamWriter(s.getOutputStream());
        w.write(message);
        w.flush();
        s.shutdownOutput();
    }

    public static String getEXIT() {
        return EXIT;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }

    public ArrayList<SocketAcceptor> getAcceptorList() {
        return acceptorList;
    }

    public void setAcceptorList(ArrayList<SocketAcceptor> acceptorList) {
        this.acceptorList = acceptorList;
    }

    @Override
    public void run() {
        Socket s =null;
        ServerSocket adminSocket =null;
        try {
            adminSocket = new ServerSocket(this.getPort());
            while (true) {
                s = adminSocket.accept();
                if(isShutdownCommand(s)){
                    this.shutdown();
                    this.sendMessage(s,"server has shutdown");
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }finally{
            SocketUtil.close(s);
            SocketUtil.close(adminSocket);
        }
        System.exit(0);
    }





}
