package com.metroroutefinder.metro.repository;


import com.metroroutefinder.metro.model.LineStation;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LineStationRepository extends JpaRepository<LineStation, Long> {
    List<LineStation> findByLineIdOrderByStationNumber(Long lineId);
}

