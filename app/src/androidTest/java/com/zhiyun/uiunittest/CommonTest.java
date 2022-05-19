package com.zhiyun.uiunittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.mock;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;

import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Describe:
 * Created by xieying on 2022/5/9
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class CommonTest {

    @Test
    public void testIntentShouldBeCreated(){
        Context context = mock(Context.class);
        Intent intent = MainActivity.createQuery(context,"query","value");
        assertNotNull(intent);
        Bundle bundle = intent.getExtras();
        assertNotNull(bundle);

        assertEquals("query",bundle.getString("QUERY"));
        assertEquals("参数不正确","value1",bundle.getString("VALUE"));
    }
}
