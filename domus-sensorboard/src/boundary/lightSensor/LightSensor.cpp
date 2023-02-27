#include <Arduino.h>
#include "Photoresistor.h"

Photoresistor::Photoresistor(const int photoresistorPin) {
    this->pin = photoresistorPin;
}

int Photoresistor::readValue() {
    return analogRead(this->pin);
}
