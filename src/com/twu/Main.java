package com.twu;

import com.twu.domain.Admin;
import com.twu.domain.CommonUser;
import com.twu.domain.HotSearch;

import java.util.*;

public class Main {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<String, HotSearch> hotSearchMap = new HashMap<>();// 热搜排行榜
        Map<String,CommonUser> userMap = new HashMap<>(); // 用户记录Map
        System.out.println("欢迎来到热搜排行榜，你是？\n1.用户\n2.管理员\n3.退出");
        String accountType = scanner.nextLine();
        while (true){
            while(!Arrays.asList(new String[]{"1","2","3"}).contains(accountType)){
                System.out.println("输入有误，请重新输入。");
                accountType = scanner.nextLine();
            }
            if (Arrays.asList(new String[]{"1","2","3"}).contains(accountType)){
                while (Integer.parseInt(accountType) != 3){
                    if (Integer.parseInt(accountType) == 1){
                        System.out.println("请输入您的昵称：");
                        String userName = scanner.nextLine();
                        CommonUser user;
                        if (userMap.containsKey(userName)){//如果是老用户，则从用户列表中找出
                            user = userMap.get(userName);
                        }else {// 如果是新用户，则创建并放入用户列表
                            user = new CommonUser();
                            user.setName(userName);
                            userMap.put(userName,user);
                        }

                        System.out.println("你好，"+user.getName()+"你可以：\n1.查看热搜排行榜\n2.给热搜事件投票\n3.购买热搜\n4.添加热搜\n5.退出");
                        String job = scanner.nextLine();

                        while(!Arrays.asList(new String[]{"1","2","3","4","5"}).contains(job)){
                            System.out.println("输入有误，请重新输入。");
                            job = scanner.nextLine();
                        }
                        while (Integer.parseInt(job) != 5){
                            user.setJob(Integer.parseInt(job));
                            user.doJob(hotSearchMap);
                            System.out.println("你好，"+user.getName()+"你可以：\n1.查看热搜排行榜\n2.给热搜事件投票\n3.购买热搜\n4.添加热搜\n5.退出");
                            job = scanner.nextLine();
                        }
                    }else if (Integer.parseInt(accountType) == 2){
                        System.out.println("请输入您的昵称：");
                        String adminName = scanner.nextLine();
                        System.out.println("请输入您的密码：");
                        String adminPassword = scanner.nextLine();
                        while (!("admin".equals(adminName) && "admin123".equals(adminPassword))){
                            System.out.println("你输入的管理员昵称或者密码错误，请重新输入！");
                            System.out.println("请输入您的昵称：");
                            adminName = scanner.nextLine();
                            System.out.println("请输入您的密码：");
                            adminPassword = scanner.nextLine();
                        }
                        Admin admin = new Admin();
                        admin.setName(adminName);
                        System.out.println("你好，"+admin.getName()+"你可以：\n1.查看热搜排行榜\n2.添加热搜\n3.添加超级热搜\n4.退出");

                        String job = scanner.nextLine();

                        while(!Arrays.asList(new String[]{"1","2","3","4"}).contains(job)){
                            System.out.println("输入有误，请重新输入。");
                            job = scanner.nextLine();
                        }
                        while (Integer.parseInt(job) != 4){
                            admin.setJob(Integer.parseInt(job));
                            admin.doJob(hotSearchMap);
                            System.out.println("你好，"+admin.getName()+"你可以：\n1.查看热搜排行榜\n2.添加热搜\n3.添加超级热搜\n4.退出");
                            job = scanner.nextLine();
                        }
                    }
                    System.out.println("欢迎来到热搜排行榜，你是？\n1.用户\n2.管理员\n3.退出");
                    accountType = scanner.nextLine();
                    if (!Arrays.asList(new String[]{"1","2","3"}).contains(accountType)){
                        break;
                    }
                }
                if (Integer.parseInt(accountType) == 3){
                    return;
                }
            }
        }
    }
}
