package com.mycompany.oodms.Dao;

import java.util.List;

public interface ForeignKey<T> {
    List<String> toList(T object);

    boolean fileAddNewRow(T object, long ForeignKeyId);

    boolean fileUpdate(T object, long ForeignKeyId);
}
