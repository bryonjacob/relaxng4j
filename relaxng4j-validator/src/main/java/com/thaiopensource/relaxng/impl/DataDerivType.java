package com.thaiopensource.relaxng.impl;

abstract class DataDerivType {
  abstract DataDerivType copy();
  abstract DataDerivType combine(DataDerivType ddt);
  PatternMemo dataDeriv(ValidatorPatternBuilder builder, Pattern p, String str, com.googlecode.relaxng4j.datatype.ValidationContext vc) {
    return builder.getPatternMemo(p.applyForPattern(new DataDerivFunction(str, vc, builder)));
  }
}
