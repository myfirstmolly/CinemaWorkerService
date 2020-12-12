package com.cinema.worker.rabbitmq;

import com.cinema.worker.model.Worker;
import com.cinema.worker.service.WorkerService;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class Consumer {

    @Autowired
    WorkerService workerService;

    @RabbitListener(queues = "${rabbitmq.queue}")
    public void consume(Worker worker) {
        workerService.addWorker(worker);
    }
}
