package com.victorsavkin.logviewer.domain.type

abstract class Type<T> {
	abstract T parse(String s)
}
