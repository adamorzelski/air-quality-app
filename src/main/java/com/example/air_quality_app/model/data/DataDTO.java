package com.example.air_quality_app.model.data;

import java.util.List;

public class DataDTO {

    public String key;
    public List<Value> values;

    public DataDTO() {
    }

    public DataDTO(String key, List<Value> values) {
        this.key = key;
        this.values = values;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public List<Value> getValues() {
        return values;
    }

    public void setValues(List<Value> values) {
        this.values = values;
    }

    public void trimmAndsetValues(int amountOfValues, List<Value> values) {
        if(values.size() >= amountOfValues) {
            this.values = values.subList(0, amountOfValues);
        } else {
            this.values = values;
        }
    }

    @Override
    public String toString() {
        return "DataDTO{" +
                "key='" + key + '\'' +
                ", values=" + values +
                '}';
    }
}
