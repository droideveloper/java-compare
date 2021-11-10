package org.fs.util.diff.data.io;

import org.fs.util.diff.data.predicate.StartsWithPredicate;
import org.fs.util.diff.util.Utils;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.fs.util.diff.util.Utils.SortBy;

class SetToListMapperImpl implements SetToListMapper<String> {

    @Override
    public List<String> map(Set<String> input, String filter) {
        return input.stream()
                .filter(StartsWithPredicate.create(filter))
                .map(Utils.mapperForFilter(filter))
                .distinct()
                .sorted(SortBy)
                .collect(Collectors.toList());
    }
}
