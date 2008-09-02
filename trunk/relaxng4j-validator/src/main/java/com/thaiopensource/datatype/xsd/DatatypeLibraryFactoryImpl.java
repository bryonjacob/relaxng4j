package com.thaiopensource.datatype.xsd;

import com.relaxng4j.datatype.DatatypeLibrary;
import com.relaxng4j.datatype.DatatypeLibraryFactory;
import com.thaiopensource.xml.util.WellKnownNamespaces;

public class DatatypeLibraryFactoryImpl implements DatatypeLibraryFactory {

  private com.relaxng4j.datatype.DatatypeLibrary datatypeLibrary = null;

  public DatatypeLibrary createDatatypeLibrary(String uri) {
    if (!WellKnownNamespaces.XML_SCHEMA_DATATYPES.equals(uri))
      return null;
    synchronized (this) {
      if (datatypeLibrary == null)
        datatypeLibrary = new DatatypeLibraryImpl();
      return datatypeLibrary;
    }
  }
}
