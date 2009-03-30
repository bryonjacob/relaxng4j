package com.thaiopensource.relaxng.impl;

import com.relaxng4j.datatype.DatatypeBuilder;
import com.relaxng4j.datatype.DatatypeException;
import com.relaxng4j.datatype.DatatypeLibrary;

public class BuiltinDatatypeLibrary implements DatatypeLibrary {
  private final DatatypeBuilder tokenDatatypeBuilder
    = new BuiltinDatatypeBuilder(new TokenDatatype());
  private final com.relaxng4j.datatype.DatatypeBuilder stringDatatypeBuilder
    = new BuiltinDatatypeBuilder(new StringDatatype());
  public com.relaxng4j.datatype.DatatypeBuilder createDatatypeBuilder(String type)
    throws com.relaxng4j.datatype.DatatypeException {
    if (type.equals("token"))
      return tokenDatatypeBuilder;
    else if (type.equals("string"))
      return stringDatatypeBuilder;
    throw new DatatypeException();
  }
  public com.relaxng4j.datatype.Datatype createDatatype(String type) throws DatatypeException {
    return createDatatypeBuilder(type).createDatatype();
  }
}
