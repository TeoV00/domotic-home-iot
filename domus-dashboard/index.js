const URL_HOME_REQUEST = "http://localhost:8080/api/data/home-state";
const URL_SENSORS_REQUEST = "http://localhost:8080/api/data/sensors";

$(document).ready(function () {
    setInterval(function () {
        getSensorsData();
        getHomeStateData();
    }, 1000);
});


function getSensorsData() {
    $.ajax({
        type: "get",
        url: URL_SENSORS_REQUEST,
        dataType: "json",
        success: function (response) {
            $("#temp").text(response["temp"]);
            $("#extLight").text(response["extLight"]);
            $("#lastUpdateSensors").text(response["lastUpdate"]);
        }
    });
}

function getHomeStateData() {
    $.ajax({
        type: "get",
        url: URL_HOME_REQUEST,
        dataType: "json",
        success: function (response) {
            $("#inLight").text(response["inLight"]);
            $("#outLight").text(response["outLight"]);
            $("#alarmState").text(response["alarmState"]);
            $("#heatSysPwr").text(response["heatSysPwr"]);
            $("#heatTemp").text(response["heatTemp"]);
            $("#garageState").text(response["garageState"]);
            $("#lastUpdateHome").text(response["lastUpdate"]);
            $("#btState").text(response["btConnected"] ? "CONNESSA - Controllo manuale": "Disconnessa - Controllo automatico");
        }
    });
}