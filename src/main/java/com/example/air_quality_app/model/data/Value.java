package com.example.air_quality_app.model.data;

public class Value{
    public String date;
    public double value;

    public Value() {
    }

    public Value(String date, double value) {
        this.date = date;
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    @Override
    public String toString() {
        return "Value{" +
                "date='" + date + '\'' +
                ", value=" + value +
                '}';
    }
}
