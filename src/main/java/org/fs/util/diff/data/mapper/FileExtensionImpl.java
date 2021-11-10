package org.fs.util.diff.data.mapper;

class FileExtensionImpl implements FileExtension {

    @Override
    public String apply(String s) {
        final String[] array = s.split("\\.");
        if (array.length != 0) {
            return array[0];
        }
        return s;
    }
}
