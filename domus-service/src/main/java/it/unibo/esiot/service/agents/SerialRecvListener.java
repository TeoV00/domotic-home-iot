package it.unibo.esiot.service.agents;

import it.unibo.esiot.service.commchannel.CommChannel;
import it.unibo.esiot.service.model.HomeStateImpl;

import java.util.logging.Logger;

public class SerialRecvListener extends Thread {
    private CommChannel channel;
    private HomeStateImpl homeState;

    public SerialRecvListener(final CommChannel channel, final HomeStateImpl homeState) {
        this.channel = channel;
        this.homeState = homeState;
    }

    @Override
    public void run() {
        while(true) {
            String jsonMsg = "";
            try {
                jsonMsg = this.channel.receiveMsg();
                System.out.println("SERIAL_AGENT: " + jsonMsg);
                //TODO: propbabile race condition
                this.homeState.updateState(jsonMsg);
                //System.out.println("SERIAL_AGENT: " + this.homeState.getJson());
            } catch (InterruptedException exception) {
                System.out.println("SERIAL_AGENT: exception reading json, json message: " + jsonMsg );
            }
        }
    }
}