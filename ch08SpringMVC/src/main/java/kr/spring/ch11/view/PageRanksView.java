package kr.spring.ch11.view;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.web.servlet.view.document.AbstractXlsView;

import kr.spring.ch11.vo.PageRank;

public class PageRanksView extends AbstractXlsView{
// 엑셀 파일을 다운 받을 수 있는 abstractxlsview를 상속받도록한다

	@Override
	protected void buildExcelDocument(Map<String, Object> model, Workbook workbook, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		// 메소드 단위로 명시하기! (코드 읽기가 쉬워짐)
		// 시트 생성
		HSSFSheet sheet = createFirstSheet((HSSFWorkbook) workbook);
		
		// 열 이름 생성
		createColumnLabel(sheet);
		
		// 표시할 데이터 생성 
		List<PageRank> pageRanks = (List<PageRank>)model.get("pageRank");
		
		// 0번은 이미 사용되었기 때문에 1번부터 시작할 수 있도록 지정
		int rowNum = 1;
		for(PageRank rank : pageRanks) {
							// 시트 불러오고, 자바빈 불러오고, rowNum은 증가해야하기 때문에 ++도 함께 표시
			createPageRankRow(sheet, rank, rowNum++);
		}
		// 현재 메소드에서 알아서 다 처리 해주기 때문에 스트림 처리는 할 필요가 없음
			
		String fileName = "pageRanks2024.xls";
		response.setHeader("Content-Disposition", "attachment;filename=\"" +fileName+ "\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
	}

	
	// 시트 생성
	private HSSFSheet createFirstSheet(HSSFWorkbook workbook) {
		// sheet 객체 생성
		HSSFSheet sheet = workbook.createSheet();
		
		// sheet 이름 지정   // sheet index, 이름
		workbook.setSheetName(0, "페이지 순위");
		
		// 특정 컬럼에 넓이를 지정
						// column index, width
		sheet.setColumnWidth(1, 256*20);
		
		return sheet;
	}
	
	// 열 이름 생성 -> 생성된 시트를 받아서 열 이름 생성
	private void createColumnLabel(HSSFSheet sheet) {
		HSSFRow firstRow = sheet.createRow(0);
		HSSFCell cell = firstRow.createCell(0);
		cell.setCellValue("순위");
		
		cell=firstRow.createCell(1);
		cell.setCellValue("페이지");
	}
	
	// 표시할 데이터 생성								// 순서값을 받아 순서에 맞게 데이터 정렬
	private void createPageRankRow(HSSFSheet sheet, PageRank rank, int rowNum) {
		HSSFRow row = sheet.createRow(rowNum);
		HSSFCell cell = row.createCell(0);
		cell.setCellValue(rank.getRank());
		
		cell = row.createCell(1);
		cell.setCellValue(rank.getPage());
	}
	
}
