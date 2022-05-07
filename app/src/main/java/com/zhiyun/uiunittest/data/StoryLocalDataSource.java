package com.zhiyun.uiunittest.data;

import com.zhiyun.uiunittest.data.dao.StoryDao;
import com.zhiyun.uiunittest.data.entity.Story;

/**
 * Describe:
 * Created by xieying on 2022/5/6
 */
public class StoryLocalDataSource {

    private final StoryDao mStoryDao;

    public StoryLocalDataSource(StoryDao storyDao) {
        mStoryDao = storyDao;
    }

    public boolean updateStory(Story story) {
        Story findStory = mStoryDao.getStoryById(story.getId());
        if (findStory == null) {
            return false;
        }
        return mStoryDao.updateStory(story);
    }
}
