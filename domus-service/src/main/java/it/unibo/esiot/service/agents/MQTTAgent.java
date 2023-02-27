package it.unibo.esiot.service.agents;

import io.vertx.core.AbstractVerticle;
import io.vertx.core.json.JsonObject;
import io.vertx.mqtt.MqttClient;
import io.vertx.mqtt.messages.MqttPublishMessage;
import it.unibo.esiot.service.commchannel.CommChannel;
import it.unibo.esiot.service.model.SensorData;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/*
 * MQTT Agent
 */
public class MQTTAgent extends AbstractVerticle {

	private static final String TOPIC = "esiot-domus-sensors";
	private final CommChannel channel;
	private final SensorData sensorData;
	
	public MQTTAgent(final CommChannel channel, final SensorData sensorData) {
		this.channel = channel;
		this.sensorData = sensorData;
	}

	@Override
	public void start() {		
		MqttClient client = MqttClient.create(vertx);
		client.connect(1883, "broker.mqtt-dashboard.com", c -> {
			log("connected");
			log("subscribing...");
			client.publishHandler(this::responseHandler)
					.subscribe(TOPIC, 2);
		});
	}

	private void responseHandler(final MqttPublishMessage msg) {
		final String receivedMsg = msg.payload().toString();
		log("New message in [" + msg.topicName() + "]: " + receivedMsg);
		storeDataPoint(msg);
		String json = this.sensorData.getJson(false);
		log("Sending to Arduino the data: "+ json);
		channel.sendMsg(json);
	}

	private void storeDataPoint(final MqttPublishMessage msg) {
		final var now = LocalDateTime.now();
		final var dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
		final String timestamp = now.format(dateTimeFormatter);
		try {
			final JsonObject res = msg.payload().toJsonObject();
			final int temperature = res.getInteger("temperature");
			final int lightness = res.getInteger("light");
			this.sensorData.updateSensorData(lightness, temperature);
		} catch (Exception e) {
			log("Error reading json: ");
			e.printStackTrace();
		}
	}

	private void log(final String msg) {
		System.out.println("[MQTT AGENT] "+msg);
	}

}