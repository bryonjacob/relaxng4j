package com.thaiopensource.relaxng.impl;

import com.thaiopensource.datatype.Datatype2;
import com.googlecode.relaxng4j.datatype.DatatypeException;
import com.googlecode.relaxng4j.datatype.DatatypeStreamingValidator;
import com.googlecode.relaxng4j.datatype.ValidationContext;

class StringDatatype implements Datatype2 {
  public boolean isValid(String str, ValidationContext vc) {
    return true;
  }

  public void checkValid(String str, com.googlecode.relaxng4j.datatype.ValidationContext vc) throws DatatypeException {
    if (!isValid(str, vc))
      throw new DatatypeException();
  }

  public Object createValue(String str, ValidationContext vc) {
    return str;
  }

  public boolean isContextDependent() {
    return false;
  }

  public boolean alwaysValid() {
    return true;
  }

  public int getIdType() {
    return ID_TYPE_NULL;
  }

  public boolean sameValue(Object obj1, Object obj2) {
    return obj1.equals(obj2);
  }

  public int valueHashCode(Object obj) {
    return obj.hashCode();
  }

  public DatatypeStreamingValidator createStreamingValidator(ValidationContext vc) {
    return new com.googlecode.relaxng4j.datatype.helpers.StreamingValidatorImpl(this, vc);
  }
}
