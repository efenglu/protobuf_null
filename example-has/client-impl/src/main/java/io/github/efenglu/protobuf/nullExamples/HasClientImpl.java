package io.github.efenglu.protobuf.nullExamples;

import com.google.protobuf.StringValue;
import io.github.efenglu.protobuf.examples.has.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.has.UpdateMyDataRequest;

public class HasClientImpl {

    MyDataServiceGrpc.MyDataServiceBlockingStub service;

    void update() {
        service.update(UpdateMyDataRequest.newBuilder()
                .setStringValue(StringValue.of("customValue"))
                .build());
    }
}
