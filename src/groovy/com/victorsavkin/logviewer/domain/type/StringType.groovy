package com.victorsavkin.logviewer.domain.type

import com.victorsavkin.logviewer.domain.ParsingException

class StringType extends Type<String> {

	String parse(String s) {
        if(s == null)
            throw new ParsingException("Invalid parameter `${s}`")
		s
	}

    boolean equals(o){
        o instanceof StringType
    }
}
