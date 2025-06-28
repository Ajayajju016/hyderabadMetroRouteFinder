package com.metroroutefinder.metro.service;

import com.metroroutefinder.metro.dto.RouteRequestDTO;
import com.metroroutefinder.metro.dto.RouteResponseDTO;
import com.metroroutefinder.metro.model.Station;
import com.metroroutefinder.metro.model.StationConnection;
import com.metroroutefinder.metro.repository.StationConnectionRepository;
import com.metroroutefinder.metro.repository.StationRepository;
import com.metroroutefinder.metro.util.DijkstraAlgorithm;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class RouteService {

    @Autowired
    private StationRepository stationRepository;

    @Autowired
    private StationConnectionRepository connectionRepository;

    public RouteResponseDTO findRoute(RouteRequestDTO request) {
        String sourceName = request.getSource().trim();
        String destinationName = request.getDestination().trim();

        Station source = stationRepository.findByName(sourceName)
                .orElseThrow(() -> new RuntimeException("Source station not found: " + sourceName));
        Station destination = stationRepository.findByName(destinationName)
                .orElseThrow(() -> new RuntimeException("Destination station not found: " + destinationName));

        List<StationConnection> connections = connectionRepository.findAll();

        for (StationConnection c : connections) {
            System.out.println(" - " + c.getFromStation().getName() + " -> " +
                    c.getToStation().getName() + " : " + c.getDistance());
        }

        DijkstraAlgorithm algo = new DijkstraAlgorithm(connections);
        return algo.findShortestPath(source, destination);
    }
}
