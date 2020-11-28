package com.cinema.worker.api;

import com.cinema.worker.*;
import com.cinema.worker.model.Worker;
import com.cinema.worker.service.WorkerService;
import io.grpc.stub.StreamObserver;
import org.lognet.springboot.grpc.GRpcService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@GRpcService
public class WorkerGrpcController extends WorkerServiceGrpc.WorkerServiceImplBase {

    @Autowired
    private WorkerService workerService;

    @Override
    public void all(AllWorkersRequest request, StreamObserver<AllWorkersResponse> responseObserver) {
        List<Worker> workers = workerService.getAll();
        List<WorkerResponse> convertedWorkers = workers.stream().
                map(Worker::toWorkerResponse).
                collect(Collectors.toList());
        AllWorkersResponse response = AllWorkersResponse.
                newBuilder().
                addAllWorkers(convertedWorkers).
                build();
        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void add(WorkerRequest request, StreamObserver<WorkerResponse> responseObserver) {
        Worker worker = workerService.addWorker(Worker.fromWorkerRequest(request));
        responseObserver.onNext(worker.toWorkerResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void byId(WorkerByIdRequest request, StreamObserver<WorkerResponse> responseObserver) {
        Worker worker = workerService.getById(UUID.fromString(request.getId()));
        responseObserver.onNext(worker.toWorkerResponse());
        responseObserver.onCompleted();
    }

    @Override
    public void delete(WorkerByIdRequest request, StreamObserver<DeleteWorkerResponse> responseObserver) {
        workerService.deleteWorkerById(UUID.fromString(request.getId()));
        responseObserver.onNext(DeleteWorkerResponse.newBuilder().build());
        responseObserver.onCompleted();
    }
}
