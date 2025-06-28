package com.metroroutefinder.metro.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import com.metroroutefinder.metro.model.MetroLine;
import com.metroroutefinder.metro.service.LineService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

        import java.util.List;

@RestController
@RequestMapping("/api/lines")
@RequiredArgsConstructor
public class LineController {

    @Autowired
    private  LineService lineService;


    @PostMapping
    public ResponseEntity<MetroLine> createLine(@RequestBody MetroLine line) {
        return ResponseEntity.ok(lineService.createLine(line));
    }

    @GetMapping
    public ResponseEntity<List<MetroLine>> getAllLines() {
        return ResponseEntity.ok(lineService.getAllLines());
    }

    @GetMapping("/{lineId}")
    public ResponseEntity<MetroLine> getLine(@PathVariable Long lineId) {
        return ResponseEntity.ok(lineService.getLine(lineId));
    }

    @PutMapping("/{lineId}")
    public ResponseEntity<MetroLine> updateLine(@PathVariable Long lineId, @RequestBody MetroLine line) {
        return ResponseEntity.ok(lineService.updateLine(lineId, line));
    }

    @DeleteMapping("/{lineId}")
    public ResponseEntity<Void> deleteLine(@PathVariable Long lineId) {
        lineService.deleteLine(lineId);
        return ResponseEntity.noContent().build();
    }
}
