package com.mycompany.oodms;

import java.util.List;

public interface ForeignKey {

    List<String> toList();

    boolean fileAddNewRow(long ForeignKeyId);

    boolean fileUpdate(long ForeignKeyId);
}
