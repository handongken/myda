package com.bqhx.yyb.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;


@RestController
@RequestMapping("/")
public class FileController {

	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	  public void testUploadFile(HttpServletRequest req,
	      MultipartHttpServletRequest multiReq) {
	    // 获取上传文件的路径1
	    String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
	    System.out.println("uploadFlePath:" + uploadFilePath );
	    // 截取上传文件的文件名
	    String uploadFileName = uploadFilePath.substring(
	        uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
	    System.out.println("multiReq.getFile()" + uploadFileName);
	    // 截取上传文件的后缀
	    String uploadFileSuffix = uploadFilePath.substring(
	        uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
	    System.out.println("uploadFileSuffix:" + uploadFileSuffix);

	    FileInputStream fis = null;
	    BufferedReader br = null;
	    try {
	      fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
	      br = new BufferedReader(new InputStreamReader(fis));
	      String tmp = null;
	      while((tmp= br.readLine())!=null) {
	    	  System.out.println(tmp);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (br != null) {
 	        try {
 	          br.close();
 	        } catch (IOException e) {
 	          e.printStackTrace();
 	        }
 	      }
	      if (fis != null) {
	        try {
	          fis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	  }
	
	@RequestMapping(value = "/download", method = RequestMethod.GET)
	  public void testDownload(HttpServletResponse res) {
	    String fileName = "upload.jpg";
	    res.setHeader("content-type", "application/octet-stream");
	    res.setContentType("application/octet-stream");
	    res.setHeader("Content-Disposition", "attachment;filename=" + fileName);
	    byte[] buff = new byte[1024];
	    BufferedInputStream bis = null;
	    OutputStream os = null;
	    try {
	      os = res.getOutputStream();
	      bis = new BufferedInputStream(new FileInputStream(new File("d://"
	          + fileName)));
	      int i = bis.read(buff);
	      while (i != -1) {
	        os.write(buff, 0, buff.length);
	        os.flush();
	        i = bis.read(buff);
	      }
	    } catch (IOException e) {
	      e.printStackTrace();
	    } finally {
	      if (bis != null) {
	        try {
	          bis.close();
	        } catch (IOException e) {
	          e.printStackTrace();
	        }
	      }
	    }
	    System.out.println("success");
	  }
}
