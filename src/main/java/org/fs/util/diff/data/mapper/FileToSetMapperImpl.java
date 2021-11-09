package org.fs.util.diff.data.mapper;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Set;
import java.util.TreeSet;

public class FileToSetMapperImpl implements FileToSetMapper<String>
{
    @Override
    public Set<String> getSet(File file)
    {
        final Set<String> merged = new TreeSet<>();
        try(BufferedReader br = new BufferedReader(new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8)))
        {
            for(String line = br.readLine(); line != null; line = br.readLine())
            {
                merged.add(line);
            }
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
        return merged;
    }
}
