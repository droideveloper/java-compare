package org.fs.util.diff.data.io;

import java.util.List;
import java.util.Set;

public interface SetToListMapper<T> {

    static SetToListMapper<String> create() {
        return new SetToListMapperImpl();
    }

    List<T> map(Set<T> input, String filter);
}
