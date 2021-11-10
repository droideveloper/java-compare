package org.fs.util.diff.data.io;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import java.io.File;
import java.util.List;

public interface FileToListMapper<T> {

    static <T> FileToListMapper<T> create(final Gson gson, final TypeToken<List<T>> typeToken) {
        return new FileToListMapperImpl<>(gson, typeToken);
    }

    List<T> getMap(File file);
}
