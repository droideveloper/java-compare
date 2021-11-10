package org.fs.util.diff.data.mapper;

import java.util.function.Function;
import java.util.regex.Pattern;
import org.fs.util.diff.data.Merge;

public interface MergeToString extends Function<Merge, String> {

  static Function<Merge, String> create(final Pattern pattern) {
    return new MergeToStringImpl(pattern);
  }
}
