package com.victorsavkin.logviewer.domain.type

import com.victorsavkin.logviewer.domain.ParsingException

class VoidType extends Type<String>{

	String parse(String s) {
        if(s == null)
            throw new ParsingException("Invalid parameter `${s}`")
        ''
	}

    boolean equals(o){
        o instanceof VoidType
    }
}
