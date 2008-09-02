package com.thaiopensource.relaxng.impl;

import com.googlecode.relaxng4j.datatype.DatatypeBuilder;
import com.googlecode.relaxng4j.datatype.DatatypeException;

class BuiltinDatatypeBuilder implements DatatypeBuilder {
  private final com.googlecode.relaxng4j.datatype.Datatype dt;

  BuiltinDatatypeBuilder(com.googlecode.relaxng4j.datatype.Datatype dt) {
    this.dt = dt;
  }

  public void addParameter(String name,
			   String value,
			   com.googlecode.relaxng4j.datatype.ValidationContext context) throws DatatypeException {
    throw new com.googlecode.relaxng4j.datatype.DatatypeException(SchemaBuilderImpl.localizer.message("builtin_param"));
  }

  public com.googlecode.relaxng4j.datatype.Datatype createDatatype() {
    return dt;
  }
}
