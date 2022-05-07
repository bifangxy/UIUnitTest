package com.zhiyun.uiunittest.data.dao;

import com.zhiyun.uiunittest.data.entity.Story;

/**
 * Describe:
 * Created by xieying on 2022/5/6
 */
public interface StoryDao {

    Story getStoryById(int id);

    boolean updateStory(Story story);
}
