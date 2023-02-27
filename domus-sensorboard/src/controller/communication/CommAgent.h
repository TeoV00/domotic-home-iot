#ifndef __COMM_AGENT__
#define __COMM_AGENT__

/**
 * @brief the Agent, i.e. the active component, which takes care 
 * of all communications between the board and the outside world. 
 */
class CommAgent {

public:
    /**
     * @brief It establishes a new communication channel.
     * @note This is blocking, i.e. until the connection has been 
     * established this function won't return the control.
     */
    virtual void establishCommChannel() = 0;

    /**
     * @brief Send the data over the communication channel.
     * 
     * @param msg the message string to send
     * @return true if the message was sent correctly, false otherwise
     */
    virtual bool sendData(const char* msg) = 0;

};

#endif