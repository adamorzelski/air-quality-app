package com.example.air_quality_app.validator;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationAddressValidatorTest {

    @Test
    void addressValidator_shouldReturnGivenString() {

        //given
        String address = "my address";
        //when
        String result = StationAddressValidator.addressValidator(address);
        //then
        assertEquals(address, result);
    }

    @Test
    void addressValidator_shouldReturnEmptyString() {

        //given
        String address1 = null;
        String address2 = "bez ulicy";
        String address3 = "Brak danych";
        String address4 = "null";
        //when
        String result1 = StationAddressValidator.addressValidator(address1);
        String result2 = StationAddressValidator.addressValidator(address2);
        String result3 = StationAddressValidator.addressValidator(address3);
        String result4 = StationAddressValidator.addressValidator(address4);
        //then
        assertAll(
                () -> assertEquals("", result1),
                () -> assertEquals("", result2),
                () -> assertEquals("", result3),
                () -> assertEquals("", result4)
        );
    }

}
