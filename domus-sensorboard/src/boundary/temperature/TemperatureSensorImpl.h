#ifndef __TEMPERATURE_SENSOR_IMPL__
#define __TEMPERATURE_SENSOR_IMPL__

#include "TemperatureSensor.h"

class TemperatureSensorImpl: public TemperatureSensor {

public:
    TemperatureSensorImpl(const int temperatureSensorPin);
    float readValue() override;
    
private:
    int pin;

};

#endif