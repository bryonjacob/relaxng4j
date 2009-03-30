package com.thaiopensource.relaxng.parse;

import java.util.Enumeration;

public interface Context extends com.relaxng4j.datatype.ValidationContext {
  Enumeration prefixes();
  Context copy();
}
