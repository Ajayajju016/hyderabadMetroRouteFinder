package com.metroroutefinder.metro.repository;


import com.metroroutefinder.metro.model.MetroLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MetroLineRepository extends JpaRepository<MetroLine, Long> {}


