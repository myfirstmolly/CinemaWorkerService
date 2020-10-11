package com.cinema.worker.service;

import com.cinema.worker.model.Position;
import com.cinema.worker.model.Worker;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface WorkerRepository extends JpaRepository<Worker, UUID> {
    List<Worker> getAllByPosition(Position position);
}
