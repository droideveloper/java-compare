package org.fs.util.diff.data.mapper;

import java.io.File;
import java.util.Set;

public interface FileToSetMapper<T> {
    Set<T> getSet(File file);
}
