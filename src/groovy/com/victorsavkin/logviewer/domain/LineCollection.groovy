package com.victorsavkin.logviewer.domain

class LineCollection {
	final List<Line> lines

    LineCollection(List<Line> lines) {
        this.lines = lines
    }

    LineCollection findAllWhereVarExist(String varName){
        def filtered = lines.findAll {
            it.variables.any {it.name == varName}
        }
        new LineCollection(filtered)
    }

    LineCollection findAllWhereVarEq(String varName, value){
        def filtered = lines.findAll {
            it.variables.any {it.name == varName && it.type.parse(it.value) == value}
        }
        new LineCollection(filtered)
    }
}
