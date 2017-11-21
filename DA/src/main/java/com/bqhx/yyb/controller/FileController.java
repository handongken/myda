package com.bqhx.yyb.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.dao.UserMapper;
import com.bqhx.yyb.util.FileUtil;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.UserVO;


@RestController
@RequestMapping("/")
public class FileController {
	private Workbook wb;
	@Autowired
	private OrganizationMapper organizationMapper;
	@Autowired
	private UserMapper userMapper;
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
	
	@RequestMapping(value = "/uploadExcelFile", method = RequestMethod.POST)
	  public void uploadExcelFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) {
		// 获取上传文件的路径1
	    String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
	    // 截取上传文件的文件名
	    String uploadFileName = uploadFilePath.substring(
	        uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
	    try {
	    	InputStream is = multiReq.getFile("file1").getInputStream();
			 //2003版本
			if(uploadFilePath.endsWith("xls")){
				wb = new HSSFWorkbook(is);
			}//2007版本及以上
			else if(uploadFilePath.endsWith("xlsx")){
				wb = new XSSFWorkbook(is);
			}
			FileUtil fileUtil = new FileUtil();
			List<String[]> dataList = fileUtil.readExcelFile(wb,organizationMapper,userMapper);
			if(is != null){
				is.close();
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取文件不存在！请检查");
		}
//	    MessageVO message = new MessageVO();
//	    message.setMessage(uploadFilePath);
//	    return message;
	}
}
