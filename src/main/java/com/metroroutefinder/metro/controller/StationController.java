package com.metroroutefinder.metro.controller;

import com.metroroutefinder.metro.model.LineStation;
import com.metroroutefinder.metro.model.Station;
import com.metroroutefinder.metro.service.StationService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class StationController {

    @Autowired
    private StationService stationService;

    @PostMapping("/lines/{lineId}/stations")
    public ResponseEntity<LineStation> addStationToLine(@PathVariable Long lineId, @RequestBody LineStation station) {
        return ResponseEntity.ok(stationService.addStationToLine(lineId, station));
    }

    @GetMapping("/lines/{lineId}/stations")
    public ResponseEntity<List<Station>> getStationsByLine(@PathVariable Long lineId) {
        return ResponseEntity.ok(stationService.getStationsByLine(lineId));
    }

    @GetMapping("/stations")
    public ResponseEntity<List<Station>> getAllStations() {
        return ResponseEntity.ok(stationService.getAllStations());
    }
}
