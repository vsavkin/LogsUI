package com.victorsavkin.logviewer.domain.type

import java.text.SimpleDateFormat
import java.util.Formatter.DateTime

class DateType extends Type<Date>{

	final String format

    DateType(String format) {
        this.format = format
    }

    Date parse(String s) {
		def f = new SimpleDateFormat(format, Locale.default)
		f.parse s
	}

    boolean equals(o){
        o instanceof DateType && o.format == format
    }
}
