package com.twu.function;

import com.twu.domain.HotSearch;

import java.util.Map;

/**
 * 管理员的特有方法
 */
public class AdminFunction extends CommonFunction{
    /**
     * 添加超级热搜
     */
    public static void insertSuperHotSearch(Map<String, HotSearch> hotSearchMap){
        System.out.println("请输入你要添加的超级热搜的名字");
        String hotSearchContent = scanner.nextLine();

        if (hotSearchMap.containsKey(hotSearchContent)){
            System.out.println("该热搜已经存在，你确定要将该热搜设置为超级热搜吗？确定请输入[yes]，否则回车");
            String confirm = scanner.nextLine();
            if ("yes".equalsIgnoreCase(confirm)){
                hotSearchMap.get(hotSearchContent).setWeight(2);
            }
            return;
        }

        HotSearch hotSearch = new HotSearch();
        hotSearch.setContent(hotSearchContent);
        hotSearch.setRank(hotSearchMap.size()+1);
        hotSearch.setWeight(2);

        hotSearchMap.put(hotSearchContent,hotSearch);
    }
}
