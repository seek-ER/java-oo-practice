package com.twu.function;

import com.twu.domain.HotSearch;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 普通用户和管理员共有的方法
 */
public class CommonFunction {
    static Scanner scanner = new Scanner(System.in);

    /**
     * 查看热搜排行榜
     */
    public static void showAll(Map<String,HotSearch> hotSearchMap){
        //这里将map.entrySet()转换成list
        List<Map.Entry<String,HotSearch>> list = new ArrayList<Map.Entry<String, HotSearch>>(hotSearchMap.entrySet());

        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,HotSearch>>() {
            //按照rank升序排序
            public int compare(Map.Entry<String, HotSearch> o1,
                               Map.Entry<String, HotSearch> o2) {
                String s1 = String.valueOf(o2.getValue().getRank());
                String s2 = String.valueOf(o1.getValue().getRank());
                return s2.compareTo(s1);
            }

        });
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i).getValue().getRank()+"\t"+ list.get(i).getValue().getContent()+"\t"+list.get(i).getValue().getMyTicket());
        }

    }

    /**
     * 添加热搜
     */
    public static void insertHot(Map<String,HotSearch> hotSearchMap){
        System.out.println("请输入你要添加的热搜事件的名字");
        String hotSearchContent = scanner.nextLine();

        if (hotSearchMap.containsKey(hotSearchContent)){
            System.out.println("该热搜已经存在，不用重复添加。");
            return;
        }

        HotSearch hotSearch = new HotSearch();
        hotSearch.setContent(hotSearchContent);
        hotSearch.setRank(hotSearchMap.size()+1);

        hotSearchMap.put(hotSearchContent,hotSearch);
    }
}
