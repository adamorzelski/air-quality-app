package com.example.air_quality_app.service;

import com.example.air_quality_app.mapper.DataDTOMapper;
import com.example.air_quality_app.mapper.StationDTOMapper;
import com.example.air_quality_app.model.data.Data;
import com.example.air_quality_app.model.data.DataDTO;
import com.example.air_quality_app.model.sensor.Sensor;
import com.example.air_quality_app.model.station.Station;
import com.example.air_quality_app.model.station.StationDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.*;

import static org.springframework.http.HttpStatus.NOT_FOUND;

@Service
public class GIOSService {

    private String stationsUrl;
    private String sensorsUrl;
    private String dataUrl;
    //TODO resttemplate throw exception handle
    private RestTemplate restTemplate;

    public GIOSService() {
    }

    @Autowired
    public GIOSService(RestTemplate restTemplate,
                       @Value("${stationsUrl}") String stationsUrl,
                       @Value("${sensorsUrl}") String sensorsUrl,
                       @Value("${dataUrl}") String dataUrl
    ) {
        this.restTemplate = restTemplate;
        this.stationsUrl = stationsUrl;
        this.sensorsUrl = sensorsUrl;
        this.dataUrl = dataUrl;
    }

    public List<StationDTO> findAllStations() {

        List<StationDTO> resultList = new ArrayList<>();
        ResponseEntity<Station[]> out = restTemplate
                .exchange(stationsUrl, HttpMethod.GET, HttpEntity.EMPTY, Station[].class);


        if(out.getStatusCode().is2xxSuccessful()) {
            List<Station> stationList = Arrays.asList(out.getBody());
            stationList.stream().forEach(station -> {
                resultList.add(StationDTOMapper.map(station));
            });
            //sort list by fullAddress
            resultList.sort((o1, o2) -> o1.getFullAddress().compareTo(o2.getFullAddress()));
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        return resultList;
    }

    public List<DataDTO> findDataByStationId(int stationId) {

        List<Integer> sensorsIdList = findSensorsIdByStationId(stationId);

        List<DataDTO> resultDataDTOList = new LinkedList<>();
        for(Integer id: sensorsIdList) {
            String url = dataUrl + id;
            ResponseEntity<Data> out = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Data.class);
            if(out.getStatusCode().is2xxSuccessful()) {
                resultDataDTOList.add(DataDTOMapper.map(out.getBody()));
            } else {
                throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
            }
        }

        return resultDataDTOList;
    }

    public List<Integer> findSensorsIdByStationId(int stationId) {

        String url = sensorsUrl + stationId;

        ResponseEntity<Sensor[]> out = restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Sensor[].class);
        List<Integer> sensorsIdList = new ArrayList<>();
        if(out.getStatusCode().is2xxSuccessful()) {
            Arrays.stream(out.getBody()).forEach(sensor -> sensorsIdList.add(sensor.getId()));
        } else {
            throw new ResponseStatusException(NOT_FOUND, "Unable to find resource");
        }

        return sensorsIdList;
    }

}














