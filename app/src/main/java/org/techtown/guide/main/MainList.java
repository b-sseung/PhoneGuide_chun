package org.techtown.guide.main;

public class MainList {

    String name;
    String learn;

    public MainList(String name) {
        this.name = name;
        this.learn = "아직 배우지 않았습니다.";
    }

    public MainList(String name, String learn){
        this.name = name;
        this.learn = learn;
    }

    public void setName(String name){
        this.name = name;
    }

    public String getName(){
        return name;
    }

    public void setLearn(String learn){
        this.learn = learn;
    }

    public String getLearn(){
        return learn;
    }
}
