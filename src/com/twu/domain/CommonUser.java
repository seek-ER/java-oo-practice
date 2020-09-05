package com.twu.domain;

import com.twu.function.UserFunction;

import java.util.List;
import java.util.Map;

/**
 * 普通用户
 */
public class CommonUser {
    private String name;
    private int job;
    private int ticket = 10;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public int getTicket() {
        return ticket;
    }

    public void setTicket(int ticket) {
        this.ticket = ticket;
    }

    public void doJob(Map<String,HotSearch> hotSearchMap) {
        if (1 == job){
            UserFunction.showAll(hotSearchMap);
        }else if (2 == job){
            UserFunction.vote(this,hotSearchMap);
        }else if (3 == job){
            UserFunction.buy(hotSearchMap);
        }else if (4 ==job){
            UserFunction.insertHot(hotSearchMap);
        }
    }
}
