#include <Arduino.h>
#include <WiFi.h>
#include "MQTTCommAgent.h"

#define CLIENT_ID_PREFIX "esiot-2122-client-"

MQTTCommAgent::MQTTCommAgent(const char* ssid, const char* pwd, 
    const char* serverAddress, const char* topic, const int port) 
    : ssid(ssid), pwd(pwd), serverAddress(serverAddress), topic(topic), port(port) { 
        Serial.begin(115200);
    }

void MQTTCommAgent::establishCommChannel() {
    connectToWifi();
    connectToMQTTServer();
}

void MQTTCommAgent::connectToWifi() {
    Serial.println("[MQTT-AGENT] Establishing wifi-connection");
    WiFi.mode(WIFI_STA);
    WiFi.begin(ssid, pwd);
    while (WiFi.status() != WL_CONNECTED) { }
    Serial.println("[MQTT-AGENT] IP Address: " + WiFi.localIP().toString());
}

void MQTTCommAgent::connectToMQTTServer() {
    client.setClient(espClient);
    client.setServer(serverAddress, port);
    while (!client.connected()) {
        Serial.println("[MQTT-AGENT] Attempting MQTT connection...");
        String clientId = String(CLIENT_ID_PREFIX) + String(random(0xffff), HEX);
        if (client.connect(clientId.c_str())) {
            Serial.println("[MQTT-AGENT] MQTT connected");
        } else {
            Serial.println("[MQTT-AGENT] Failed, rc=" + String(client.state()));
        }
    }
}

bool MQTTCommAgent::sendData(const char* msg) {
    if (client.connected()) {
        Serial.println("[MQTT-AGENT] Sending data: " + String(msg) + " on topic: " + String(topic));
        return client.publish(topic, msg);
    } else {
        return false;
    }
}
