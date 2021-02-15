package com.example.air_quality_app.model.sensor;

public class Sensor{
    public int id;
    public int stationId;
    public Param param;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStationId() {
        return stationId;
    }

    public void setStationId(int stationId) {
        this.stationId = stationId;
    }

    public Param getParam() {
        return param;
    }

    public void setParam(Param param) {
        this.param = param;
    }

    @Override
    public String toString() {
        return "Sensor{" +
                "id=" + id +
                ", stationId=" + stationId +
                ", param=" + param +
                '}';
    }
}
