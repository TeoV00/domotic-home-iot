#ifndef __GARDEN_CONTROLLER__
#define __GARDEN_CONTROLLER__

#include <ArduinoJson.h>
#include "MainController.h"
#include "./communication/CommAgent.h"
#include "../boundary/lightSensor/LightSensor.h"
#include "../boundary/temperature/TemperatureSensor.h"

class SensorboardController: public MainController {

public:
    SensorboardController();
    void execute() override;

private:
    char data[256];
    CommAgent* comm;
    void readSensors();
    void sendData(const char* data);
    LightSensor* lightSensor;
    TemperatureSensor* temperatureSensor;
};

#endif