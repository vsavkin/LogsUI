package com.victorsavkin.logviewer.domain.type

class NumericType extends Type<Double>{

	Double parse(String s) {
		Double.parseDouble s
	}
}
