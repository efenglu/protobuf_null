package io.github.efenglu.protobuf.nullExamples;

import io.github.efenglu.protobuf.examples.oneof.MyData;
import io.github.efenglu.protobuf.examples.oneof.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.oneof.NullableString;
import io.github.efenglu.protobuf.examples.oneof.NullableSubData;
import io.github.efenglu.protobuf.examples.oneof.SubData;
import io.github.efenglu.protobuf.examples.oneof.UpdateMyDataRequest;
import io.github.efenglu.protobuf.examples.oneof.UpdateMyDataResponse;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class OneOfServiceImpl extends MyDataServiceGrpc.MyDataServiceImplBase {

    @Override
    public void upateMyData(
            final UpdateMyDataRequest request, final StreamObserver<UpdateMyDataResponse> responseObserver
    ) {
        if (request.hasData()) {
            MyData data = request.getData();
            if (data.hasStringValue()) {
                NullableString nullableStringValue = data.getStringValue();
                final String nullableString;
                switch(nullableStringValue.getKindCase()) {
                    case NULL:
                        nullableString = null;
                        break;
                    case DATA:
                        nullableString = nullableStringValue.getData();
                        break;
                    default:
                        // TODO: Neither null nor data, what to do?
                        throw Status.INVALID_ARGUMENT.asRuntimeException();
                }
            }

            if (request.getData().hasSubData()) {
                NullableSubData nullableSubDataValue = data.getSubData();
                final SubData nullableSubData;
                switch (nullableSubDataValue.getKindCase()) {
                    case NULL:
                        nullableSubData = null;
                        break;
                    case DATA:
                        nullableSubData = nullableSubDataValue.getData();
                        break;
                    default:
                        // TODO: Neither null nor data, what to do?
                        throw Status.INVALID_ARGUMENT.asRuntimeException();
                }
            }

        }
    }
}
