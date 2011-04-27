package com.victorsavkin.logviewer.domain.type

abstract class Type<T> {
	abstract T parse(String s)

	boolean isApplicable(String s){
		try{
			parse(s)
			true
		} catch (Exception){
			false
		}
	}
}
