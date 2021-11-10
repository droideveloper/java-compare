package org.fs.util.diff.data.mapper;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.fs.util.diff.data.Merge;

class MergeToStringImpl implements MergeToString {

  private final Pattern pattern;

  MergeToStringImpl(final Pattern pattern) {
    this.pattern = pattern;
  }

  @Override public String apply(Merge merge) {
    final Matcher matcher = pattern.matcher(merge.getSource());
    if (matcher.find()) {
      return "res/" + matcher.group(0);
    }
    System.out.println("merge: " + merge.getSource());
    return null;
  }
}
