#include <Arduino.h>
#include "SensorboardController.h"
#include "setup.h"
#include "communication/MQTTCommAgent.h"
#include "../boundary/lightSensor/Photoresistor.h"
#include "../boundary/temperature/TemperatureSensorImpl.h"

#define MAPPED_TEMPERATURE_LEVELS 5
#define MAPPED_LIGHTNESS_LEVELS 8
#define LIGHTNESS_LEVELS 4096
#define MAX_TEMPERATURE 40

SensorboardController::SensorboardController() {
    comm = new MQTTCommAgent(SSID_WIFI, PWD, MQTT_SERVER, TOPIC, MQTT_PORT);
    comm->establishCommChannel();
    temperatureSensor = new TemperatureSensorImpl(TEMPERATURE_SENSOR_PIN);
    lightSensor = new Photoresistor(PHOTORESISTOR_PIN);
}

void SensorboardController::execute() {
    readSensors();
    sendData(data);
}

void SensorboardController::readSensors() {
    StaticJsonDocument<256> doc;
    doc["temperature"] = map(
        temperatureSensor->readValue(), 0, MAX_TEMPERATURE, 1, MAPPED_TEMPERATURE_LEVELS + 1
    );
    doc["light"] = lightSensor->readValue();
    serializeJson(doc, data);
}

void SensorboardController::sendData(const char* data) {
    comm->sendData(data);
}
