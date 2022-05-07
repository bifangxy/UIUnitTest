package com.zhiyun.uiunittest;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;


import com.zhiyun.uiunittest.data.StoryLocalDataSource;
import com.zhiyun.uiunittest.data.dao.StoryDao;
import com.zhiyun.uiunittest.data.entity.Story;

import org.junit.Before;
import org.junit.Test;

/**
 * Describe:
 * Created by xieying on 2022/5/6
 */
public class StoryLocalDataSourceTest {

    private StoryDao mStoryDao;

    private StoryLocalDataSource mStoryLocalDataSource;


    @Before
    public void initData(){
        //mock一个Story对象
        mStoryDao = mock(StoryDao.class);
        //设置桩
        when(mStoryDao.getStoryById(1)).thenReturn(new Story(1, "剧本", "www.baidu.com"));
        when(mStoryDao.updateStory(any(Story.class))).thenReturn(true);

        mStoryLocalDataSource = new StoryLocalDataSource(mStoryDao);
    }


    @Test
    public void updateTest1(){
        boolean result = mStoryLocalDataSource.updateStory(new Story(1,"旧数据","旧地址"));
        assertTrue("此处应该为true",result);

        verify(mStoryDao,times(1)).getStoryById(anyInt());
        verify(mStoryDao,times(1)).updateStory(any(Story.class));

    }

    @Test
    public void updateTest2(){
//        Story story = mStoryDao.getStoryById(1);
//        System.out.println(story);

        boolean result = mStoryLocalDataSource.updateStory(new Story(2,"旧数据","旧地址"));
        assertTrue("此处应该为true",result);

        verify(mStoryDao,times(1)).getStoryById(anyInt());
        verify(mStoryDao,times(0)).updateStory(any(Story.class));

    }
}
