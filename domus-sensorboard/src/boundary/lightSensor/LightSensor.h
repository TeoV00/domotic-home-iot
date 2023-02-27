#ifndef __LIGHT_SENSOR__
#define __LIGHT_SENSOR__

/**
 * @brief An "interface" modeling a light sensor. 
 */
class LightSensor {

public:
    /**
     * @brief Reads the value of lightness. 
     * @return int an integer in the range (0, 4096) representing
     * the lightness measured by the sensor.
     */
    virtual int readValue() = 0;

};

#endif