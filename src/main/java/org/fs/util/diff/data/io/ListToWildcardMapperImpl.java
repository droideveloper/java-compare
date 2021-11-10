package org.fs.util.diff.data.io;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static org.fs.util.diff.util.Utils.EXTENSION_MAPPER;

public class ListToWildcardMapperImpl implements ListToWildcardMapper {

    @Override
    public List<String> apply(List<String> strings) {
        final List<String> source = strings.stream()
                .map(EXTENSION_MAPPER)
                .distinct()
                .collect(Collectors.toList());

        final List<String> rules = new ArrayList<>();
        for (int i = 0; i < source.size(); i++)
        {
            final String entry = source.get(i);
            final String[] array = entry.split("_");
            if (array.length > 1) {
                int index = 0;
                StringBuffer query = new StringBuffer(array[index]);
                query.append("_");
                long max = source.stream().filter(s -> s.startsWith(query.toString())).count();
                while(index < array.length) {
                    index++;
                    if (index < array.length) {
                        final String nextQuery = query + array[index] + "_";
                        long next = source.stream().filter(s -> s.startsWith(nextQuery)).count();
                        if (next >= max) {
                            max = next;
                            query.append(array[index])
                                 .append("_");
                        } else {
                            break;
                        }
                    }
                }
                if (max != 1) {
                    query.append("*");
                    rules.add(query.toString());
                } else {
                    rules.add(entry);
                }
                i += (int) max - 1;
            } else {
                rules.add(entry);
            }
        }

        return rules;
    }
}
