package com.thaiopensource.datatype.xsd;

class EntityDatatype extends NCNameDatatype {
  boolean allowsValue(String str, com.googlecode.relaxng4j.datatype.ValidationContext vc) {
    return vc.isUnparsedEntity(str);
  }

  Object getValue(String str, com.googlecode.relaxng4j.datatype.ValidationContext vc) {
    if (!allowsValue(str, vc))
      return null;
    return super.getValue(str, vc);
  }
}
