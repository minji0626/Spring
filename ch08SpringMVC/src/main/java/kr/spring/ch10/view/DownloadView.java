package kr.spring.ch10.view;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.FileCopyUtils;
import org.springframework.web.servlet.view.AbstractView;

public class DownloadView extends AbstractView{
// 실제로 뷰가 아니기 때문에 상속받아서 작성해야 한다
	
	// content 타입 지정해줘야함(문서 타입)
	// 파일의 종류(지정한 content type)를 설정하는 것 - 다운로드가 가능해짐
	// 넓은 범주(application) / 좁은 범주(download)
	public DownloadView() {
		setContentType("application/download;charset=utf-8");
	}
	
	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		 // 다운로드 하는 파일의 경로 정보가 저장된 File 객체 반환(from model)
		 // model에서 저장했던 속성
		 File file = (File)model.get("downloadFile");
		 
		 // Content Type 지정 - 위에서 지정해준 타입 불러옴
		 response.setContentType(getContentType());
		 
		 // 해당 content의 용량 지정 - 파일 길이
		 response.setContentLength((int) file.length());
		 
		 // 스트림 - 목적지(client)로부터 파일을 읽을 때 생성
		 // 파일로부터 정보를 가져올 때 file input stream
		 // 파일을 생성해줄 때 file ouput stream
		 // 경로로부터 파일을 읽어오기 때문에 스트림을 생성해줘야한다
		 // 파일 명 구하기
		 // getBytes로 가져올 때는 utf-8, 전체적으로는 iso-8859-1 사용
		 String fileName = new String(file.getName().getBytes("utf-8"),"iso-8859-1");
		 
		 // HTTP 응답 메세지 해더 세팅
		 // Content-Disposition은 HTTP Response Body에 오는 컨텐츠의 기질/성향을 알려주는 속성
		 // Content-Disposition에 attachment를 주는 경우, Body에 오는 값을 다운로드 받으라는 뜻
		 // "fileName" -> 큰 따옴표가 특수문자가 아닌 일반 문자로 보이기 위해 '\'삽입
		 response.setHeader("Content-Disposition", "attachment;filename=\"" +fileName+ "\";");
		 
		 //Content-Transfer-Encoding 는 전송되는 데이터의 안의 내용물들의 인코딩 방식
		 response.setHeader("Content-Transfer-Encoding", "binary");
		 
		 // 파일을 읽어와서 보낼 때 output Stream 사용(파일 쓰기)
		 OutputStream out = response.getOutputStream();
		 
		 // 파일을 읽어오는 과정 - 이미 위에 예외 던지는게 있기 때문에 catch는 생성하지 않음
		 FileInputStream fis = null;
		 try {
			 	// 경로로부터 파일을 읽어옴
			 	fis = new FileInputStream(file);
			
			 	// inputstream에 있는걸 outputstream으로 쉽게 넘길 수 있음
			 	// 읽은 정보(fis)를 쓰기 정보(out)로 변환시켜 준다
			 	FileCopyUtils.copy(fis, out);
			 	
		} finally {
			if(fis != null) try {fis.close();} catch (IOException e) {}
		}
		
		 // 전송 작업 진행 (파일 전송)
		 out.flush();
		 
	}

}
