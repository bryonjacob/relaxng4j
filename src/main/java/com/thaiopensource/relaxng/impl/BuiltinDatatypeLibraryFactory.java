package com.thaiopensource.relaxng.impl;

import com.relaxng4j.datatype.DatatypeLibrary;
import com.relaxng4j.datatype.DatatypeLibraryFactory;

import java.util.Hashtable;

import com.thaiopensource.xml.util.WellKnownNamespaces;

class BuiltinDatatypeLibraryFactory implements com.relaxng4j.datatype.DatatypeLibraryFactory {
  private final Hashtable cache = new Hashtable();
  private final DatatypeLibraryFactory factory;
  private final DatatypeLibrary builtinDatatypeLibrary
    = new BuiltinDatatypeLibrary();
  private com.relaxng4j.datatype.DatatypeLibrary lastDatatypeLibrary = null;
  private String lastDatatypeLibraryUri = null;

  BuiltinDatatypeLibraryFactory(DatatypeLibraryFactory factory) {
    this.factory = factory;
    cache.put(WellKnownNamespaces.RELAX_NG_COMPATIBILITY_DATATYPES,
              new CompatibilityDatatypeLibrary(this));
  }

  public com.relaxng4j.datatype.DatatypeLibrary createDatatypeLibrary(String uri) {
    if (uri.equals(""))
      return builtinDatatypeLibrary;
    if (uri.equals(lastDatatypeLibraryUri))
      return lastDatatypeLibrary;
    com.relaxng4j.datatype.DatatypeLibrary library = (com.relaxng4j.datatype.DatatypeLibrary)cache.get(uri);
    if (library == null) {
      if (factory == null)
	return null;
      library = factory.createDatatypeLibrary(uri);
      if (library == null)
	return null;
      cache.put(uri, library);
    }
    lastDatatypeLibraryUri = uri;
    return lastDatatypeLibrary = library;
  }
}
