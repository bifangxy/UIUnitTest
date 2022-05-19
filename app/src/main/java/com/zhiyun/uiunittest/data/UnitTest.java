package com.zhiyun.uiunittest.data;

/**
 * Describe:
 * Created by xieying on 2022/5/9
 */
public class UnitTest {

    private int count;

    private String describe;

    public UnitTest(int count, String describe) {
        this.count = count;
        this.describe = describe;
    }

    public int getDescribeCount(int count,String describe){
        return count;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }
}
