package com.example.air_quality_app.service;

import com.example.air_quality_app.model.data.Data;
import com.example.air_quality_app.model.data.DataDTO;
import com.example.air_quality_app.model.data.Value;
import com.example.air_quality_app.model.sensor.Sensor;
import com.example.air_quality_app.model.station.City;
import com.example.air_quality_app.model.station.Commune;
import com.example.air_quality_app.model.station.Station;
import com.example.air_quality_app.model.station.StationDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.*;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.*;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.spy;

@ExtendWith(MockitoExtension.class)
class GIOSServiceTest {

    @Mock
    private RestTemplate restTemplate;

    @Spy
    private GIOSService giosService;

    @BeforeEach
    void setUp() {
        giosService = new GIOSService(
                restTemplate,
                "http://api.gios.gov.pl/pjp-api/rest/station/findAll",
                "http://api.gios.gov.pl/pjp-api/rest/station/sensors/",
                "http://api.gios.gov.pl/pjp-api/rest/data/getData/"
        );

        GIOSService GIOSServiceSpy = spy(giosService);
        this.giosService = GIOSServiceSpy;
    }

    @Test
    void findAllStations_httpStatusOK_shouldReturnSortedStationDTOList(){

        //given
        String url= "http://api.gios.gov.pl/pjp-api/rest/station/findAll";

        Station station = new Station(
                1,
                "stationName",
                "gegrLat",
                "gegrLon",
                new City(1, "cityName", new Commune()),
                "addressStreet"
        );

        Station station2 = new Station(
                2,
                "stationName2",
                "gegrLat2",
                "gegrLon2",
                new City(2, "a_cityName2", new Commune()),
                "addressStreet2"
        );

        Station[] serverResponseData = new Station[2];
        serverResponseData[0] = station;
        serverResponseData[1] = station2;

        Mockito
                .when(restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Station[].class))
                .thenReturn(new ResponseEntity<Station[]>(serverResponseData, HttpStatus.OK));

        //when
        List<StationDTO> result = giosService.findAllStations();

        //then
        Mockito.verify(restTemplate).exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Station[].class);
        assertEquals(serverResponseData.length, result.size());
        //Check if sorted by fullAddress (city name + street)
        assertEquals(station2.getCity().getName(), result.get(0).getCity());

    }

    @Test
    void findAllStations_httpStatusOtherThanOK_shouldThrowResponseStatusException(){

        //given
        String url= "http://api.gios.gov.pl/pjp-api/rest/station/findAll";

        Mockito
                .when(restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Station[].class))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        //when + then
        assertThrows(ResponseStatusException.class, () -> giosService.findAllStations());
        Mockito.verify(restTemplate).exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Station[].class);

    }

    @Test
    void findSensorsIdByStationId_httpStatusOK_shouldReturnSensorsIdList() {

        //given
        int stationId = 1;
        String url= "http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + stationId;

        Sensor sensor1 = new Sensor();
        sensor1.setId(1);

        Sensor sensor2 = new Sensor();
        sensor2.setId(1);

        Sensor[] serverResponseData = new Sensor[2];
        serverResponseData[0] = sensor1;
        serverResponseData[1] = sensor2;

        Mockito
                .when(restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Sensor[].class))
                .thenReturn(new ResponseEntity<Sensor[]>(serverResponseData, HttpStatus.OK));

        //when
        List<Integer> resultSensorsIdList = giosService.findSensorsIdByStationId(stationId);

        //then
        Mockito.verify(restTemplate).exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Sensor[].class);
        assertEquals(serverResponseData.length, resultSensorsIdList.size());

    }

    @Test
    void findSensorsIdByStationId_httpStatusOtherThanOK_shouldThrowResponseStatusException() {

        //given
        int stationId = 1;
        String url= "http://api.gios.gov.pl/pjp-api/rest/station/sensors/" + stationId;

        Mockito
                .when(restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Sensor[].class))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        //when + then
        assertThrows(ResponseStatusException.class, () -> giosService.findSensorsIdByStationId(stationId));
        Mockito.verify(restTemplate).exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Sensor[].class);

    }

    @Test
    void findDataByStationId_httpStatusOK_shouldReturnDataDTOList() {

        //given
        int stationId = 1;

        List<Integer> sensorsIds = Arrays.asList(4);
        String url = "http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensorsIds.get(0);
        Mockito
                .doReturn(sensorsIds)
                .when(giosService).findSensorsIdByStationId(stationId);


        Data serverResponseData = new Data("key", Arrays.asList(new Value("date", 11.11), new Value("date2", 22.22)));
        Mockito
                .when(restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Data.class))
                .thenReturn(new ResponseEntity<Data>(serverResponseData, HttpStatus.OK));

        //when
        List<DataDTO> resultDataDTOList = giosService.findDataByStationId(stationId);

        //then
        Mockito.verify(giosService).findSensorsIdByStationId(stationId);
        assertEquals(serverResponseData.getKey(), resultDataDTOList.get(0).getKey());
        assertEquals(serverResponseData.getValues().size(), resultDataDTOList.get(0).getValues().size());

    }

    @Test
    void findDataByStationId_httpStatusOtherThanOK_shouldThrowResponseStatusException() {

        //given
        int stationId = 1;

        List<Integer> sensorsIds = Arrays.asList(4);
        String url = "http://api.gios.gov.pl/pjp-api/rest/data/getData/" + sensorsIds.get(0);
        Mockito
                .doReturn(sensorsIds)
                .when(giosService).findSensorsIdByStationId(stationId);

        Mockito
                .when(restTemplate.exchange(url, HttpMethod.GET, HttpEntity.EMPTY, Data.class))
                .thenReturn(new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR));

        //when + then
        assertThrows(ResponseStatusException.class, () -> giosService.findDataByStationId(stationId));
        Mockito.verify(giosService).findSensorsIdByStationId(stationId);

    }
}



























