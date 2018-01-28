package com.storyshell.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.inject.Inject;

import org.springframework.transaction.annotation.Transactional;

import com.storyshell.contentdataservices.GenericExceptionHandler;
import com.storyshell.dao.ContentData;
import com.storyshell.dao.ElasticData;
import com.storyshell.model.ContentHit;

public class HitServiceImpl  implements HitService {

	@Inject
	private ElasticData elasticData;
	
	@Inject
	private ContentData contentData;
	
	@Override
	@Transactional(rollbackFor = { GenericExceptionHandler.class, IOException.class, Exception.class,
			SQLException.class })
	public int saveHit(ContentHit contentHit) {
		int resultId = contentData.saveLikesHit(contentHit);
		contentHit.setId(resultId);
		
		//after saving hit to mysql  database, we save to elastic data
		ContentHit result = elasticData.save(contentHit);
		return result.getId();
	}

	@Override
	public List<ContentHit> getContentHit() {
		return null;
	}

	@Override
	public int unsaveHit(int hitId) {
		
		return 0;
	}

}
