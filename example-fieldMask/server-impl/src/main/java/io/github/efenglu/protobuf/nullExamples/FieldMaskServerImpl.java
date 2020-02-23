package io.github.efenglu.protobuf.nullExamples;

import com.google.protobuf.FieldMask;
import com.google.protobuf.util.FieldMaskUtil;
import io.github.efenglu.protobuf.examples.fieldmask.ListMyDataRequest;
import io.github.efenglu.protobuf.examples.fieldmask.ListMyDataResponse;
import io.github.efenglu.protobuf.examples.fieldmask.MyData;
import io.github.efenglu.protobuf.examples.fieldmask.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.fieldmask.UpdateMyDataRequest;
import io.github.efenglu.protobuf.examples.fieldmask.UpdateMyDataResponse;
import io.grpc.stub.StreamObserver;

import java.util.List;

public class FieldMaskServerImpl extends MyDataServiceGrpc.MyDataServiceImplBase {

    MyDataRepo repo;

    @Override
    public void update(
            final UpdateMyDataRequest request,
            final StreamObserver<UpdateMyDataResponse> responseObserver
    ) {

        MyData updateData = request.getUpdate();
        FieldMask fieldMask = request.getFieldMask();

        // Fetch exiting Values
        MyData existing = repo.readData(updateData.getId());
        MyData.Builder builder = existing.toBuilder();

        // Update only the fields listed in the fieldmask
        FieldMaskUtil.merge(fieldMask, updateData, builder);

        // Store the result
        repo.writeData(builder.build());

        // Send the new state back
        responseObserver.onNext(UpdateMyDataResponse.newBuilder()
                .setNewData(builder)
                .build());
    }

    @Override
    public void list(
            final ListMyDataRequest request,
            final StreamObserver<ListMyDataResponse> responseObserver
    ) {
        int id = request.getId();
        FieldMask fieldMask = request.getFieldMask();

        List<MyData> result = repo.listData(id);

        ListMyDataResponse.Builder response = ListMyDataResponse.newBuilder();
        MyData.Builder builder = MyData.newBuilder();
        for (MyData data : result) {
            builder.clear();

            // Use the field mask to send back ONLY the data requested
            FieldMaskUtil.merge(fieldMask, data, builder);

            response.addData(builder);
        }

        // Send the filtered list back
        responseObserver.onNext(response.build());
    }

}