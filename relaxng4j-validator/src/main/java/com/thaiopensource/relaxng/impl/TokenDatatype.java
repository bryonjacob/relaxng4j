package com.thaiopensource.relaxng.impl;

class TokenDatatype extends StringDatatype {
  public Object createValue(String str, com.googlecode.relaxng4j.datatype.ValidationContext vc) {
    return StringNormalizer.normalize(str);
  }
}
