package com.share.memory.jgroup_demo;

import org.jgroups.JChannel;
import org.jgroups.Message;
import org.jgroups.ReceiverAdapter;
import org.jgroups.View;

import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * author : fanzhe
 * email : fansy1990@foxmail.com
 * date : 2019/10/24 PM10:20.
 */
public class SimpleChat3 extends ReceiverAdapter {
    JChannel channel;
    String user_name=System.getProperty("user.name", "n/a");


    public static void main(String[] args) throws Exception {
        new SimpleChat3().start();
    }

    private void start() throws Exception {
        channel=new JChannel("tcp_3.xml").setReceiver(this);
        channel.connect("ChatCluster");
        eventLoop();
        channel.close();
    }
    public void viewAccepted(View new_view) {
        System.out.println("** view: " + new_view);
    }

    public void receive(Message msg) {
        System.out.println(msg.getSrc() + ": " + msg.getObject());
    }
    private void eventLoop() {
        BufferedReader in=new BufferedReader(new InputStreamReader(System.in));
        while(true) {
            try {
                System.out.print("> "); System.out.flush();
                String line=in.readLine().toLowerCase();
                if(line.startsWith("quit") || line.startsWith("exit"))
                    break;
                line="[" + user_name + "] " + line;
                Message msg=new Message(null, line);
                channel.send(msg);
            }
            catch(Exception e) {
            }
        }
    }
}