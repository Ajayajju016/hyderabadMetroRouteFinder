package com.metroroutefinder.metro.repository;


import com.metroroutefinder.metro.model.StationConnection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StationConnectionRepository extends JpaRepository<StationConnection, Long> {
    List<StationConnection> findAll();
}