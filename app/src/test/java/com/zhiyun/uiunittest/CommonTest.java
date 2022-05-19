package com.zhiyun.uiunittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.isA;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.doThrow;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.reset;
import static org.mockito.Mockito.timeout;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoInteractions;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import com.zhiyun.uiunittest.data.StoryLocalDataSource;
import com.zhiyun.uiunittest.data.UnitTest;
import com.zhiyun.uiunittest.data.dao.StoryDao;
import com.zhiyun.uiunittest.data.entity.Story;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.MockitoRule;
import org.mockito.stubbing.Answer;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;

/**
 * Describe:
 * Created by xieying on 2022/5/6
 */
//@RunWith(MockitoJUnitRunner.class)
public class CommonTest {

    //第一种创建mock对象的方法
    @Mock
    private List<String> mMockList;

    @Rule
    public MockitoRule mMockitoRule = MockitoJUnit.rule();

    @Before
    public void init(){
//        MockitoAnnotations.openMocks(this);
    }

    /**
     * 验证行为
     */
    @Test
    public void validationBehavior() {
//第二种创建mock对象的方法
//        mMockList = mock(List.class);

        mMockList.add("one");
        mMockList.add("two");
        mMockList.add("two");
        mMockList.add("three");

        System.out.println("---" + mMockList.get(0));

//        verify(mMockList).add(anyString());

//        verify(mMockList,times(2)).add("two");

//        verify(mMockList,atLeast(1)).add("one");

//        verify(mMockList,never()).add("three");

//        verify(mMockList, atMost(1)).add("two");
    }


    /**
     * 测试桩
     */
    @Test
    public void stubbing(){
        when(mMockList.get(0)).thenReturn("one");
        when(mMockList.get(0)).thenReturn("two");
        when(mMockList.get(1)).thenThrow(new RuntimeException());

//        System.out.println(mMockList.get(0));
//        System.out.println(mMockList.get(1));
        System.out.println(mMockList.get(999));
    }


    /**
     * 参数匹配
     */
    @Test
    public void paramsMatch() {
//        when(mMockList.get(12)).thenReturn("element1");
//        when(mMockList.get(anyInt())).thenReturn("element");
//        when(mMockList.contains(anyString())).thenReturn(true);
        when(mMockList.contains(isA(String.class))).thenReturn(true);
//        assertTrue(mMockList.contains("das"));
//        System.out.println(mMockList.get(12));
//        System.out.println(mMockList.get(10));


        UnitTest unitTest = mock(UnitTest.class);
//        when(unitTest.getDescribeCount(anyInt(), "describe")).thenReturn(1);
        when(unitTest.getDescribeCount(anyInt(), anyString())).thenReturn(1);
//        System.out.println(unitTest.getDescribeCount(anyInt(), eq("describe")));
        System.out.println(unitTest.getDescribeCount(1, "1"));
    }


    /**
     * 为void方法抛出异常
     */
    @Test
    public void voidThrow(){
        doThrow(new RuntimeException("DASDASD")).when(mMockList).add("1");
        mMockList.add("d");
    }

    /**
     * 验证调用顺序
     */
    @Test
    public void verificationInOrder(){
        //单个mock对象
//        mMockList.add("one");
//        mMockList.add("two");
//
        InOrder inOrder = Mockito.inOrder(mMockList);
//        inOrder.verify(mMockList).add("one");
//        inOrder.verify(mMockList).add("two");

        //多个mock对象
        List firstMock = mock(List.class);
        List secondMock = mock(List.class);

        firstMock.add("first");
        secondMock.add("second");

        inOrder = Mockito.inOrder(firstMock,secondMock);

//        inOrder.verify(secondMock).add("second");
//        inOrder.verify(firstMock).add("first");
//        inOrder.verify(secondMock,never()).add("one");
//        inOrder.verify(firstMock,never()).add("one");
    }


    /**
     * 验证mock对象没有产生过交互
     */
    @Test
    public void interactionNeverHappened(){
        verifyNoInteractions(mMockList);

//        mMockList.add("one");
//        mMockList.clear();
        verify(mMockList,never()).add("one");
        verifyNoInteractions(mMockList);
//        verifyNoMoreInteractions(mMockList);

    }

    /**
     * 查找是否有未验证的交互
     */
    @Test
    public void findingRedundantInvocations(){
        mMockList.add("one");
        mMockList.add("two");
//        mMockList.remove("one");
        verify(mMockList,times(2)).add(anyString());
        mMockList.add("two");
        verify(mMockList,times(3)).add(anyString());
        verifyNoMoreInteractions(mMockList);
    }


    /**
     * 根据调用顺序设置不同的stubbing
     */
    @Test()
    public void stubbingConsecutiveCalls() {
        when(mMockList.get(1))
                .thenReturn("one")
                .thenReturn("result");

//        when(mMockList.get(anyInt())).thenReturn("1").thenReturn("2");
//        mMockList.get(0);
        System.out.println(mMockList.get(1));
        System.out.println(mMockList.get(1));
    }


    /**
     * 回调
     */
    @Test
    public void stubbingWithCallback() {
        when(mMockList.get(anyInt()))
                .thenAnswer(invocation -> {
                    Object[] args = invocation.getArguments();
                    Object mock = invocation.getMock();
                    return "callback " + Arrays.toString(args);
                });
        System.out.println(mMockList.get(1));


//        doNothing()
//                .doThrow(new RuntimeException())
//                .when(mMockList).clear();
//        System.out.println(mMockList.get(0));
    }


    /**
     * doReturn使用，在无法使用 when(Object) 的极少数情况下使用 doReturn()。
     */
    @Test
    public void doReturn(){
        List list = new LinkedList();
        List spy  = Mockito.spy(list);

//        when(spy.get(0)).thenReturn("first");
        Mockito.doReturn("first").when(spy).get(0);


        when(mMockList.get(0)).thenThrow(new RuntimeException());

//        when(mMockList.get(0)).thenReturn("first");

        Mockito.doReturn("first").when(mMockList).get(0);
    }

    @Test
    public void doAnswer(){
        Mockito.doAnswer(new Answer() {
            @Override
            public Object answer(InvocationOnMock invocation) throws Throwable {
                Object[] args = invocation.getArguments();
                Object mock = invocation.getMock();
                return "callback " + Arrays.toString(args);
            }
        }).when(mMockList).get(0);

        System.out.println(mMockList.get(0));
    }


    /**
     * spy
     */
    @Test
    public void spyTest(){
        List list = new LinkedList();
        list.add("1");
        List spy  = Mockito.spy(list);

//        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0));

        System.out.println(spy.size());

        System.out.println(list.size());


    }


    /**
     * 为未stub的方法设置默认返回值
     */
    @Test
    public void returnDefault(){
//        LinkedList<String> list = mock(LinkedList.class,Mockito.RETURNS_SMART_NULLS);

        LinkedList<String> list1 = mock(LinkedList.class, invocation -> {
            Class<?> returnType = invocation.getMethod().getReturnType();
            System.out.println(returnType);
            if(returnType == Object.class ){
                return "two";
            }else if (returnType == int.class){
                return 3;
            }
            return "one";
        });
//        when(list1.get(1)).thenReturn("two");
//        System.out.println(list1.get(1));
//        System.out.println(list1.get(2));
        System.out.println(list1.indexOf("das"));
    }


    /**
     * 参数捕捉
     */
    @Test
    public void capturingArguments(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        mMockList.add("two");
        verify(mMockList).add(argumentCaptor.capture());
        assertEquals("one",argumentCaptor.getValue());
    }


    @Test
    public void resetMock(){
        when(mMockList.size()).thenReturn(10);
        when(mMockList.get(anyInt())).thenReturn("one");

        System.out.println(mMockList.size());
        System.out.println(mMockList.get(0));

        reset(mMockList);

        System.out.println(mMockList.size());
        System.out.println(mMockList.get(0));
    }

    @Test
    public void timeOut() throws InterruptedException {
        mMockList.add("one");
        System.out.println(System.currentTimeMillis());
        verify(mMockList,timeout(10000)).add("one");
        System.out.println(System.currentTimeMillis());
    }

    @Test
    public void mockOrSpy(){
       boolean isMock =  Mockito.mockingDetails(mMockList).isMock();

       boolean isSpy =  Mockito.mockingDetails(mMockList).isSpy();

        System.out.println("--isMock = "+isMock+"   isSpy = "+isSpy);
    }



    @Mock
    StoryDao mStoryao2;

    @Mock
    StoryDao mStoryao;

    @InjectMocks
    StoryLocalDataSource mStoryLocalDataSource;

    @Test
    public void injectMocksTest(){
        when(mStoryao.getStoryById(1)).thenReturn(new Story(1,"injectMocksTest","www.baidu.com"));
        when(mStoryao2.getStoryById(1)).thenReturn(new Story(2,"injectMocksTest","www.baidu.com"));
        System.out.println(mStoryLocalDataSource.getStoryById(1).toString());
    }







}
