package com.example.air_quality_app.model.data;

import java.util.List;

public class Data{

    public String key;
    public List<Value> values;

    public Data() {
    }

    public Data(String key, List<Value> values) {
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

    @Override
    public String toString() {
        return "Data{" +
                "key='" + key + '\'' +
                ", values=" + values +
                '}';
    }
}
