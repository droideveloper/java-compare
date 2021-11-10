package org.fs.util.diff.data.io;

import java.io.File;
import java.util.List;
import java.util.Map;

public interface MapToXmlMapper<T> {

    static MapToXmlMapper<String> create() {
        return new MapToXmlMapperImpl();
    }

    void setMap(Map<String, List<T>> source, File out);
}
