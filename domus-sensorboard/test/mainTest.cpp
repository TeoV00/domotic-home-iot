#include <Arduino.h>
#include <unity.h>

void test_string_concat(void) {
    String hello = "Hello, ";
    String world = "world!";
    TEST_ASSERT_EQUAL_STRING("Hello, world!", (hello + world).c_str());
}

void setup() {
    // NOTE!!! Wait for >2 secs
    // if board doesn't support software reset via Serial.DTR/RTS
    delay(2000);
    UNITY_BEGIN(); // IMPORTANT LINE!
    RUN_TEST(test_string_concat);
}

void loop() {

}