package com.mycompany.oodms.Dao;

import java.util.List;

// for the entity that need to add and update to the file
public interface ObjectDao<T> {
    List<String> toList(T object);

    boolean fileAddNewRow(T object);

    boolean fileUpdate(T object);
}
