#include <Arduino.h>
#include "./controller/SensorboardController.h"

#define LOOP_INTERVAL 5000

unsigned long last = 0;
unsigned long prev = 0;
MainController* controller;

void setup() {
    controller = new SensorboardController();
}

void loop() {
    last = millis();
    if (last - prev >= LOOP_INTERVAL) {
        controller->execute();
        prev = millis();
    }
}