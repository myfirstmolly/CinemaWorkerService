package com.cinema.worker.model;

import com.cinema.worker.WorkerRequest;
import com.cinema.worker.WorkerResponse;
import lombok.Data;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import java.util.UUID;

@EnableAutoConfiguration
@Entity
@Data
public final class Worker {

    @Id
    private final UUID workerId;

    private String name;
    private String surname;
    private int salary;

    @Enumerated(EnumType.STRING)
    private Position position;

    public Worker() {
        workerId = UUID.randomUUID();
    }

    public Worker(UUID id, String name, String surname, int salary, Position position) {
        workerId = id;
        this.name = name;
        this.surname = surname;
        this.salary = salary;
        this.position = position;
    }

    public static Worker fromWorkerRequest(WorkerRequest workerRequest) {
        return new Worker(UUID.randomUUID(),
                workerRequest.getName(),
                workerRequest.getSurname(),
                (int) workerRequest.getSalary(),
                Position.valueOf(workerRequest.getPosition().toString()));
    }

    public WorkerResponse toWorkerResponse() {
        return WorkerResponse.newBuilder().
                setId(workerId.toString()).
                setName(name).
                setSurname(surname).
                setSalary(salary).
                setPosition(com.cinema.worker.Position.valueOf(position.name())).
                build();
    }

}
