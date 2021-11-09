package org.fs.util.diff.data.io;

import java.io.File;
import java.util.Set;

public interface SetToFileMapper<T> {
    void setMap(Set<T> input, String filter, File out);
}
