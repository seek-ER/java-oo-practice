package com.twu.function;

import com.twu.domain.CommonUser;
import com.twu.domain.HotSearch;

import java.util.*;

/**
 * 普通用户的特有方法
 */
public class UserFunction extends CommonFunction {
    static Scanner scanner = new Scanner(System.in);

    /**
     * 购买热搜
     */
    public static void buy(Map<String,HotSearch> hotSearchMap){
        Set<String> keySet = hotSearchMap.keySet();
        System.out.println("请输入你要购买的热搜：");
        String hotSearchWantToBuy = scanner.nextLine();
        while (!hotSearchMap.containsKey(hotSearchWantToBuy)){
            System.out.println("你输入的名称有误，请再输入一遍。");
            hotSearchWantToBuy = scanner.nextLine();
        }
        System.out.println("请输入你要购买的热搜排名");
        String hotSearchRankWantToBuy = scanner.nextLine();
        while (Integer.parseInt(hotSearchRankWantToBuy) >hotSearchMap.size()){
            System.out.println("你要购买的热搜排名超出热搜范围，请再输入一遍");
            hotSearchRankWantToBuy = scanner.nextLine();
        }
        System.out.println("请输入你要购买的金额");
        String buyMoney = scanner.nextLine();
        int originalRank = hotSearchMap.get(hotSearchWantToBuy).getRank(); // 被购买热搜原本的排名
        int myMoney = 0;//所要替换的热搜的myMoney
        for (String key : keySet){
            if (hotSearchMap.get(key).getRank() == Integer.parseInt(hotSearchRankWantToBuy)){
                myMoney = hotSearchMap.get(key).getMyMoney();
            }
        }

        if (Integer.parseInt(buyMoney)<=myMoney){ // 购买的money小于被替换位置热搜的money，证明被替换位置的热搜也是购买的，竞价失败
            System.out.println("购买失败。");
            return;
        }

        if (Integer.parseInt(buyMoney)>myMoney && myMoney==0){// 购买成功，且被替换热搜不是购买热搜
            for (String key: keySet){
                // 从hotSearchRankWantToBuy 到 originalRank 之间的非购买的热搜排名都加1
                if (hotSearchMap.get(key).getRank()>=Integer.parseInt(hotSearchRankWantToBuy) && hotSearchMap.get(key).getRank()<originalRank && hotSearchMap.get(key).getMyMoney()==0 )  {
                    hotSearchMap.get(key).setRank(hotSearchMap.get(key).getRank()+1);
                }
            }
            // 购买成功后，设置被购买热搜的rank以及myMoney
            hotSearchMap.get(hotSearchWantToBuy).setRank(Integer.parseInt(hotSearchRankWantToBuy));
            hotSearchMap.get(hotSearchWantToBuy).setMyMoney(Integer.parseInt(buyMoney));
            // 多次购买后，可能出现 从hotSearchRankWantToBuy 到 originalRank 之间的非购买的热搜排名都加1 的rank 和购买热搜的rank 重复了，保持购买热搜的rank不变，非购买热搜rank加1
            for (String key1: keySet){//防止rank重复了
                int rank1 = hotSearchMap.get(key1).getRank();
                for (String key2: keySet){
                    int rank2 = hotSearchMap.get(key2).getRank();
                    if (rank1 == rank2 && hotSearchMap.get(key1).getMyMoney()==0 && hotSearchMap.get(key2).getMyMoney()!=0 ){
                        hotSearchMap.get(key1).setRank(rank1+1);
                    }
                }
            }
            System.out.println("购买成功。");
        }

        if (Integer.parseInt(buyMoney)>myMoney && myMoney!=0){// 购买成功，且被替换热搜是购买热搜，则删除原本在该rank位置的热搜
            Iterator<Map.Entry<String, HotSearch>> iterator = hotSearchMap.entrySet().iterator();
            while (iterator.hasNext()){
                Map.Entry<String, HotSearch> entry = iterator.next();
                if (entry.getValue().getRank() == Integer.parseInt(hotSearchRankWantToBuy)){
                    iterator.remove();
                    break;
                }
            }

            for (String key:keySet){
                // 原本比originalRank还要大的热搜，由于删除了一个热搜，则rank-1
                if (hotSearchMap.get(key).getRank()>originalRank){
                    hotSearchMap.get(key).setRank(hotSearchMap.get(key).getRank()-1);
                }
            }

            // 购买成功后，设置被购买热搜的rank以及myMoney
            hotSearchMap.get(hotSearchWantToBuy).setRank(Integer.parseInt(hotSearchRankWantToBuy));
            hotSearchMap.get(hotSearchWantToBuy).setMyMoney(Integer.parseInt(buyMoney));
            System.out.println("购买成功。");
        }

    }

    /**
     * 给热搜事件投票
     */
    public static void vote(CommonUser user, Map<String,HotSearch> hotSearchMap){
        int ticketBefore = user.getTicket();
        System.out.println("请输入你要投票的热搜名称：");
        String hotSearchNameNeedVote = scanner.nextLine();
        // 输入的热搜名称有误，重新输入
        /*boolean flag = false;
        while (flag =false){
            for (HotSearch hotSearch : hotSearchList){
                if (hotSearch.getContent().equals(hotSearchNeedVote)){
                    flag = true;
                    break;
                }
            }
            if (flag = false){
                System.out.println("你输入的名称有误，请再输入一遍。");
                hotSearchNeedVote = scanner.nextLine();
            }
        }*/
        while (!hotSearchMap.containsKey(hotSearchNameNeedVote)){
            System.out.println("你输入的名称有误，请再输入一遍。");
            hotSearchNameNeedVote = scanner.nextLine();
        }
        System.out.println("请输入你要投票的热搜票数： （你目前还有: "+ticketBefore+"票)");
        int voteTicket = scanner.nextInt();
        scanner.nextLine();//nextInt后面需要换行
        // 投票数大于现有票数，投票失败
        if (voteTicket>ticketBefore){
            System.out.println("投票失败");
            return;
        }
        // 更新热搜排行榜，即更新对应热搜的票数 || weight为设置的权重，超级热搜为2
        HotSearch hotSearchNeedVote = hotSearchMap.get(hotSearchNameNeedVote);
        int beforeTickets = hotSearchMap.get(hotSearchNameNeedVote).getMyTicket();
        int weight = hotSearchMap.get(hotSearchNameNeedVote).getWeight();
        hotSearchNeedVote.setMyTicket(voteTicket*weight+beforeTickets);

        // 更新票数后更改对应的rank
        /*
        基本思路
             1、将所有热搜分为购买热搜和非购买热搜；
                 1.1、购买热搜的map以购买的rank作为键，对应的HotSearch作为值
                 1.2、非购买热搜仍以HotSearch的内容为键，对应的HotSearch作为值
             2、将非购买热搜按照票数进行降序排序，形成针对非购买热搜的List
             3、新建一个大小为所有热搜数量的List
                3.1、对于List的每一个位置，如果该位置是购买热搜的位置，则从购买热搜的map中取出该元素并添加到这个List中
                3.2、如果该位置不是购买热搜的位置，则从非购买热搜的List中依次取出元素进行填充
             4、按照List的顺序更新所有热搜的Rank
             5、覆盖原来的HotSearchMap
        */
        Map<Integer, HotSearch> buyedHotSearchMap = new HashMap<>();
        Map<String,HotSearch> notBuyedHotSearchMap = new HashMap<>();
        for (HotSearch hotSearch : hotSearchMap.values()){
            if (hotSearch.getMyMoney()!=0){
                buyedHotSearchMap.put(hotSearch.getRank(),hotSearch );
            }else {
                notBuyedHotSearchMap.put(hotSearch.getContent(),hotSearch);
            }
        }

        //这里将map.entrySet()转换成list
        List<Map.Entry<String,HotSearch>> list = new ArrayList<>(notBuyedHotSearchMap.entrySet());
        //然后通过比较器来实现排序
        //根据Ticket降序排列
        list.sort((o1, o2) -> {// lambda
            String s1 = String.valueOf(o2.getValue().getMyTicket());
            String s2 = String.valueOf(o1.getValue().getMyTicket());
            return s1.compareTo(s2);
        });

        ArrayList<HotSearch> orderedList = new ArrayList<>();
        for (int i=1, j=0; i<=hotSearchMap.size() && j<list.size();i++){
            if (buyedHotSearchMap.containsKey(i)){
                orderedList.add(buyedHotSearchMap.get(i));
            }else {
                orderedList.add(list.get(j).getValue());
                j++;
            }
        }

        Map<String,HotSearch> updatedRankHotSearchMap = new HashMap<>();
        for (int i =0; i<orderedList.size();i++){
            orderedList.get(i).setRank(i+1);
            updatedRankHotSearchMap.put(orderedList.get(i).getContent(),orderedList.get(i));
        }

        hotSearchMap = updatedRankHotSearchMap;


        /*            *//*
            基本思路
                1.除了被投票的热搜，其他的热搜排名变化只能为0 或者 +1；
                2.购买的热搜和排名原本就比被投票的热搜低的热搜排名不会变化；

                3.投票后，将所有的热搜按照票数降序排序，得到一个List；
                    按照顺序找到被投票的热搜，记录排名的当前的遍历顺序，记为 expectedRank；
                    在被投票的热搜后面若有一个购买热搜，而且，
                    3.1 排序后，在被投票热搜前的热搜的排名不会变化；
                    3.2 在被投票热搜后的热搜如果原本排名比被投票热搜的排名小而且是非购买的热搜，则排名加一；

                遍历该List，直到找到被投票的热搜
                    利用遍历的局部变量i记录此时的排名顺序，记为 expectedRank ；

            *//*

        //这里将map.entrySet()转换成list
        List<Map.Entry<String,HotSearch>> list = new ArrayList<Map.Entry<String, HotSearch>>(hotSearchMap.entrySet());
        //然后通过比较器来实现排序
        Collections.sort(list,new Comparator<Map.Entry<String,HotSearch>>() {
            //根据Ticket降序排列
            public int compare(Map.Entry<String, HotSearch> o1,
                               Map.Entry<String, HotSearch> o2) {
                String s1 = String.valueOf(o2.getValue().getMyTicket());
                String s2 = String.valueOf(o1.getValue().getMyTicket());
                return s2.compareTo(s1);
            }

        });
        int buyedRankBeforeExpectedRank = 0; //
        int expectedRank = 0;
        int beforeRankOfTheHotSearchNeedVote=0;
        for (int i =1; i<=list.size();i++){
            if (list.get(i).getValue().getContent().equals(hotSearchNeedVote)){
                beforeRankOfTheHotSearchNeedVote = list.get(i).getValue().getRank();
                expectedRank = i;
                for (int j = i+1; i<=list.size();j++){
                    if (list.get(j).getValue().getMyMoney() !=0 && list.get(j).getValue().getRank()<=i){ //获取该投票后的热搜 后面 热搜排名小于expectedRank且是购买的 热搜数
                        buyedRankBeforeExpectedRank++;
                    }
                    if (list.get(j).getValue().getMyMoney() == 0 && list.get(j).getValue().getRank() < beforeRankOfTheHotSearchNeedVote){// 该投票后的热搜 后面的非购买的且原来排名比该比该热搜小的热搜
                        int beforeRank = list.get(j).getValue().getRank();
                        list.get(j).getValue().setRank(beforeRank+1);
                    }
                }
                int realRankOfTheHotSearchNeedVote = expectedRank + buyedRankBeforeExpectedRank;
                list.get(i).getValue().setRank(realRankOfTheHotSearchNeedVote);
                break;
            }
        }*/


        // 刷新用户拥有的票数
        user.setTicket(ticketBefore-voteTicket);

        System.out.println("投票成功！");


    }
}
