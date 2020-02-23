package io.github.efenglu.protobuf.nullExamples;

import com.google.protobuf.util.FieldMaskUtil;
import io.github.efenglu.protobuf.examples.fieldmask.ListMyDataRequest;
import io.github.efenglu.protobuf.examples.fieldmask.MyData;
import io.github.efenglu.protobuf.examples.fieldmask.MyDataServiceGrpc;
import io.github.efenglu.protobuf.examples.fieldmask.UpdateMyDataRequest;

import java.util.List;

public class FieldMaskClientImpl {

    MyDataServiceGrpc.MyDataServiceBlockingStub serviceFutureStub;

    MyData sendUpdate(int id, String value) {
        UpdateMyDataRequest request = UpdateMyDataRequest.newBuilder()
                .setUpdate(MyData.newBuilder()
                        .setId(id)
                        .setStringValue(value)
                )
                .setFieldMask(FieldMaskUtil.fromFieldNumbers(MyData.class, MyData.STRINGVALUE_FIELD_NUMBER))
                .build();

        return serviceFutureStub.update(request).getNewData();
    }

    List<MyData> listOnlySubData(int id) {
        ListMyDataRequest request = ListMyDataRequest.newBuilder()
                .setId(id)
                .setFieldMask(FieldMaskUtil.fromFieldNumbers(MyData.class, MyData.SUBDATA_FIELD_NUMBER))
                .build();

        return serviceFutureStub.list(request).getDataList();
    }
}
