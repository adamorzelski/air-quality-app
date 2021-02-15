package com.example.air_quality_app.controller;

import com.example.air_quality_app.model.station.StationDTO;
import com.example.air_quality_app.service.GIOSService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class ViewController {

    private GIOSService giosService;

    @Autowired
    public ViewController(GIOSService giosService) {
        this.giosService = giosService;
    }

    @GetMapping
    public String getIndex(Model model) {

        List<StationDTO> stations = giosService.findAllStations();
        model.addAttribute("stations", stations);

        return "index";
    }
}
