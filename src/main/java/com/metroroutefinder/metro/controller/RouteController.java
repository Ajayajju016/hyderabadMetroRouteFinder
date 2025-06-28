package com.metroroutefinder.metro.controller;



import com.metroroutefinder.metro.dto.RouteRequestDTO;
import com.metroroutefinder.metro.dto.RouteResponseDTO;
import com.metroroutefinder.metro.service.RouteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/route")
@RequiredArgsConstructor
public class RouteController {


    @Autowired
    private  RouteService routeService;

    @PostMapping("/find")
    public ResponseEntity<RouteResponseDTO> findRoute(@RequestBody RouteRequestDTO request) {
        return ResponseEntity.ok(routeService.findRoute(request));
    }
}
