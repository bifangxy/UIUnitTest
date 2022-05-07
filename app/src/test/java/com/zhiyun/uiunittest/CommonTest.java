package com.zhiyun.uiunittest;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.ArgumentMatchers.argThat;
import static org.mockito.ArgumentMatchers.isNotNull;
import static org.mockito.Mockito.atLeast;
import static org.mockito.Mockito.atMost;
import static org.mockito.Mockito.doCallRealMethod;
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

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InOrder;
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

        System.out.println(mMockList.get(0));
//        System.out.println(mMockList.get(1));
//        System.out.println(mMockList.get(999));
    }


    /**
     * 参数匹配
     */
    @Test
    public void paramsMatch() {
        when(mMockList.get(anyInt())).thenReturn("element");
        when(mMockList.contains(anyString())).thenReturn(true);
        assertTrue(mMockList.contains("das"));
        System.out.println(mMockList.get(12));
    }


    /**
     * 为void方法抛出异常
     */
    @Test
    public void voidThrow(){
        doThrow(new RuntimeException()).when(mMockList).clear();
        mMockList.clear();
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

        inOrder.verify(firstMock).add("first");
//        inOrder.verify(firstMock,never()).add("one");
//        inOrder.verify(secondMock,never()).add("one");
        inOrder.verify(secondMock).add("second");
    }


    /**
     * 验证mock对象没有产生过交互
     */
    @Test
    public void interactionNeverHappened(){
        verifyNoInteractions(mMockList);

        mMockList.add("one");
        verify(mMockList).add("one");
//        verifyNoMoreInteractions(mMockList);

        verifyNoInteractions(mMockList);
    }

    /**
     * 查找是否有未验证的交互
     */
    @Test
    public void findingRedundantInvocations(){
        mMockList.add("one");
        mMockList.add("two");
        verify(mMockList,times(2)).add(anyString());
//        verify(mMockList).add(anyString());
        verifyNoMoreInteractions(mMockList);
    }


    /**
     * 根据调用顺序设置不同的stubbing
     */
    @Test
    public void stubbingConsecutiveCalls() {
//        when(mMockList.get(anyInt())).thenThrow(new RuntimeException())
//                .thenReturn("result");

        when(mMockList.get(anyInt())).thenReturn("1").thenReturn("2");
//        mMockList.get(0);
        System.out.println(mMockList.get(1));
        System.out.println(mMockList.get(2));
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


        doNothing()
                .doThrow(new RuntimeException())
                .when(mMockList).clear();
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
                return null;
            }
        }).when(mMockList).get(0);
    }


    /**
     * spy
     */
    @Test
    public void spyTest(){
        List list = new LinkedList();
        List spy  = Mockito.spy(list);

        when(spy.size()).thenReturn(100);
        spy.add("one");
        spy.add("two");

        System.out.println(spy.get(0));

        System.out.println(spy.size());
    }


    /**
     * 为未stub的方法设置默认返回值
     */
    @Test
    public void returnDefault(){
        LinkedList<String> list = mock(LinkedList.class,Mockito.RETURNS_SMART_NULLS);

        LinkedList<String> list1 = mock(LinkedList.class, invocation -> {
//            Class<?> returnType = invocation.getMethod().getReturnType();
//            System.out.println(returnType);
//            if(returnType == Object.class ){
//                return "one";
//            }else if (returnType == int.class){
//                return 1;
//            }
//            return null;
            return "one";
        });
//        when(list1.get(1)).thenReturn("two");
//        System.out.println(list1.get(1));
        System.out.println(list1.get(2));
//        System.out.println(list1.indexOf("das"));
    }


    /**
     * 参数捕捉
     */
    @Test
    public void capturingArguments(){
        ArgumentCaptor<String> argumentCaptor = ArgumentCaptor.forClass(String.class);
        mMockList.add("one");
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
    public void timeOut(){
        mMockList.add("one");
        verify(mMockList,timeout(100)).add("one");

    }

    @Test
    public void mockOrSpy(){
       boolean isMock =  Mockito.mockingDetails(mMockList).isMock();

       boolean isSpy =  Mockito.mockingDetails(mMockList).isSpy();

        System.out.println("--isMock = "+isMock+"   isSpy = "+isSpy);
    }









}
