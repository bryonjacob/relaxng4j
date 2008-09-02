package com.thaiopensource.relaxng.impl;

import com.googlecode.relaxng4j.datatype.DatatypeBuilder;
import com.googlecode.relaxng4j.datatype.DatatypeException;
import com.googlecode.relaxng4j.datatype.DatatypeLibrary;

public class BuiltinDatatypeLibrary implements DatatypeLibrary {
  private final DatatypeBuilder tokenDatatypeBuilder
    = new BuiltinDatatypeBuilder(new TokenDatatype());
  private final com.googlecode.relaxng4j.datatype.DatatypeBuilder stringDatatypeBuilder
    = new BuiltinDatatypeBuilder(new StringDatatype());
  public com.googlecode.relaxng4j.datatype.DatatypeBuilder createDatatypeBuilder(String type)
    throws com.googlecode.relaxng4j.datatype.DatatypeException {
    if (type.equals("token"))
      return tokenDatatypeBuilder;
    else if (type.equals("string"))
      return stringDatatypeBuilder;
    throw new DatatypeException();
  }
  public com.googlecode.relaxng4j.datatype.Datatype createDatatype(String type) throws DatatypeException {
    return createDatatypeBuilder(type).createDatatype();
  }
}
