package com.zhiyun.uiunittest;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import static org.junit.Assert.*;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.when;

import java.util.LinkedList;
import java.util.List;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {


    private int test = 1;

    @Mock
    LinkedList mMockedList;


    @Test
    public void addition_isCorrect() {
//        assertEquals(4, 2 + 2);

        List mockList = mock(List.class);
        mockList.add("one");
        mockList.clear();

        verify(mockList).add("two");
        verify(mockList).clear();

    }


    @Test
    public void mockTest(){
        LinkedList mockedList = mock(LinkedList.class);

        when(mockedList.get(0)).thenReturn("first");
        when(mockedList.get(99)).thenReturn("first");

        when(mockedList.get(anyInt())).thenReturn("any int");

        System.out.println(mockedList.get(anyInt()));

        System.out.println(mockedList.get(99));

        System.out.println(test);

        verify(mockedList,atLeast(1)).get(anyInt());



        doThrow(new RuntimeException("没有返回值")).when(mockedList).addFirst(anyString());

        mockedList.addFirst("1");

        System.out.println(mockedList.get(0));


        verifyNoInteractions(mockedList);

        verify(mockedList,never()).add(isA(String.class));
//        mockedList.addFirst("2");
    }

    @Test
    public void mockTest2(){
        when(mMockedList.get(anyInt())).thenReturn("test2");
        System.out.println(mMockedList.get(2));
    }

    @Before
    public void before(){
        MockitoAnnotations.openMocks(this);
        test = 3;
    }


}