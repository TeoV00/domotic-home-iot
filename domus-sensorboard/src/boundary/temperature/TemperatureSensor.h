#ifndef __TEMPERATURE_SENSOR__
#define __TEMPERATURE_SENSOR__

/**
 * @brief An "interface" modeling a temperature sensor. 
 */
class TemperatureSensor {

public:
    /**
     * @brief Get the temperature.
     * @return float the temperature, measured in Celsius degrees.
     */
    virtual float readValue() = 0;

};

#endif