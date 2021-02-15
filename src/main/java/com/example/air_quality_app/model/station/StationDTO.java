package com.example.air_quality_app.model.station;

import com.example.air_quality_app.validator.StationAddressValidator;

public class StationDTO {
    public int id;
    public String gegrLat;
    public String gegrLon;
    public String city;
    public String addressStreet;
    public String fullAddress;

    public StationDTO() {
    }

    public StationDTO(int id, String gegrLat, String gegrLon, String city, String addressStreet) {
        this.id = id;
        this.gegrLat = gegrLat;
        this.gegrLon = gegrLon;
        this.city = city;
        this.addressStreet = addressStreet;
        this.fullAddress = city + " " + StationAddressValidator.addressValidator(addressStreet);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getGegrLat() {
        return gegrLat;
    }

    public void setGegrLat(String gegrLat) {
        this.gegrLat = gegrLat;
    }

    public String getGegrLon() {
        return gegrLon;
    }

    public void setGegrLon(String gegrLon) {
        this.gegrLon = gegrLon;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getAddressStreet() {
        return addressStreet;
    }

    public void setAddressStreet(String addressStreet) {
        this.addressStreet = addressStreet;
    }

    public String getFullAddress() {
        return fullAddress;
    }

    public void setFullAddress(String fullAddress) {
        this.fullAddress = fullAddress;
    }

    @Override
    public String toString() {
        return "StationDTO{" +
                "id=" + id +
                ", gegrLat='" + gegrLat + '\'' +
                ", gegrLon='" + gegrLon + '\'' +
                ", city='" + city + '\'' +
                ", addressStreet='" + addressStreet + '\'' +
                ", fullAddress='" + fullAddress + '\'' +
                '}';
    }
}



