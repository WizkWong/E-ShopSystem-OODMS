package com.mycompany.oodms.Dao;

import java.util.List;

// for the entity have two id that need to add and update to the file
public interface ForeignKey<T> {
    List<String> toList(T object, long ForeignKeyId);

    boolean fileAddNewRow(T object, long ForeignKeyId);

    boolean fileUpdate(T object, long ForeignKeyId);
}
