package com.twu.domain;

import com.twu.function.AdminFunction;

import java.util.Map;

/**
 * 管理员
 */
public class Admin {
    private String name;
    private String password;
    private int job;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public int getJob() {
        return job;
    }

    public void setJob(int job) {
        this.job = job;
    }

    public void doJob(Map<String,HotSearch> hotSearchMap) {
        if (1 == job){
            AdminFunction.showAll(hotSearchMap);
        } else if (2 ==job){
            AdminFunction.insertHot(hotSearchMap);
        } else if (3 == job){
            AdminFunction.insertSuperHotSearch(hotSearchMap);
        }
    }
}
