package com.metroroutefinder.metro.service;


import com.metroroutefinder.metro.model.MetroLine;
import com.metroroutefinder.metro.repository.MetroLineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class LineService {

    @Autowired
    private  MetroLineRepository lineRepository;

    public MetroLine createLine(MetroLine line) {
        return lineRepository.save(line);
    }

    public List<MetroLine> getAllLines() {
        return lineRepository.findAll();
    }

    public MetroLine getLine(Long id) {
        return lineRepository.findById(id).orElseThrow();
    }

    public MetroLine updateLine(Long id, MetroLine updatedLine) {
        MetroLine line = getLine(id);
        line.setName(updatedLine.getName());
        line.setColor(updatedLine.getColor());
        return lineRepository.save(line);
    }

    public void deleteLine(Long id) {
        lineRepository.deleteById(id);
    }
}
