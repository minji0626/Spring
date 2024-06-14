package kr.spring.ch14;

public class WriteArticleService {
	// property
	private WriteArticleDAO writeArticleDAO;

	public void setWriteArticleDAO(WriteArticleDAO writeArticleDAO) {
		this.writeArticleDAO = writeArticleDAO;
	}

	@Override
	public String toString() {
		return "WriteArticleService [writeArticleDAO=" + writeArticleDAO + "]";
	}
	
	public void write() {
		System.out.println("WriteArticleService의 write() 메소드 실행");
		writeArticleDAO.insert();
	}
}
