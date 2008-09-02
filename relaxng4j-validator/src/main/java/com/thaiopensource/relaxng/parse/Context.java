package com.thaiopensource.relaxng.parse;

import java.util.Enumeration;

public interface Context extends com.googlecode.relaxng4j.datatype.ValidationContext {
  Enumeration prefixes();
  Context copy();
}
