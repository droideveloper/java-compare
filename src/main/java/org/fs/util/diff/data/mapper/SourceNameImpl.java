package org.fs.util.diff.data.mapper;

import org.fs.util.diff.util.Utils;

class SourceNameImpl implements SourceName {

  SourceNameImpl() { }

  @Override public String apply(String s) {
    final String[] items = s.split(Utils.PATH_STR);
    final int size = items.length;
    if (size != 0) {
      return items[size -1];
    }
    return s;
  }
}
