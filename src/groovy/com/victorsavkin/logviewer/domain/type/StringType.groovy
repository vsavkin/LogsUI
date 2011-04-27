package com.victorsavkin.logviewer.domain.type

class StringType extends Type<String> {

	String parse(String s) {
        if(s == null)
            throw new NullPointerException()
		s
	}
}
