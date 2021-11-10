package org.fs.util.diff.data.predicate;

class StartsWithPredicateImpl implements StartsWithPredicate {

  private final String filter;

  StartsWithPredicateImpl(final String filter) {
    this.filter = filter;
  }

  @Override public boolean test(String s) {
    return s.startsWith("res/" + filter);
  }
}
