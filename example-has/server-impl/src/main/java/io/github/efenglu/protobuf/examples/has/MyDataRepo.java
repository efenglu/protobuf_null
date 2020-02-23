package io.github.efenglu.protobuf.examples.has;

import java.util.List;

public interface MyDataRepo {

    MyData readData(int id);
    void writeData(MyData data);

    List<MyData> listData(int id);
}
