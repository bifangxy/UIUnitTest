package com.zhiyun.uiunittest;

import static androidx.test.espresso.Espresso.onData;
import static androidx.test.espresso.Espresso.onView;
import static androidx.test.espresso.action.ViewActions.clearText;
import static androidx.test.espresso.action.ViewActions.click;
import static androidx.test.espresso.assertion.ViewAssertions.doesNotExist;
import static androidx.test.espresso.assertion.ViewAssertions.matches;
import static androidx.test.espresso.matcher.ViewMatchers.isDisplayed;
import static androidx.test.espresso.matcher.ViewMatchers.withId;
import static androidx.test.espresso.matcher.ViewMatchers.withText;

import static org.hamcrest.Matchers.allOf;
import static org.hamcrest.Matchers.anyOf;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.hasEntry;
import static org.hamcrest.Matchers.instanceOf;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.isA;
import static org.hamcrest.Matchers.not;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.contrib.RecyclerViewActions;
import androidx.test.espresso.matcher.BoundedMatcher;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.filters.LargeTest;
import androidx.test.rule.ActivityTestRule;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.hamcrest.Matchers;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.util.Map;

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
    public void test() throws InterruptedException {
        onView(withId(R.id.tv_test01)).perform(click());
//        Thread.sleep(5000);
        onView(withId(R.id.tv_test01)).check(matches(withText("测试1")));

//        ViewMatchers.withId();
//        ViewMatchers.withText();
//        anyOf(withText(),withId());

    }

    @Test
    public void test2() {
        //anyOf()任何一个匹配条件通过
        //allOf()所有的匹配条件匹配则通过
        onView(withId(R.id.tv_test01)).perform(click());
//        onView(withId(R.id.tv_test01)).check(matches(withText("测试1")));
        onView(withId(R.id.tv_test02)).check(matches(not(isDisplayed())));

        onView(withId(R.id.tv_test01)).perform(click());

        onView(withId(R.id.tv_test02)).check(matches(not(isDisplayed())));

//        onView(withId(R.id.tv_test02)).check(matches(withText("测试1")));
//        onView(withId(R.id.tv_test01)).perform(click());
//        onView(withId(R.id.tv_test02)).check(matches(isDisplayed()));

    }

    @Test
    public void test3(){
        onData(allOf(isA(String.class),is("Mango")))
                .inAdapterView(withId(R.id.lv_data))
                .perform(click());
//        onData(withValue("Mango"))
//                .inAdapterView(withId(R.id.lv_data))
//                .perform(click());

        onView(withId(R.id.tv_test02)).check(matches(withText("Mango")));
    }

    @Test
    public void test4(){
        onView(withId(R.id.rcv_data))
                .perform(RecyclerViewActions.actionOnItemAtPosition(9,click()));
        onView(withText("Mango")).check(matches(isDisplayed()));
    }



    public static Matcher<Object> withValue(final String value){
        return new BoundedMatcher<Object, String>(String.class) {
            @Override
            public void describeTo(Description description) {
                description.appendText("has value "+value);
            }

            @Override
            protected boolean matchesSafely(String item) {
                return value.equals(item);
            }
        };
    }


}
