#include <Arduino.h>
#include "TemperatureSensorImpl.h"

#define POWER_SUPPLY 5.0
#define ADC_RESOLUTION 4096.0
#define VOLTAGE_ZERO_LEVEL 0.4
#define STEP 100

TemperatureSensorImpl::TemperatureSensorImpl(const int temperatureSensorPin) {
    this->pin = temperatureSensorPin;
}

float TemperatureSensorImpl::readValue() {
    int adcValue = analogRead(pin);
    float mVValue = adcValue * (POWER_SUPPLY / ADC_RESOLUTION);
    return (mVValue - VOLTAGE_ZERO_LEVEL) * STEP;
}
