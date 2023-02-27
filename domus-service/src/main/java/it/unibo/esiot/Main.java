package it.unibo.esiot;

import io.vertx.core.Vertx;
import it.unibo.esiot.service.agents.HTTPServerAgent;
import it.unibo.esiot.service.agents.MQTTAgent;
import it.unibo.esiot.service.agents.SerialRecvListener;
import it.unibo.esiot.service.commchannel.CommChannel;
import it.unibo.esiot.service.commchannel.SerialCommChannel;
import it.unibo.esiot.service.model.HomeStateImpl;
import it.unibo.esiot.service.model.SensorData;
import it.unibo.esiot.service.model.SensorDataImpl;

public class Main {
    private final static int BAUD_RATE_SERIAL = 9600;
    private final static int DEFAULT_PORT_NUMBER_SERIAL = 21401;
    private final static int HTTP_PORT = 8080;

    public static void main(String[] args) throws Exception {
        int portNum= DEFAULT_PORT_NUMBER_SERIAL;
        if (args.length != 0) {
            portNum = Integer.parseInt(args[0]);
        }
        CommChannel channel = new SerialCommChannel("/dev/tty.usbmodem"+portNum, BAUD_RATE_SERIAL);
        HomeStateImpl homeState = new HomeStateImpl();
        SensorData sensorData = new SensorDataImpl();

        Vertx vertx = Vertx.vertx();
        HTTPServerAgent httpServerAgent = new HTTPServerAgent(8080, homeState, sensorData);
        vertx.deployVerticle(httpServerAgent);

        MQTTAgent mqttAgent = new MQTTAgent(channel, sensorData);
        vertx.deployVerticle(mqttAgent);

        SerialRecvListener serialAgent = new SerialRecvListener(channel, homeState);
        serialAgent.start();

    }
}
