package org.fs.util.diff.data.mapper;

import java.util.function.Function;

public interface SourceName extends Function<String, String> {

  static SourceName create() {
    return new SourceNameImpl();
  }
}
