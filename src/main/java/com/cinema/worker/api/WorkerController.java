package com.cinema.worker.api;

import com.cinema.worker.model.Position;
import com.cinema.worker.model.Worker;
import com.cinema.worker.service.WorkerService;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/worker")
@AllArgsConstructor
@NoArgsConstructor
public class WorkerController {

    @Autowired
    private WorkerService workerService;

    @PostMapping
    public Worker addWorker(@RequestBody Worker worker) {
        return workerService.addWorker(worker);
    }

    @GetMapping
    public List<Worker> getAll() {
        return workerService.getAll();
    }

    @GetMapping("{position}")
    public List<Worker> getAllByPosition(@PathVariable(value = "position")
                                                 Position position) {
        return workerService.getWorkersByPosition(position);
    }

    @DeleteMapping("{workerId}")
    public ResponseEntity<Void> deleteById(@PathVariable(value = "workerId") UUID id) {
        workerService.deleteWorkerById(id);
        return ResponseEntity.noContent().build();
    }

}
