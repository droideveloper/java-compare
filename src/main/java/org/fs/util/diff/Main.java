package org.fs.util.diff;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;
import org.fs.util.diff.data.Merge;
import org.fs.util.diff.data.io.*;

import java.io.*;
import java.util.List;
import java.util.Set;

import static org.fs.util.diff.util.Utils.MERGE_MAPPER;
import static org.fs.util.diff.util.Utils.RESOURCES;

public class Main {

    private final static String RESOURCE_DIR = "build/resources/main/";

    private final static String PROJECT_DIR = new File("./", RESOURCE_DIR).getAbsolutePath();
    private final static String OUT_DIR = new File("./", "build/tmp/").getAbsolutePath();

    public static void main(String[] args) {
        final File release = new File(PROJECT_DIR, "release.json");

        final Gson gson = new GsonBuilder().create();
        final TypeToken<List<Merge>> typeToken = new TypeToken<>() {};

        final FileToListMapper<Merge> listMapper = FileToListMapper.create(gson, typeToken);
        final List<Merge> mergeList = listMapper.getMap(release);

        final Set<String> search = mergeList.stream()
            .map(MERGE_MAPPER)
            .collect(Collectors.toSet());


        final Map<String, List<String>> source = new HashMap<>();

        final MapToXmlMapper<String> xml = MapToXmlMapper.create();
        final ListToWildcardMapper wildcard = ListToWildcardMapper.create();

        final SetToListMapper<String> mapper = SetToListMapper.create();
        final ListToFileMapper<String> io = ListToFileMapper.create();
        for (String filter: RESOURCES) {
            final List<String> data = mapper.map(search, filter);
            final List<String> w = wildcard.apply(data);
            source.put(filter, w);
            io.setList(w, filter, new File(OUT_DIR, filter + ".txt"));
        }

        xml.setMap(source, new File(OUT_DIR, "keep.xml"));
    }
}