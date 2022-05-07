package com.zhiyun.uiunittest.data.entity;

/**
 * Describe:
 * Created by xieying on 2022/5/6
 */
public class Story {

    public Story(int id, String name, String url) {
        this.id = id;
        this.name = name;
        this.url = url;
    }

    private int id;

    private String name;

    private String url;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    @Override
    public String toString() {
        return "Story{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", url='" + url + '\'' +
                '}';
    }
}
