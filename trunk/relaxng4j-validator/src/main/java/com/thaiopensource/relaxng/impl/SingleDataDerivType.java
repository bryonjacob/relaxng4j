package com.thaiopensource.relaxng.impl;

import com.googlecode.relaxng4j.datatype.ValidationContext;

/**
 * DerivType for a Pattern whose derivative wrt any data is always the same.
 */
class SingleDataDerivType extends DataDerivType {
  private PatternMemo memo;

  SingleDataDerivType() { }

  PatternMemo dataDeriv(ValidatorPatternBuilder builder, Pattern p, String str, ValidationContext vc) {
    if (memo == null)
      memo = super.dataDeriv(builder, p, str, vc);
    return memo;
  }

  DataDerivType copy() {
    return new SingleDataDerivType();
  }

  DataDerivType combine(DataDerivType ddt) {
    return ddt;
  }
}