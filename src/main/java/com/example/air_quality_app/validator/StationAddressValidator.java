package com.example.air_quality_app.validator;

public class StationAddressValidator {

    public static String addressValidator(String address) {
        if(address == null || address.equals("bez ulicy") || address.equals("Brak danych") || address.equals("null")) {
            return "";
        }
        return address;
    }
}
