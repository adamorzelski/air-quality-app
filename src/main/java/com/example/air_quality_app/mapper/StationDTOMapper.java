package com.example.air_quality_app.mapper;

import com.example.air_quality_app.model.station.Station;
import com.example.air_quality_app.model.station.StationDTO;

public class StationDTOMapper {

    public static StationDTO map(Station station) {
        StationDTO stationDTO = new StationDTO(station.getId(), station.gegrLat, station.gegrLon, station.getCity().getName(), station.getAddressStreet());
        return stationDTO;
    }
}
