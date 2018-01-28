package com.storyshell.services;

import java.util.List;

import com.storyshell.model.ContentHit;

public interface HitService {

	public int saveHit(ContentHit contentHit);
	
	public List<ContentHit> getContentHit();
	
	public int unsaveHit(int hitId);
	
}
