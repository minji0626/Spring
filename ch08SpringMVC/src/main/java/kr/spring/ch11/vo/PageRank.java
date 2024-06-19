package kr.spring.ch11.vo;

public class PageRank {
	// 접속하는 횟수 DB 저장	
	private int rank;
	private String page;

	public PageRank() {}
	
	// 생성자 정의 
	public PageRank(int rank, String page) {
		this.rank = rank;
		this.page = page;
	}
	
	public int getRank() {
		return rank;
	}
	public void setRank(int rank) {
		this.rank = rank;
	}
	public String getPage() {
		return page;
	}
	public void setPage(String page) {
		this.page = page;
	}
	@Override
	public String toString() {
		return "PageRank [rank=" + rank + ", page=" + page + "]";
	}
	
	
}
