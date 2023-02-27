package it.unibo.esiot.service.model;

import org.json.JSONException;
import org.json.JSONObject;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class HomeStateImpl {

    private PowerState inLight; //indoor lights
    private int outLight; //outdoor light
    private AlarmState alarmState; //alarm system power state
    private PowerState heatSysPwr; //heating system power
    private int heatTemp; // selected heating system temperature
    private GarageState garageState;
    private boolean btConnected;
    private String timestamp;

    public HomeStateImpl() {
        this.inLight = PowerState.OFF;
        this.outLight = 0;
        this.alarmState = AlarmState.OFF;
        this.heatSysPwr = PowerState.OFF;
        this.heatTemp = 0;
        this.garageState = GarageState.CLOSE;
        this.btConnected = false;
        this.timestamp = "";
    }

    public void updateState(final String jsonHomeState) {
        try {
            JSONObject jsonObject = new JSONObject(jsonHomeState);
            this.inLight = PowerState.getFromValue(jsonObject.getInt("iL"));
            this.outLight = jsonObject.getInt("oL");
            this.alarmState = AlarmState.getFromValue(jsonObject.getInt("alarm"));
            this.heatSysPwr = PowerState.getFromValue(jsonObject.getInt("hP"));
            this.heatTemp = jsonObject.getInt("hT");
            this.garageState = GarageState.getFromValue(jsonObject.getInt("gar"));
            this.btConnected = jsonObject.getInt("bt") == 1;
            final var now = LocalDateTime.now();
            final var dateTimeFormatter = DateTimeFormatter.ofPattern("yy-MM-dd HH:mm:ss");
            this.timestamp = now.format(dateTimeFormatter);
        } catch (JSONException exception) {
            System.out.println("HOME_STATE: " + exception);
        }

    }

    public String getJson() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("inLight",this.inLight.getDescr());
        jsonObject.put("outLight",this.outLight);
        jsonObject.put("alarmState",this.alarmState.getDescr());
        jsonObject.put("heatSysPwr",this.heatSysPwr.getDescr());
        jsonObject.put("heatTemp",this.heatTemp);
        jsonObject.put("garageState",this.garageState.getDescr());
        jsonObject.put("btConnected",this.btConnected);
        jsonObject.put("lastUpdate", this.timestamp);

        String json = jsonObject.toString();
        return json;
    }
}
