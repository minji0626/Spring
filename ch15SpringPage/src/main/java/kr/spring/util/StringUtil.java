package kr.spring.util;

public class StringUtil {
	//HTML 태그를 허용하면서 줄바꿈
	public static String useBrHtml(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	//HTML 태그를 허용하지 않으면서 줄바꿈
	public static String useBrNoHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;")
				  .replaceAll(">", "&gt;")
				  .replaceAll("\r\n", "<br>")
				  .replaceAll("\r", "<br>")
				  .replaceAll("\n", "<br>");
	}
	
	//HTML를 허용하지 않음
	public static String useNoHTML(String str) {
		if(str == null) return null;
		
		return str.replaceAll("<", "&lt;").replaceAll(">", "&gt;");
	}
	
	//큰 따옴표 처리
	public static String parseQuot(String str) {
		if(str == null) return null;
		
		return str.replaceAll("\"", "&quot;");
	}
	
	// 비밀번호 생성하기
	public static String randomPassword(int length){
		int index = 0;
		char[] charSet = new char[]{
				'0','1','2','3','4','5','6','7','8','9'
				,'A','B','C','D','E','F','G','H','I','J','K','L','M'
				,'N','O','P','Q','R','S','T','U','V','W','X','Y','Z'
				,'a','b','c','d','e','f','g','h','i','h','k','l','m'
				,'n','o','p','q','r','s','t','u','v','w','x','y','z'
		};

		StringBuffer sb = new StringBuffer();

		for(int i=0;i<length;i++){
			index = (int)(charSet.length * Math.random());
			sb.append(charSet[index]);
		}

		return sb.toString();
	}
	
}








