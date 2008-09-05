package com.thaiopensource.relaxng.impl;

class ValuePattern extends StringPattern {
  private final Object obj;
  private final com.relaxng4j.datatype.Datatype dt;

  ValuePattern(com.relaxng4j.datatype.Datatype dt, Object obj) {
    super(combineHashCode(VALUE_HASH_CODE, obj.hashCode()));
    this.dt = dt;
    this.obj = obj;
  }

  boolean samePattern(Pattern other) {
    if (getClass() != other.getClass())
      return false;
    if (!(other instanceof ValuePattern))
      return false;
    return (dt.equals(((ValuePattern)other).dt)
	    && dt.sameValue(obj, ((ValuePattern)other).obj));
  }

  void accept(PatternVisitor visitor) {
    visitor.visitValue(dt, obj);
  }

  Object apply(PatternFunction f) {
    return f.caseValue(this);
  }

  void checkRestrictions(int context, DuplicateAttributeDetector dad, Alphabet alpha)
    throws RestrictionViolationException {
    switch (context) {
    case START_CONTEXT:
      throw new RestrictionViolationException("start_contains_value");
    }
  }

  com.relaxng4j.datatype.Datatype getDatatype() {
    return dt;
  }

  Object getValue() {
    return obj;
  }

}
