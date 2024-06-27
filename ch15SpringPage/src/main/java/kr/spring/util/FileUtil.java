package kr.spring.util;

import java.io.FileInputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class FileUtil {
	// 지정한 경로의 파일을 읽어들여 byte 배열로 변환해준다
	public static byte[] getBytes(String path) {
		FileInputStream fis = null;
		byte[] readbyte = null;
		try {
			fis = new FileInputStream(path);
			readbyte = new byte[fis.available()];
			fis.read(readbyte);
		} catch (Exception e) {
			log.error(e.toString());
		} finally {
			if(fis != null) try {fis.close();}catch (IOException e) {}
		}
		
		return readbyte;
	}
}
