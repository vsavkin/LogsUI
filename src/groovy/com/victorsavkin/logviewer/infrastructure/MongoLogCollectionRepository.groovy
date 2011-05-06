package com.victorsavkin.logviewer.infrastructure

import com.victorsavkin.logviewer.domain.line.LineCollection

import com.victorsavkin.logviewer.domain.line.LineSerializer

class MongoLogCollectionRepository {

    private final mongo
    private final lineSerializer = new LineSerializer()

    MongoLogCollectionRepository(mongo) {
        this.mongo = mongo
    }

    void insert(LineCollection c) {
        mongo.headers << createHeader(c)
        mongo.lines << serializeLines(c)
    }

    int getNumberOfCollections() {
        mongo.headers.count()
    }

    void clear() {
        mongo.headers.drop()
        mongo.lines.drop()
    }

    List<LineCollection> findAll(Criteria criteria) {
        def map = createResMapBasedOnHeaders(criteria)
        fillLines map, criteria
        createLineCollections map
    }

    private createResMapBasedOnHeaders(criteria) {
        def d = mongo.headers.find(createMongoCriteria(criteria))
        def res = [:]
        d.each {res[it.collectionName] = []}
        res
    }

    private fillLines(map, criteria) {
        def d = mongo.lines.find(createMongoCriteria(criteria))
        d.each {
            map[it.collectionName] = map[it.collectionName] ?: []
            map[it.collectionName] << lineSerializer.parse(it)
        }
    }

    private createLineCollections(map) {
        map.collect {name, lines -> new LineCollection(name, lines)}
    }

    private createMongoCriteria(criteria) {
        [collectionName: criteria.filename]
    }

    private createHeader(c) {
        [collectionName: c.name]
    }

    private serializeLines(c) {
        c.lines.collect {lineSerializer.toMap(c.name, it)}
    }
}
