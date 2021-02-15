package com.example.air_quality_app.controller;

import com.example.air_quality_app.model.data.DataDTO;
import com.example.air_quality_app.service.GIOSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/api")
public class DataRestController {

    private GIOSService giosService;

    @Autowired
    public DataRestController(GIOSService giosService) {
        this.giosService = giosService;
    }

    @GetMapping("/{id}")
    public List<DataDTO> getStationData(@PathVariable int id) {
        return giosService.findDataByStationId(id);
    }

}
