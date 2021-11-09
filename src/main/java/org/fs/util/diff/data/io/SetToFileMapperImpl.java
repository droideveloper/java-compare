package org.fs.util.diff.data.io;

import org.fs.util.diff.util.Utils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;

public class SetToFileMapperImpl implements SetToFileMapper<String>
{
    @Override
    public void setMap(Set<String> input, String filter, File out)
    {
        final Predicate<String> check = s -> s.startsWith("res/" + filter);
        final Function<String, String> mapper = s -> {
            final String[] items = s.split("/");
            if (items.length != 0) {
                return items[items.length - 1];
            }
            return s;
        };

        final Function<String, String> asMapper = s -> s;

        final List<String> source = input.stream()
                .filter(check)
                .map(filter.equals("") ? asMapper : mapper)
                .distinct()
                .sorted(Utils.SortBy)
                .collect(Collectors.toList());


        try(BufferedWriter writer = new BufferedWriter(new FileWriter(out, StandardCharsets.UTF_8)))
        {
            for(String s: source)
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
