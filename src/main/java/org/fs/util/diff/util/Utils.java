package org.fs.util.diff.util;

import java.util.function.Function;
import org.fs.util.diff.data.Merge;

import java.util.Comparator;
import java.util.regex.Pattern;

import org.fs.util.diff.data.mapper.FileExtension;
import org.fs.util.diff.data.mapper.MergeToString;
import org.fs.util.diff.data.mapper.SourceName;

public final class Utils {

    public final static String[] RESOURCES = new String[] { "layout", "animator", "raw", "drawable", "mipmap", "anim", "color", "font", "interpolator", "menu", "xml", "navigation" };

    public final static String PATH_STR = "/";

    private final static String SEARCH_PATTERN = "(layout|raw|drawable|mipmap|anim|color|animator|font|menu|navigation|xml|interpolator).*$";
    private final static Pattern PATTERN = Pattern.compile(SEARCH_PATTERN, Pattern.MULTILINE);

    public static final Comparator<String> SortBy = (l, r) -> {
        String[] la = l.split("_");
        String[] ra = r.split("_");

        final String lf = la.length != 0 ? la[0] : l;
        final String rf = ra.length != 0 ? ra[0] : r;


        return lf.compareTo(rf);
    };


    public static final Function<Merge, String> MERGE_MAPPER = MergeToString.create(PATTERN);

    public static final Function<String, String> EXTENSION_MAPPER = FileExtension.create();

    public static final Function<String, String> NAME_MAPPER = SourceName.create();

    public static final Function<String, String> NOTHING_MAPPER = s -> s;

    public static Function<String, String> mapperForFilter(String filter) {
        return filter.equalsIgnoreCase("") ? NOTHING_MAPPER : NAME_MAPPER;
    }
}
