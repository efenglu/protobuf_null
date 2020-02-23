package io.github.efenglu.protobuf.examples.has;

import io.grpc.stub.StreamObserver;

public class ServerImpl extends MyDataServiceGrpc.MyDataServiceImplBase {
    MyDataRepo repo;
    @Override
    public void update(final UpdateMyDataRequest request, final StreamObserver<UpdateMyDataResponse> responseObserver) {

        // Fetch exiting Values
        MyData existing = repo.readData(request.getId());
        MyData.Builder builder = existing.toBuilder();

        // Update Fields as necessary
        if (request.hasStringValue()) {
            builder.setStringValue(request.getStringValue().getValue());
        }

        if (request.hasSubData()) {
            if (request.getSubData().hasBigValue()) {
                builder.setSubData(
                        builder.getSubData().toBuilder().setBigValue(request.getSubData().getBigValue().getValue())
                );
            }
        }

        repo.writeData(builder.build());

        responseObserver.onNext(UpdateMyDataResponse.newBuilder()
                .setNewData(builder)
                .build());
    }
}
