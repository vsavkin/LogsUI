package com.victorsavkin.logviewer.domain.type

import java.text.SimpleDateFormat

class DateType extends Type<Date>{

	String format

	Date parse(String s) {
		def f = new SimpleDateFormat(format)
		f.parse s
	}
}
