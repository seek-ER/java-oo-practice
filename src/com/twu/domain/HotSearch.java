package com.twu.domain;

/**
 *
 */
public class HotSearch {
    private int rank;
    private String content;
    private int myTicket;
    private int weight = 1; //投票的权重系数
    private int myMoney;

    public HotSearch() {
    }

    public HotSearch(int rank,String content, int myTicket) {
        this.rank = rank;
        this.content = content;
        this.myTicket = myTicket;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getMyTicket() {
        return myTicket;
    }

    public void setMyTicket(int myTicket) {
        this.myTicket = myTicket;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getMyMoney() {
        return myMoney;
    }

    public void setMyMoney(int myMoney) {
        this.myMoney = myMoney;
    }

    @Override
    public String toString() {
        return "HotSearch{" +
                "rank=" + rank +
                ", content='" + content + '\'' +
                ", myTicket=" + myTicket +
                ", weight=" + weight +
                ", myMoney=" + myMoney +
                '}';
    }
}
