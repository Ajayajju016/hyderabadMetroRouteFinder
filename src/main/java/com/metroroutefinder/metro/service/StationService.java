package com.metroroutefinder.metro.service;

import com.metroroutefinder.metro.repository.StationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;



import com.metroroutefinder.metro.model.LineStation;
import com.metroroutefinder.metro.model.Station;
import com.metroroutefinder.metro.model.MetroLine;
import com.metroroutefinder.metro.repository.LineStationRepository;
import com.metroroutefinder.metro.repository.StationRepository;
import com.metroroutefinder.metro.repository.MetroLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StationService {

    @Autowired
    private  StationRepository stationRepository;
    @Autowired
    private  LineStationRepository lineStationRepository;
    @Autowired
    private  MetroLineRepository lineRepository;

    public LineStation addStationToLine(Long lineId, LineStation lineStation) {
        Station input = lineStation.getStation();

        Station station = stationRepository.findByName(input.getName()).orElse(null);

        if (station == null) {
            station = stationRepository.save(input);
        } else {
            // Update existing station's interchange flag if needed
            if (input.isInterchange() && !station.isInterchange()) {
                station.setInterchange(true);  // explicitly upgrade
                stationRepository.save(station);
            }
        }

        lineStation.setStation(station);
        lineStation.setLine(lineRepository.findById(lineId).orElseThrow());
        return lineStationRepository.save(lineStation);
    }


    public List<Station> getStationsByLine(Long lineId) {
        return lineStationRepository.findByLineIdOrderByStationNumber(lineId)
                .stream()
                .map(LineStation::getStation)
                .toList();
    }

    public List<Station> getAllStations() {
        return stationRepository.findAll();
    }
}
