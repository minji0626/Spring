package kr.spring.view;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.view.AbstractView;

@Component
public class imageView extends AbstractView{

	@Override
	protected void renderMergedOutputModel(Map<String, Object> model, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		
		// 바이트 배열에 모델에 저장해둔 imageFile을 저장시킨다
		byte[] file = (byte[]) model.get("imageFile");
		// filename도 저장시켰기 때문에 불러온다
		String filename = (String)model.get("filename");
		
		String ext = filename.substring(filename.lastIndexOf("."));
		
		if(ext.equalsIgnoreCase(".gif")) {
			ext="image/gif";
		} else if(ext.equalsIgnoreCase(".png")) {
			ext = "image/png";
		} else {
			ext="image/jpeg";
		}
		
		response.setContentType(ext);
		response.setContentLength(file.length);
		
		String file_name = new String(filename.getBytes("utf-8"),"iso-8859-1");
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" + file_name +"\";");
		response.setHeader("Content-Transfer-Encoding", "binary");
		
		OutputStream out = response.getOutputStream();
		InputStream input = null;
		
		try {
			input = new ByteArrayInputStream(file);
			IOUtils.copy(input, out);
			out.flush();
		} finally {
			if(out != null) out.close();
			if(input != null) input.close();
		}
	}

}
