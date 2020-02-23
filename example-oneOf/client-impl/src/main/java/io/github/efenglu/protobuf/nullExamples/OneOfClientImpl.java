package io.github.efenglu.protobuf.nullExamples;

import com.google.protobuf.NullValue;
import io.github.efenglu.protobuf.examples.MyData;
import io.github.efenglu.protobuf.examples.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.NullableString;
import io.github.efenglu.protobuf.examples.NullableSubData;
import io.github.efenglu.protobuf.examples.SubData;
import io.github.efenglu.protobuf.examples.UpdateMyDataRequest;

public class OneOfClientImpl {

    public void sendNullValue(MyDataServiceGrpc.MyDataServiceFutureStub service) {
        UpdateMyDataRequest request = UpdateMyDataRequest.newBuilder()
                .setData(MyData.newBuilder()
                        .setStringValue(NullableString.newBuilder().setNull(NullValue.NULL_VALUE).build())
                        .setSubData(NullableSubData.newBuilder().setNull(NullValue.NULL_VALUE).build())
                        .build())
                .build();

        service.upateMyData(request);
    }

    public void sendValue(MyDataServiceGrpc.MyDataServiceFutureStub service) {
        UpdateMyDataRequest request = UpdateMyDataRequest.newBuilder()
                .setData(MyData.newBuilder()
                        .setStringValue(NullableString.newBuilder().setData("hello").build())
                        .setSubData(NullableSubData.newBuilder().setData(SubData.newBuilder().setBigValue(1234567).build()).build())
                        .build())
                .build();

        service.upateMyData(request);
    }
}
