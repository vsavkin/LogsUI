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

    String toString(Date d) {
		def f = new SimpleDateFormat(format, Locale.default)
		f.format d
	}

    boolean equals(o){
        o instanceof DateType && o.format == format
    }
}
