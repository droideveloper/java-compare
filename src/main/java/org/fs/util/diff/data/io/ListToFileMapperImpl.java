package org.fs.util.diff.data.io;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;


class ListToFileMapperImpl implements ListToFileMapper<String>
{
    @Override
    public void setList(List<String> input, String filter, File out)
    {
        try(BufferedWriter writer = new BufferedWriter(new FileWriter(out, StandardCharsets.UTF_8)))
        {
            for(String s: input)
            {
                writer.write(s);
                writer.newLine();
            }
        } catch (IOException ioe)
        {
            ioe.printStackTrace();
        }
    }
}
