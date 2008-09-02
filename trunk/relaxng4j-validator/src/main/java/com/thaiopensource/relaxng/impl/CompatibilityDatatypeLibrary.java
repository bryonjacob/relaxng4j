package com.thaiopensource.relaxng.impl;

import com.googlecode.relaxng4j.datatype.Datatype;
import com.googlecode.relaxng4j.datatype.DatatypeBuilder;
import com.googlecode.relaxng4j.datatype.DatatypeLibrary;
import com.googlecode.relaxng4j.datatype.DatatypeLibraryFactory;
import com.thaiopensource.xml.util.WellKnownNamespaces;

class CompatibilityDatatypeLibrary implements DatatypeLibrary {
  private final DatatypeLibraryFactory factory;
  private DatatypeLibrary xsdDatatypeLibrary = null;

  CompatibilityDatatypeLibrary(DatatypeLibraryFactory factory) {
    this.factory = factory;
  }

  public DatatypeBuilder createDatatypeBuilder(String type)
          throws com.googlecode.relaxng4j.datatype.DatatypeException {
    if (type.equals("ID") || type.equals("IDREF") || type.equals("IDREFS")) {
      if (xsdDatatypeLibrary == null) {
        xsdDatatypeLibrary = factory.createDatatypeLibrary(WellKnownNamespaces.XML_SCHEMA_DATATYPES);
        if (xsdDatatypeLibrary == null)
          throw new com.googlecode.relaxng4j.datatype.DatatypeException();
      }
      return xsdDatatypeLibrary.createDatatypeBuilder(type);
    }
    throw new com.googlecode.relaxng4j.datatype.DatatypeException();
  }

  public Datatype createDatatype(String type) throws com.googlecode.relaxng4j.datatype.DatatypeException {
    return createDatatypeBuilder(type).createDatatype();
  }
}
