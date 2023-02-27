package it.unibo.esiot.service.model;

import org.json.JSONObject;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SensorDataImpl implements SensorData{
    private int externalLight;
    private int temperature;
    private String lastUpdateTime;

    public SensorDataImpl() {
        this.temperature = 0;
        this.externalLight = 0;
        final var dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        this.lastUpdateTime = LocalDateTime.now().format(dateTimeFormatter);
    }

    @Override
    public void updateSensorData(int newExtLight, int newExtTemp) {
        this.temperature = newExtTemp;
        this.externalLight = newExtLight;
        final var dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
        this.lastUpdateTime = LocalDateTime.now().format(dateTimeFormatter);
    }

    @Override
    public int getExtLight() {
        return this.externalLight;
    }

    @Override
    public int getExtTemp() {
        return this.temperature;
    }

    @Override
    public String getLastUpdateTime() {
        return this.lastUpdateTime;
    }

    public String getJson(boolean timestampEnabled) {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("extLight",this.externalLight);
        jsonObject.put("temp",this.temperature);
        if (timestampEnabled) {
            jsonObject.put("lastUpdate", this.lastUpdateTime);
        }
        return jsonObject.toString();
    }
}
