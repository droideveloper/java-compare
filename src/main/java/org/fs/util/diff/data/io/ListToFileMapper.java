package org.fs.util.diff.data.io;

import java.io.File;
import java.util.List;

public interface ListToFileMapper<T> {

    static ListToFileMapper<String> create() {
        return new ListToFileMapperImpl();
    }

    void setList(List<T> input, String filter, File out);
}
