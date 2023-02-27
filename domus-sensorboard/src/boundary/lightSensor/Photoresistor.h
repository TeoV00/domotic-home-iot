#ifndef __PHOTORESISTOR__
#define __PHOTORESISTOR__

#include "LightSensor.h"

class Photoresistor: public LightSensor {

public:
    Photoresistor(const int photoresistorPin);
    int readValue() override;

private:
    int pin;

};

#endif