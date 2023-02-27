#ifndef __MQTT_AGENT__
#define __MQTT_AGENT__

#include <WiFi.h>
#include <PubSubClient.h>
#include "CommAgent.h"

/**
 * @brief The concrete MQTT agent.
 */
class MQTTCommAgent: public CommAgent {

public:
    MQTTCommAgent(const char* ssid, const char* pwd, const char* serverAddress, 
        const char* topic, const int port);
    void establishCommChannel() override;
    bool sendData(const char* msg) override;

private:
    WiFiClient espClient;
    PubSubClient client;
    const char* ssid;
    const char* pwd;
    const char* serverAddress;
    const char* topic;
    const int port;
    void connectToWifi();
    void connectToMQTTServer();

};

#endif