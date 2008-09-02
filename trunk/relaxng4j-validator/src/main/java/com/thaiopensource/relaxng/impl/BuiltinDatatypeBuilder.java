package com.thaiopensource.relaxng.impl;

import com.relaxng4j.datatype.DatatypeBuilder;
import com.relaxng4j.datatype.DatatypeException;

class BuiltinDatatypeBuilder implements DatatypeBuilder {
  private final com.relaxng4j.datatype.Datatype dt;

  BuiltinDatatypeBuilder(com.relaxng4j.datatype.Datatype dt) {
    this.dt = dt;
  }

  public void addParameter(String name,
			   String value,
			   com.relaxng4j.datatype.ValidationContext context) throws DatatypeException {
    throw new com.relaxng4j.datatype.DatatypeException(SchemaBuilderImpl.localizer.message("builtin_param"));
  }

  public com.relaxng4j.datatype.Datatype createDatatype() {
    return dt;
  }
}
