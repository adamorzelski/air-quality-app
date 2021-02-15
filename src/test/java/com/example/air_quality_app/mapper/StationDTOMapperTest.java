package com.example.air_quality_app.mapper;

import com.example.air_quality_app.model.station.City;
import com.example.air_quality_app.model.station.Commune;
import com.example.air_quality_app.model.station.Station;
import com.example.air_quality_app.model.station.StationDTO;
import com.example.air_quality_app.validator.StationAddressValidator;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StationDTOMapperTest {

    @Test
    void map_shouldReturnStationDTO() {

        //given
        Station station = new Station(
                1,
                "stationName",
                "gegrLat",
                "gegrLon",
                new City(1, "cityName", new Commune()),
                "addressStreet"
        );

        //when
        StationDTO stationDTO = StationDTOMapper.map(station);

        //then
        String stationFullAddress = station.city.getName() + " " + StationAddressValidator.addressValidator(station.getAddressStreet());
        assertAll(
                () -> assertEquals(station.getId(), stationDTO.getId()),
                () -> assertEquals(station.getGegrLat(), stationDTO.getGegrLat()),
                () -> assertEquals(station.getGegrLon(), stationDTO.getGegrLon()),
                () -> assertEquals(station.getCity().getName(), stationDTO.getCity()),
                () -> assertEquals(station.getAddressStreet(), stationDTO.getAddressStreet()),
                () -> assertEquals(stationFullAddress, stationDTO.getFullAddress())
        );
    }


}
