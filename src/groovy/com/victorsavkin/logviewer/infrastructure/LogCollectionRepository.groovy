package com.victorsavkin.logviewer.infrastructure

import com.victorsavkin.logviewer.domain.LineCollection

interface LogCollectionRepository {
	LineCollection findOne(Criteria criteria)
	List<LineCollection> findAll(Criteria criteria)
}