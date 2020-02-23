package io.github.efenglu.protobuf.nullExamples;

import com.google.protobuf.NullValue;
import io.github.efenglu.protobuf.examples.oneof.MyData;
import io.github.efenglu.protobuf.examples.oneof.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.oneof.NullableString;
import io.github.efenglu.protobuf.examples.oneof.NullableSubData;
import io.github.efenglu.protobuf.examples.oneof.SubData;
import io.github.efenglu.protobuf.examples.oneof.UpdateMyDataRequest;

public class OneOfClientImpl {

    MyDataServiceGrpc.MyDataServiceBlockingStub service;

    public void sendNullValue() {
        UpdateMyDataRequest request = UpdateMyDataRequest.newBuilder()
                .setData(MyData.newBuilder()
                        .setStringValue(NullableString.newBuilder().setNull(NullValue.NULL_VALUE).build())
                        .setSubData(NullableSubData.newBuilder().setNull(NullValue.NULL_VALUE).build())
                        .build())
                .build();

        service.upateMyData(request);
    }

    public void sendValue() {
        UpdateMyDataRequest request = UpdateMyDataRequest.newBuilder()
                .setData(MyData.newBuilder()
                        .setStringValue(NullableString.newBuilder().setData("hello").build())
                        .setSubData(NullableSubData.newBuilder().setData(SubData.newBuilder().setBigValue(1234567).build()).build())
                        .build())
                .build();

        service.upateMyData(request);
    }
}
