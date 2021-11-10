package org.fs.util.diff.data.io;

import java.util.List;
import java.util.function.Function;

public interface ListToWildcardMapper extends Function<List<String>, List<String>> {

    static ListToWildcardMapper create() {
        return new ListToWildcardMapperImpl();
    }
}
