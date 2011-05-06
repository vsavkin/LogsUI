package com.victorsavkin.logviewer.domain.line

class LineCollection {
    final String name
	final List<Line> lines

    LineCollection(String name, List<Line> lines) {
        this.name = name
        this.lines = lines
    }

    LineCollection findAllWhereFieldExist(String varName){
        def filtered = lines.findAll {
            it.fields.any {it.name == varName}
        }
        new LineCollection("$name filtered by $varName", filtered)
    }

    LineCollection findAllWhereFieldEq(String fieldName, value){
        def filtered = lines.findAll {
            it.fields.any {it.name == fieldName && it.type.parse(it.value) == value}
        }
        new LineCollection("$name filtered by $fieldName = $value", filtered)
    }
}
