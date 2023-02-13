package com.mycompany.oodms.Dao;

import java.util.List;

public interface ObjectDao<T> {
    List<String> toList(T object);

    boolean fileAddNewRow(T object);

    boolean fileUpdate(T object);
}
