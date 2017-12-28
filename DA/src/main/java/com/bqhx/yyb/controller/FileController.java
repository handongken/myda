package com.bqhx.yyb.controller;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;
import java.util.zip.ZipOutputStream;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.log4j.Logger; 
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.service.FileService;
import com.bqhx.yyb.service.InformationService;
import com.bqhx.yyb.service.OrganizationService;
import com.bqhx.yyb.service.TypeService;
import com.bqhx.yyb.service.UserService;
import com.bqhx.yyb.util.DateUtil;
import com.bqhx.yyb.util.FileUtil;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.FileVO;
import com.bqhx.yyb.vo.MessageVO;
import com.bqhx.yyb.vo.UserVO;


@RestController
@RequestMapping("/")
public class FileController {
	private Workbook wb;
	
	private Logger logger = Logger.getLogger(FileController.class);
	@Value("${uploadfiles.path}")
	private String uploadfilespath;
	@Value("${uploadfiles.url}") 
	private String uploadfilesurl;
	@Autowired
	@Qualifier("FileServiceImpl") 
	private FileService fileService;
	@Autowired
	@Qualifier("InformationServiceImpl") 
	private InformationService informationService;
	@Autowired
	@Qualifier("OrganizationServiceImpl") 
	private OrganizationService organizationService;
	@Autowired
	@Qualifier("UserServiceImpl") 
	private UserService userService;
	@Autowired
	@Qualifier("TypeServiceImpl") 
	private TypeService typeService;
	
	/**
	 * test
	 * @param req
	 * @param multiReq
	 */
	@RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
	  public void testUploadFile(HttpServletRequest req,
	      MultipartHttpServletRequest multiReq) {
	    // 获取上传文件的路径1
	    String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
	    logger.info("uploadFlePath:" + uploadFilePath );
	    // 截取上传文件的文件名
	    String uploadFileName = uploadFilePath.substring(
	        uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));
	    logger.info("multiReq.getFile()" + uploadFileName);
	    // 截取上传文件的后缀
	    String uploadFileSuffix = uploadFilePath.substring(
	        uploadFilePath.indexOf('.') + 1, uploadFilePath.length());
	    logger.info("uploadFileSuffix:" + uploadFileSuffix);

	    FileInputStream fis = null;
	    BufferedReader br = null;
	    try {
	      fis = (FileInputStream) multiReq.getFile("file1").getInputStream();
	      br = new BufferedReader(new InputStreamReader(fis));
	      String tmp = null;
	      while((tmp= br.readLine())!=null) {
	    	  logger.info(tmp);
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
	
	/**
	 * test
	 * @param res
	 */
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
	    logger.info("success");
	  }
	
	/**
	 * 上传用户架构excel
	 * @param req
	 * @param multiReq
	 */
	@RequestMapping(value = "/uploadOrgExcelFile", method = RequestMethod.POST)
	  public void uploadOrgExcelFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) {
		String userId = multiReq.getParameter("userId");
		// 获取上传文件的路径1
	    String uploadFilePath = multiReq.getFile("file1").getOriginalFilename();
	    /*// 截取上传文件的文件名
	    String uploadFileName = uploadFilePath.substring(
	        uploadFilePath.lastIndexOf('\\') + 1, uploadFilePath.indexOf('.'));*/
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
			fileUtil.readOrgExcelFile(wb,organizationService,userService,userId);
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
	}
	
	/**
	 * 上传数据总表Excel
	 * @param req
	 * @param multiReq
	 */
	@RequestMapping(value = "/uploadDataExcelFile", method = RequestMethod.POST)
	  public void uploadDataExcelFile(HttpServletRequest req, MultipartHttpServletRequest multiReq) {
		// 获取上传文件name
	    String uploadFileName = multiReq.getFile("file2").getOriginalFilename();
	    String userId = multiReq.getParameter("userId");
	    try{
	    	InputStream is = multiReq.getFile("file2").getInputStream();
	    	 //2003版本
			if(uploadFileName.endsWith("xls")){
				wb = new HSSFWorkbook(is);
			}//2007版本及以上
			else if(uploadFileName.endsWith("xlsx")){
				wb = new XSSFWorkbook(is);
			}
			FileUtil fileUtil = new FileUtil();
			List<ConditionVO> conlist = fileUtil.readDataExcelFile(wb, informationService,typeService,organizationService,userId);
            if(conlist.size() != 0){
            	informationService.insertBatch(conlist);
            }
	    }catch(FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
			throw new RuntimeException("读取文件不存在！请检查");
		}
	}
	
	/**
	 * 上传文件(多文件)
	 * @param req
	 * @param multiReq
	 */
	@RequestMapping(value = "/uploadBatchFiles", method = RequestMethod.POST)
	public List<FileVO> uploadBatchFiles(HttpServletRequest req, MultipartHttpServletRequest multiReq,UserVO user,String contract) {
		List<FileVO> fileList = new ArrayList<FileVO>();
		List<MultipartFile> files =multiReq.getFiles("file");
		MultipartFile file = null;
		for (int i =0; i< files.size(); ++i) {
			file = files.get(i); 
			FileVO fileVO = new FileVO();
			if (!file.isEmpty()) {
				fileVO.setContract(contract);
				// 获得物理路径webapp所在路径
				String pathRoot = req.getSession().getServletContext().getRealPath("");
				logger.info("物理路径webapp: " + pathRoot);
				// 获取上传文件name
				String uploadFileName = file.getOriginalFilename();
				logger.info("上传文件name: " + uploadFileName);
				//文件后缀
				String fileType = uploadFileName.substring(uploadFileName.indexOf(".")+1);
				fileVO.setFileType(fileType);
				logger.info("文件后缀: " + fileType);
				// 重命名文件
				String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		        String newfileName = uuid + "_" + contract + "_" + getDate() + "_" + uploadFileName;
		        fileVO.setFileName(newfileName);
		        logger.info(newfileName);
//		        String path = pathRoot.substring(0,pathRoot.indexOf("DA"));
		        //显示url
		        fileVO.setFileUrl(uploadfilesurl + newfileName);
		        String uploadPath = uploadfilespath.substring(uploadfilespath.indexOf("D"));
		        logger.info("上传路径： " + uploadPath);
		        String filePath = uploadPath.replace("/", "\\") + newfileName;
		        fileVO.setFilePath(filePath);
		        logger.info("filePath: " + filePath);
		        //插入时间与用户
		        String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
		        fileVO.setInsDate(insDate);
		        fileVO.setInsUser(user.getUserId());
		        //插入t_file表
		        int code = fileService.insertFilePath(fileVO);
		        if(code == 1){
		        	 try {
							FileUtil.uploadFile(file.getBytes(), uploadPath, newfileName);
						} catch (IOException e) {
							e.printStackTrace();
							throw new RuntimeException("读取文件不存在！请检查");
						} catch (Exception e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
				        fileVO.setMessage(Constant.SUCCESS);
		        }else{
		        	fileVO.setMessage(Constant.FAILED);
		        }
			}else{
				fileVO.setMessage(Constant.FAILED);
			}
			fileList.add(fileVO);
		}
		return fileList;
	}
	
	/**
	 * 下载文件(单文件)
	 * @param req
	 * @param multiReq
	 */
	@RequestMapping(value = "/downloadFile", method = RequestMethod.POST)
	public MessageVO downloadFile(HttpServletResponse res,FileVO fileVO) {
		MessageVO message = new MessageVO();
		//在服务器端创建临时zip格式文件
		String uuid = UUID.randomUUID().toString().replaceAll("-", "");
		String zipTemp = uuid + "_" + fileVO.getContract() + ".zip";
		// 下载路径D:/uploadFiles/
		String path = uploadfilespath.substring(uploadfilespath.indexOf("D"));
		File fileZip = new File(path + zipTemp);
		// 文件输出流
		FileOutputStream outStream;
		try {
			outStream = new FileOutputStream(fileZip);
			// 压缩流
			ZipOutputStream toClient = new ZipOutputStream(outStream);
			// 要压缩的文件列表
			List<FileVO> fileList = selectFileByCondition(fileVO);
			for(int i = 0; i < fileList.size(); i++){
				FileVO file = fileList.get(i);
				String filePath = file.getFilePath();
				File inputFile = new File(filePath);
				FileUtil.zipFile(inputFile, toClient);
			}
			FileUtil.downloadFile(fileZip, res, true);
			outStream.close();
	//		toClient.close();
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		message.setCode(Constant.FLAG_ONE);
		message.setMessage(Constant.SUCCESS);
		return message;
	}
	
	/**
	 * 删除文件
	 */
	@RequestMapping(value = "/deleteFile", method = RequestMethod.POST)
	public boolean deleteFile(FileVO fileVO, String userId) {
//		String filePath = "D:" + "\\uploadFiles" + "\\2017-12-04_微信截图_20170822165945.png";
		String filePath = fileVO.getFilePath();
		logger.info(filePath);
		File file = new File(filePath);
		// 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
        	file.delete();
        	//删除t_file表中数据（delFlg=1）
        	FileVO tfile = new FileVO();
        	tfile.setDelFlg(Constant.FLAG_ONE);
        	tfile.setUpdUser(userId);
        	String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
        	tfile.setUpdDate(updDate);
        	tfile.setContract(fileVO.getContract());
        	tfile.setFileName(fileVO.getFileName());
        	fileService.updateFileByPrimaryKey(tfile);
            logger.info("删除单个文件" + filePath + "成功！");
            return true;
        } else {
        	logger.info("删除单个文件失败：" + filePath + "失败！");
            return false;
        }
	}
	
	/**
	 * 查看图片
	 * @return filePath
	 */
	@RequestMapping(value = "/selectFileByCondition", method = RequestMethod.POST)
	public List<FileVO> selectFileByCondition(FileVO file){
		List<FileVO> fileList = fileService.selectFileByCondition(file);
		return fileList;
	}
	
	private String getDate() {
		SimpleDateFormat sdf = new SimpleDateFormat(Constant.FILEPATTERN);
		return sdf.format(new Date());
	}
}
