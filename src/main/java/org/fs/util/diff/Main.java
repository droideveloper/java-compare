package org.fs.util.diff;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
import org.fs.util.diff.data.Merge;
import org.fs.util.diff.data.io.SetToFileMapper;
import org.fs.util.diff.data.io.SetToFileMapperImpl;
import org.fs.util.diff.data.mapper.FileToListMapper;
import org.fs.util.diff.data.mapper.FileToListMapperImpl;
import org.fs.util.diff.data.mapper.FileToSetMapper;
import org.fs.util.diff.data.mapper.FileToSetMapperImpl;
import org.fs.util.diff.util.Utils;

import java.io.*;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.regex.Pattern;

public class Main {

    private final static String RESOURCE_DIR = "build/resources/main/";

    private final static String PROJECT_DIR = new File("./", RESOURCE_DIR).getAbsolutePath();
    private final static String OUT_DIR = new File("./", "build/tmp/").getAbsolutePath();

    private final static String SEARCH_PATTERN = "(layout|raw|drawable|anim|color|animator).*$";

    private final static String[] KEYWORDS = new String[] {"layout", "animator", "raw", "drawable", "anim", "color", ""};

    public static void main(String[] args) {
        final File resources = new File(PROJECT_DIR, "skipped_resources_2.txt");
        final File release = new File(PROJECT_DIR, "release.json");
        final Pattern pattern = Pattern.compile(SEARCH_PATTERN, Pattern.MULTILINE);

        final FileToSetMapper<String> setMapper = new FileToSetMapperImpl();
        final Set<String> lookUp = setMapper.getSet(resources);

        final Gson gson = new GsonBuilder().create();
        final TypeToken<List<Merge>> typeToken = new TypeToken<>() {};

        final FileToListMapper<Merge> listMapper = new FileToListMapperImpl<>(gson, typeToken);
        final List<Merge> mergeList = listMapper.getMap(release);

        final Set<String> search = Utils.mapToSet(mergeList, pattern);

        final Set<String> hits = new TreeSet<>();
        for (String q: search)
        {
            if (lookUp.contains(q))
            {
                hits.add(q);
            }
        }

        final SetToFileMapper<String> fileMapper = new SetToFileMapperImpl();
        for (String filter: KEYWORDS)
        {
            final String name = filter.equals("") ? "release" : filter;
            fileMapper.setMap(hits, filter, new File(OUT_DIR, name + ".txt"));
        }

    }
}