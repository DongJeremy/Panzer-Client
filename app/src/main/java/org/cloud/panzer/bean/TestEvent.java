package org.cloud.panzer.bean;

public class TestEvent {
    long id;
    String name;
    public TestEvent(long id,String name) {
        this.id= id;
        this.name= name;
    }
    public long getId() {
        return id;
    }
    public String getName() {
        return name;
    }
}
