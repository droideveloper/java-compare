package org.fs.util.diff.data.mapper;

import java.util.function.Function;

public interface FileExtension extends Function<String, String> {

    static  Function<String, String> create() {
        return new FileExtensionImpl();
    }
}
