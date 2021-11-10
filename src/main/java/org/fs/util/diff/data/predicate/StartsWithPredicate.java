package org.fs.util.diff.data.predicate;

import java.util.function.Predicate;

public interface StartsWithPredicate extends Predicate<String> {

  static StartsWithPredicate create(final String filter) {
    return new StartsWithPredicateImpl(filter);
  }
}
