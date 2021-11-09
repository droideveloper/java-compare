package org.fs.util.diff.util;

import org.fs.util.diff.data.Merge;

import java.util.Comparator;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class Utils {

    public static final Comparator<String> SortBy = (l, r) -> {
        String[] la = l.split("_");
        String[] ra = r.split("_");

        final String lf = la.length != 0 ? la[0] : l;
        final String rf = ra.length != 0 ? ra[0] : r;


        return lf.compareTo(rf);
    };


    public static Set<String> mapToSet(List<Merge> mergeList, Pattern pattern) {
        final Set<String> merged = new TreeSet<>();
        for(int i = 0; i < mergeList.size(); i++) {
            final Merge merge = mergeList.get(i);
            final Matcher matcher = pattern.matcher(merge.getSource());
            if (matcher.find()) {
                merged.add("res/" + matcher.group(0));
            }
        }
        return merged;
    }
}
