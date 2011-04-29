package com.victorsavkin.logviewer.domain.type

import java.text.SimpleDateFormat

class DateType extends Type<Date>{

	final String format

    DateType(String format) {
        this.format = format
    }

    Date parse(String s) {
		def f = new SimpleDateFormat(format, Locale.default)
		f.parse s
	}
}
