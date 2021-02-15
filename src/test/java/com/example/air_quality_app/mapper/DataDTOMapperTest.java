package com.example.air_quality_app.mapper;

import com.example.air_quality_app.model.data.Data;
import com.example.air_quality_app.model.data.DataDTO;
import com.example.air_quality_app.model.data.Value;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataDTOMapperTest {

    @Test
    void map_shouldReturnDataDTO() {

        //given
        List<Value> values = Arrays.asList(new Value(), new Value());
        Data data = new Data("key", values);

        //when
        DataDTO dataDTO = DataDTOMapper.map(data);

        //then
        assertAll(
                () -> assertEquals(data.getKey(), dataDTO.getKey()),
                () -> assertEquals(data.getValues().size(), dataDTO.getValues().size())
        );
    }

    @Test
    void map_shouldChangeKey() {

        //given
        List<Value> values = Arrays.asList(new Value(), new Value());
        String keyToChange = "PM2.5";
        Data data = new Data(keyToChange, values);

        //when
        DataDTO dataDTO = DataDTOMapper.map(data);

        //then
        String expectedKey = "PM2";
        assertEquals(expectedKey, dataDTO.getKey());
    }

    @Test
    void map_shouldTrimValuesAndReturn10Values() {

        //given
        List<Value> values = new ArrayList<>(11);
        for(int i = 0 ; i < 11; i++) {
            values.add(null);
        }

        Data data = new Data("key", values);

        //when
        DataDTO dataDTO = DataDTOMapper.map(data);

        //then
        assertEquals(10, dataDTO.getValues().size());


    }
}
