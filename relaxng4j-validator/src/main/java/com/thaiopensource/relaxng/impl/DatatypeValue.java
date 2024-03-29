package com.thaiopensource.relaxng.impl;

import com.relaxng4j.datatype.Datatype;

class DatatypeValue {
  private final Object value;
  private final com.relaxng4j.datatype.Datatype dt;

  DatatypeValue(Object value, Datatype dt) {
    this.value = value;
    this.dt = dt;
  }

  public int hashCode() {
    return dt.hashCode() ^ dt.valueHashCode(value);
  }

  public boolean equals(Object obj) {
    if (!(obj instanceof DatatypeValue))
      return false;
    DatatypeValue other = (DatatypeValue)obj;
    if (other.dt != dt)
      return false;
    return dt.sameValue(value, other.value);
  }
}
