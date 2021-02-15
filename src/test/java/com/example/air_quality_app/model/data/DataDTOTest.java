package com.example.air_quality_app.model.data;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DataDTOTest {

    private List<Value> values;

    @BeforeEach
    private void createValuesList() {
        Value value1 = new Value();
        Value value2 = new Value();
        Value value3 = new Value();

        values = Arrays.asList(value1, value2, value3);
    }

    @Test
    void trimmAndsetValues_shouldSetExpectedAmountOfValues() {

        //given
        DataDTO dataDTO = new DataDTO();

        //when
        int expectedAmountOfValues = 2;
        dataDTO.trimmAndsetValues(expectedAmountOfValues, values);

        //then
        assertEquals(expectedAmountOfValues, dataDTO.getValues().size());
    }

    @Test
    void trimmAndsetValues_tooHighExpectedAmountOfValues_shouldSetAvailableAmountOfValues() {

        //given
        DataDTO dataDTO = new DataDTO();

        //when
        int expectedAmountOfValues = 5;
        dataDTO.trimmAndsetValues(expectedAmountOfValues, values);

        //then
        assertEquals(values.size(), dataDTO.getValues().size());
    }
}
