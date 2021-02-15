package com.example.air_quality_app.mapper;

import com.example.air_quality_app.model.data.Data;
import com.example.air_quality_app.model.data.DataDTO;

public class DataDTOMapper {

    public static DataDTO map(Data data) {

        DataDTO result = new DataDTO();
        if(data.getKey().equals("PM2.5")) {
            result.setKey("PM2");
        } else {
            result.setKey(data.getKey());
        }
        result.trimmAndsetValues(10, data.getValues());
        return result;
    }


}
