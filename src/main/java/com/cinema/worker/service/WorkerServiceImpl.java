package com.cinema.worker.service;

import com.cinema.worker.model.Position;
import com.cinema.worker.model.Worker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;

@Service
public final class WorkerServiceImpl implements WorkerService {

    @Autowired
    WorkerRepository workerRepository;

    @Override
    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public List<Worker> getAll() {
        return workerRepository.findAll();
    }

    @Override
    public List<Worker> getWorkersByPosition(Position position) {
        return workerRepository.getAllByPosition(position);
    }

    @Override
    public void deleteWorkerById(UUID id) {
        workerRepository.deleteById(id);
    }

}
