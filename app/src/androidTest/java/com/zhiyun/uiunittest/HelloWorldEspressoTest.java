package com.zhiyun.uiunittest;

import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

/**
 * Describe:
 * Created by xieying on 2022/4/20
 */

@RunWith(AndroidJUnit4.class)
@LargeTest
public class HelloWorldEspressoTest {


    @Rule
    public ActivityScenarioRule<MainActivity> activityRule =
            new ActivityScenarioRule<>(MainActivity.class);

    @Test
    public void listGoesOverTheFold() throws InterruptedException {

//        Hamcrest匹配器
        //anyOf()任何一个匹配条件通过
        //allOf()所有的匹配条件匹配则通过


        onView(withId(R.id.tv_test01)).perform(click());
//        Thread.sleep(1000);
//        onView(withId(R.id.tv_test02)).check(matches(not(isDisplayed())));

        onView(withId(R.id.tv_test02)).check(matches(withText("测试1")));
//        onView(withId(R.id.tv_test01)).perform(click());
//        onView(withId(R.id.tv_test02)).check(matches(isDisplayed()));
    }
}
