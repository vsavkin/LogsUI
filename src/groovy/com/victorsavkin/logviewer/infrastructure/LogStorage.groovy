package com.victorsavkin.logviewer.infrastructure

import com.victorsavkin.logviewer.domain.LineCollection

interface LogStorage {

	LineCollection findOne(Criteria criteria)
	List<LineCollection> findAll(Criteria criteria)
}