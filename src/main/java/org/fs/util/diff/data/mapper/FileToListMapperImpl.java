package org.fs.util.diff.data.mapper;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;

public class FileToListMapperImpl<T> implements FileToListMapper<T>
{
    private final Gson gson;
    private final TypeToken<List<T>> typeToken;

    public FileToListMapperImpl(final Gson gson, final TypeToken<List<T>> typeToken) {
        this.gson = gson;
        this.typeToken = typeToken;
    }

    @Override
    public List<T> getMap(File file)
    {
        try(Reader reader = new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))
        {
            return gson.fromJson(reader, typeToken.getType());
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return null;
    }
}
