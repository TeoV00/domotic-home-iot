package it.unibo.esiot.service.model;

public interface SensorData {
    void updateSensorData(final int newExtLight, final int newExtTemp);
    int getExtLight();
    int getExtTemp();
    String getLastUpdateTime();
    String getJson(boolean timestampEnabled);
}
