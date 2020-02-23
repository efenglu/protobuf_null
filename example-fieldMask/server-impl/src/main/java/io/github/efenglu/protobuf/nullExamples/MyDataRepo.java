package io.github.efenglu.protobuf.nullExamples;

import io.github.efenglu.protobuf.examples.MyData;

import java.util.List;

public interface MyDataRepo {

    MyData readData(int id);
    void writeData(MyData data);

    List<MyData> listData(int id);
}
