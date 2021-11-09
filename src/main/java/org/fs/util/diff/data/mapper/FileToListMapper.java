package org.fs.util.diff.data.mapper;

import java.io.File;
import java.util.List;

public interface FileToListMapper<T> {
    List<T> getMap(File file);
}
