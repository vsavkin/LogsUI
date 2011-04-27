package com.victorsavkin.logviewer.domain.type

class VoidType extends Type<String>{

	String parse(String s) {
        if(s == null)
            throw new NullPointerException()
        ''
	}
}
