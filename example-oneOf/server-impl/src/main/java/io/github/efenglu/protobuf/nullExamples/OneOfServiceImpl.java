package io.github.efenglu.protobuf.nullExamples;

import io.github.efenglu.protobuf.examples.MyData;
import io.github.efenglu.protobuf.examples.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.NullableString;
import io.github.efenglu.protobuf.examples.NullableSubData;
import io.github.efenglu.protobuf.examples.SubData;
import io.github.efenglu.protobuf.examples.UpdateMyDataRequest;
import io.github.efenglu.protobuf.examples.UpdateMyDataResponse;
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
                    default:
                        // TODO: Neither null nor data, what to do?
                        throw Status.INVALID_ARGUMENT.asRuntimeException();
                }
            }

        }
    }
}
