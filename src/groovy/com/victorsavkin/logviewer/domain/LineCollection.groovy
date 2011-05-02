package com.victorsavkin.logviewer.domain

class LineCollection {
    final String name
	final List<Line> lines

    LineCollection(String name, List<Line> lines) {
        this.name = name
        this.lines = lines
    }

    LineCollection findAllWhereVarExist(String varName){
        def filtered = lines.findAll {
            it.variables.any {it.name == varName}
        }
        new LineCollection("$name filtered by $varName", filtered)
    }

    LineCollection findAllWhereVarEq(String varName, value){
        def filtered = lines.findAll {
            it.variables.any {it.name == varName && it.type.parse(it.value) == value}
        }
        new LineCollection("$name filtered by $varName = $value", filtered)
    }
}
