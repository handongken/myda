package com.bqhx.yyb.util;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.util.ResourceUtils;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.constant.ContinueFlgEnum;
import com.bqhx.yyb.constant.StatusEnum;
import com.bqhx.yyb.controller.FileController;
import com.bqhx.yyb.dao.FileMapper;
import com.bqhx.yyb.service.InformationService;
import com.bqhx.yyb.service.OrganizationService;
import com.bqhx.yyb.service.TypeService;
import com.bqhx.yyb.service.UserService;
import com.bqhx.yyb.vo.ConditionVO;
import com.bqhx.yyb.vo.FileVO;
import com.bqhx.yyb.vo.OrganizationCodeVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationVO;
import com.bqhx.yyb.vo.TypeVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserVO;

public class FileUtil {
	
	private static Logger logger = Logger.getLogger(FileController.class);
	@Value("${uploadfiles.url}") 
	private static String uploadfilesurl;
	@Value("${uploadfiles.path}")
	private static String uploadfilespath;
	/** 最大流为10M*/
	final static int MAX_BYTE = 10 * 1024 * 1024;
	/** 接受流的容量*/
	static long streamTotal = 0;
	/** 流需要分开的数量*/
	static int streamNum = 0;
	/** 文件剩下的字符数*/
	static int leaveByte = 0;
	/** byte数组接受文件的数据*/
	static byte[] inOutByte;
	/**
	 * 根据路径及文件名获取Excel文件
	 * @param uploadFilePath
	 * @param uploadFileName
	 */
	public static FileInputStream getFile(String filePath,String fileName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile(filePath+fileName));
    }
	/**
	 * 读取架构Excel并保存进数据库
	 * @param wb
	 * @param organizationMapper
	 * @param userMapper
	 */
	public  void readOrgExcelFile(Workbook wb,OrganizationService organizationService,UserService userService,String insUserId){
		String sybCode = "";
        String syb = "";
        String sybManager = "";
        String dqCode = "";
        String dq = "";
        String dqManager = "";
        String fgsCode = "";
        String fgs = "";
        String fgsManager = "";
        String yybCode = "";
        String yyb = "";
        String yybManager = "";
        String tdManager = "";
        String name = "";
        String userId = "";
        String tel = "";
        String post = "";//岗位名称
        String tdCode = "";//t_id
		 if(wb != null){
			 for(int sheetNum = 0;sheetNum < wb.getNumberOfSheets();sheetNum++){
				//获得当前sheet
				 Sheet sheet = wb.getSheetAt(sheetNum);//
				 if(sheet == null || !"入职".equals(sheet.getSheetName())){
                     continue;
                 }
                 //获得当前sheet的结束行
                 int lastRowNum = sheet.getLastRowNum();
                 //标题行数
                 int titleRowNum = getDataRowNumByName(sheet,"事业部编码");
                 //标题数据行
                 String[] titleDatas = getDatas(sheet,titleRowNum);
                 //获取要导入的数据行
                 int dataRowNum = getDataRowNumByTel(sheet);//4
                 //循环从数据行开始
                 for(int rowNum = dataRowNum;rowNum <= lastRowNum;rowNum++){
                	//获得当前行
                     Row row = sheet.getRow(rowNum);
                     if(row == null){
                         continue;
                     }
                   //获得当前行的开始列
                   int firstCellNum = row.getFirstCellNum();
                   //获得当前行的列数
                   int lastCellNum = row.getPhysicalNumberOfCells();
                   String[] cells = new String[row.getPhysicalNumberOfCells()];
                   //循环当前行所有列
                   for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
                       Cell cell = row.getCell(cellNum);
                       cells[cellNum] = getCellValue(cell);
                       
                       if("事业部编码".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   sybCode = "";
                    	   }else{
                    		   sybCode = getCellValue(cell);
                    	   }
                       }else if("事业部".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   syb = "";
                    	   }else{
                    		   syb = getCellValue(cell);
                    	   }
                       }else if("事业部经理".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   sybManager = "";
                    	   }else{
                    		   sybManager = getCellValue(cell);
                    	   }
                       }else if("大区编码".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   dqCode = "";
                    	   }else{
                    		   dqCode = getCellValue(cell);
                    	   }
                       }else if("大区".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   dq = "";
                    	   }else{
                    		   dq = getCellValue(cell);
                    	   }
                       }else if("大区经理".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   dqManager = "";
                    	   }else{
                    		   dqManager = getCellValue(cell);
                    	   }
                       }else if("分公司编码".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   fgsCode = "";
                    	   }else{
                    		   fgsCode = getCellValue(cell);
                    	   }
                       }else if("分公司".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   fgs = "";
                    	   }else{
                    		   fgs = getCellValue(cell);
                    	   }
                       }else if("分公司经理".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   fgsManager = "";
                    	   }else{
                    		   fgsManager = getCellValue(cell);
                    	   }
                       }else if("营业部编码".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   yybCode = "";
                    	   }else{
                    		   yybCode = getCellValue(cell);
                    	   }
                       }else if("营业部".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   yyb = "";
                    	   }else{
                    		   yyb = getCellValue(cell);
                    	   }
                       }else if("营业部经理".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   yybManager = "";
                    	   }else{
                    		   yybManager = getCellValue(cell);
                    	   }
                       }else if("团队经理".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   tdManager = "";
                    	   }else{
                    		   tdManager = getCellValue(cell);
                    	   }
                       }else if("姓名".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   name = "";
                    	   }else{
                    		   name = getCellValue(cell);
                    	   }
                       }else if("员工编号".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   userId = "";
                    	   }else{
                    		   userId = getCellValue(cell);
                    	   }
                       }else if("手机号".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   tel = "";
                    	   }else{
                    		   tel = getCellValue(cell);
                    	   }
                       }else if("岗位名称".equals(titleDatas[cellNum].trim())){
                    	   if("无".equals(getCellValue(cell))){
                    		   post = "";
                    	   }else{
                    		   post = getCellValue(cell);
                    	   }
                       }
                   }
                   OrganizationConditionVO orCon = new OrganizationConditionVO();
                   
                   //syb
                   if(sybCode != null && !"".equals(sybCode)){
                	   orCon.setOid(sybCode);
        			   orCon.setLevelType(Constant.FLAG_ZERO);
        			   //oc表
                	   OrganizationCodeVO syboc = organizationService.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(syboc != null){
                		   if(!syb.equals(syboc.getOname()) || !sybManager.equals(syboc.getMname())|| !Constant.FLAG_ZERO.equals(syboc.getDelFlg())){
                			   orCon.setOname(syb);
                			   orCon.setMname(sybManager);
                			   organizationService.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(syb); 
                		   orCon.setMname(sybManager);
                		   organizationService.insertOrganizationCode(orCon); 
                	   }
                	   orCon.setDid(sybCode);
                	   //o表
                	   orCon.setPid("B001");
            		   orCon.setFid("C001");
            		   orCon.setYid("D001");
                	   List<OrganizationVO> sybolist = organizationService.selectOrByCondition(orCon); 
                	   //不存在新增
                	   if(sybolist == null || sybolist.size() == 0){
                		   organizationService.insertOrganization(orCon);
                	   }
                   }else{
                	   sybCode = "A001";  
                   }
                   //dq
                   if(dqCode != null && !"".equals(dqCode)){
                	   orCon.setOid(dqCode); 
                	   orCon.setLevelType(Constant.FLAG_ONE);
                	 //oc表
                	   OrganizationCodeVO dqoc = organizationService.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(dqoc != null){
                		   if(!dq.equals(dqoc.getOname()) || !dqManager.equals(dqoc.getMname()) || !Constant.FLAG_ZERO.equals(dqoc.getDelFlg())){
                			   orCon.setOname(dq);
                			   orCon.setMname(dqManager);
                			   organizationService.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(dq);
                		   orCon.setMname(dqManager);
                		   organizationService.insertOrganizationCode(orCon); 
                	   }
                	   orCon.setDid(sybCode);
                	   orCon.setPid(dqCode);
                	   //o表
                	   orCon.setFid("C001");
            		   orCon.setYid("D001");
                	   List<OrganizationVO> dqolist = organizationService.selectOrByCondition(orCon);
                	   //不存在新增
                	   if(dqolist == null || dqolist.size() == 0){
                		   organizationService.insertOrganization(orCon);
                	   }
                   }else{
                	   dqCode = "B001";
                   }
                 //fgs
                   if(fgsCode != null && !"".equals(fgsCode)){
                	   orCon.setOid(fgsCode); 
                	   orCon.setLevelType(Constant.FLAG_TWO);
                	 //oc表
                	   OrganizationCodeVO fgsoc = organizationService.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(fgsoc != null){
                		   if(!fgs.equals(fgsoc.getOname()) || !fgsManager.equals(fgsoc.getMname())){
                			   orCon.setOname(fgs);
                			   orCon.setMname(fgsManager);
                			   organizationService.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(fgs);
            			   orCon.setMname(fgsManager);
            			   organizationService.insertOrganizationCode(orCon); 
                	   }
                	   orCon.setDid(sybCode);
                	   orCon.setPid(dqCode);
                	   orCon.setFid(fgsCode);
                	   //o表
                	   orCon.setYid("D001");
                	   List<OrganizationVO> fgsolist = organizationService.selectOrByCondition(orCon);
                	   //不存在新增
                	   if(fgsolist == null || fgsolist.size() == 0){
                		   organizationService.insertOrganization(orCon);
                	   }
                   }else{
                	   fgsCode = "C001";
                   }
                 //yyb
                   if(yybCode != null && !"".equals(yybCode)){
                	   orCon.setOid(yybCode); 
                	   orCon.setLevelType(Constant.FLAG_THREE);
                	 //oc表
                	   OrganizationCodeVO yyboc = organizationService.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(yyboc != null){
                		   if(!yyb.equals(yyboc.getOname()) || !yybManager.equals(yyboc.getMname())|| !Constant.FLAG_ZERO.equals(yyboc.getDelFlg())){
                			   orCon.setOname(yyb);
                			   orCon.setMname(yybManager);
                			   organizationService.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(yyb);
            			   orCon.setMname(yybManager);
            			   organizationService.insertOrganizationCode(orCon); 
                	   }
            		   orCon.setDid(sybCode);
            		   orCon.setPid(dqCode);
            		   orCon.setFid(fgsCode);
                	   orCon.setYid(yybCode);
                	   //o表
                	   List<OrganizationVO> yybolist = organizationService.selectOrByCondition(orCon);
                	   //不存在新增
                	   if(yybolist == null || yybolist.size() == 0){
                		   organizationService.insertOrganization(orCon);
                	   }
                   }else{
                	   yybCode = "D001";
                   }
                 //td
                   if(tdManager != null && !"".equals(tdManager)){
//                	   String yyb_td = "";
                	   String tdteam = "";
            		   orCon.setOid(yybCode); 
            		   orCon.setLevelType(Constant.FLAG_FOUR);
            		   //oc表
            		   List<OrganizationCodeVO> tdoclist = organizationService.fuzzySelectOrganizationCode(orCon);
            		   //营业部下存在团队
            		   if(tdoclist != null){
            		   //所有团队经理
            			   List<String> mlist = new ArrayList<String>();
            			   for(int i = 0; i < tdoclist.size(); i++){
            				   OrganizationCodeVO tdoc = tdoclist.get(i);
            				   mlist.add(tdoc.getMname());
            			   }
            			   //不存在就新增团队
            			   if(!mlist.contains(tdManager)){
            				   if(mlist.size() == 1){
            					   tdCode = yybCode + Constant.FLAG_A;
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   organizationService.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 2){
            					   tdCode = yybCode + Constant.FLAG_B;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   organizationService.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 3){
            					   tdCode = yybCode + Constant.FLAG_C;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   organizationService.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 4){
            					   tdCode = yybCode + Constant.FLAG_D;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   organizationService.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 5){
            					   tdCode = yybCode + Constant.FLAG_E;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   organizationService.insertOrganizationCode(orCon);
            				   }
            			   }
            		   }//营业部下不存在团队
            		   else{
            			   tdCode = yybCode + "A";
            			   tdteam = tdManager + "Team";
            			   orCon.setOid(tdCode);
            			   orCon.setOname(tdteam);
            			   orCon.setMname(tdManager);
            			   organizationService.insertOrganizationCode(orCon); 
            		   }
            		   orCon.setDid(sybCode);
            		   orCon.setPid(dqCode);
            		   orCon.setFid(fgsCode);
            		   orCon.setYid(yybCode);
                	   orCon.setTid(tdCode);
                	   //o表
                	   List<OrganizationVO> tdolist = organizationService.selectOrByCondition(orCon);
                	   //不存在新增
                	   if(tdolist == null || tdolist.size() == 0){
                		   organizationService.insertOrganization(orCon);
                	   }                  
                	   }
                   //user表
                   UserConditionVO ucon = new UserConditionVO();
                   if(!"".equals(userId)){
                	   ucon.setUserId(userId);
                   }else{
                	   ucon.setTel(tel); 
                   }
                   UserVO user = userService.selectUserByPrimaryKey(ucon);
                   String typeId = "";
                   if(post.contains("事业部经理")){
                	   typeId = "8";
                   }else if(post.contains("大区经理")){
                	   typeId = "7";
                   }else if(post.contains("分公司经理")){
                	   typeId = "6";
                   }else if(post.contains("营业部经理")){
                	   typeId = "5";
                   }else if(post.contains("团队经理")){
                	   typeId = "4";
                   }else if(post.contains("客户经理")){
                	   typeId = "3";
                   }
                   //存在
                   if(user != null){
                	   if(!name.equals(user.getName()) || !tel.equals(user.getTel())|| !Constant.FLAG_ZERO.equals(user.getDelFlg())){
                		   ucon.setName(name);
                		   ucon.setTel(tel);
                		   userService.updateUserByPrimaryKeySelective(ucon,insUserId);
                	   }
                   }//不存在
                   else{
                	   ucon.setPassword(Constant.FLAG_ONE);
                	   ucon.setName(name);
            		   ucon.setTel(tel);
            		   ucon.setTypeId(typeId);
            		   ucon.setSid(sybCode);
            		   ucon.setDid(dqCode);
            		   ucon.setFid(fgsCode);
            		   ucon.setYid(yybCode);
            		   ucon.setTid(tdCode);
            		   //插入时间
            		   String insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
            		   ucon.setInsDate(insDate);
            		   ucon.setLocalUserId(insUserId);
            		   userService.insertUserSelective(ucon);
                   }
                 }
			 } 
		 }
	}
	
	/**
	 * 读取数据总表Excel并保存进数据库
	 */
	public List<ConditionVO> readDataExcelFile(Workbook wb, InformationService informationService,TypeService typeService,OrganizationService organizationService,String userId){
		List<ConditionVO> conlist = new ArrayList<ConditionVO>();
		if(wb != null){
			for(int sheetNum = 0;sheetNum < wb.getNumberOfSheets();sheetNum++){
				//获得当前sheet
				 Sheet sheet = wb.getSheetAt(sheetNum);//
				 if(sheet == null || !"da".equals(sheet.getSheetName())){
                     continue;
                 }
				//获得当前sheet的结束行
	                int lastRowNum = sheet.getLastRowNum();
	                //标题行数
	                int titleRowNum = getDataRowNumByName(sheet,"合同编号");
	                //标题数据行
	                String[] titleDatas = getDatas(sheet,titleRowNum);
	              //获取要导入的数据行
	                int dataRowNum = getDataRowNumByTel(sheet);//2
	              //循环从数据行开始
	                for(int rowNum = dataRowNum;rowNum <= lastRowNum;rowNum++){
	                	String sybCode = "A001";
	                	String syb = "";
	                	String sybManager = "";
	                	String dqCode = "B001";
	                	String dq = "";
	                	String dqManager = "";
	                	String fgsCode = "C001";
	                	String fgs = "";
	                	String fgsManager = "";
	                	String yybCode = "D001";
	                	String yyb = "";
	                	String yybManager = "";
	                	String tManager = "";
	                	//新增contract
	                	String contract = "";
	                	//更新contract
	                	String updContract = "";
	                	String insDate = "";
	                	ConditionVO con = new ConditionVO();
	                	//获得当前行
	                    Row row = sheet.getRow(rowNum);
	                    if(row == null){
	                        continue;
	                    }
	                  //获得当前行的开始列
	                  int firstCellNum = row.getFirstCellNum();
	                  //获得当前行的列数
	                  int lastCellNum = row.getPhysicalNumberOfCells();
//	                  String[] cells = new String[row.getPhysicalNumberOfCells()];
	                  //循环当前行所有列
	                  for(int cellNum = firstCellNum; cellNum < lastCellNum;cellNum++){
	                	  Cell cell = row.getCell(cellNum);
//	                      cells[cellNum] = getCellValue(cell);
	                      //给list赋值
	                      if("合同编号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setContract("无");
	                    	   }else{
	                    		   ConditionVO incon = new ConditionVO();
	                    		   incon.setContract(getCellValue(cell));
	                    		   ConditionVO info = informationService.selectByPrimaryKey(incon);
	                    		   if(info == null){
	                    			   contract = getCellValue(cell);
	                    			   con.setContract(contract);
	                    		   }else{
	                    			   updContract = getCellValue(cell);
	                    			   con.setContract(updContract);
	                    		   }
	                    	   }
	                       }else if("投资金额".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setMoney(0);
	                    	   }else{
	                    		   con.setMoney(Integer.valueOf(getCellValue(cell)));
	                    	   }
	                       }else if("产品名称".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setType("");
	                    	   }else{
	                    		   TypeVO typecon = new TypeVO();
	                    		   typecon.setTypeName(getCellValue(cell));
	                    		   TypeVO type = typeService.selectTypeByCondition(typecon);
	                    		   con.setType(type.getType());
	                    	   } 
	                       }else if("折标系数".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setZbRatio(new BigDecimal(0.0));
	                    	   }else{
	                    		   con.setZbRatio(new BigDecimal(getCellValue(cell)));
	                    	   }
	                       }else if("绩效业绩".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setJxAchievement(new BigDecimal(0.0));
	                    	   }else{
	                    		   con.setJxAchievement(new BigDecimal(getCellValue(cell)));
	                    	   }
	                       }else if("理财经理".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setLcManager("");
	                    	   }else{
	                    		   con.setLcManager(getCellValue(cell));
	                    	   } 
	                       }else if("员工编号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setLcId("");
	                    	   }else{
	                    		   con.setLcId(getCellValue(cell));
	                    	   }
	                       }else if("团队经理".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   tManager = getCellValue(cell);
	                    		   con.setTmanager(tManager);
	                    	   }
	                       }else if("事业部编码".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   sybCode = getCellValue(cell);
	                    	   }
	                       }else if("事业部名称".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		  syb = getCellValue(cell);
	                    	   }
	                       }else if("事业部经理".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   sybManager = getCellValue(cell);
	                    		   con.setSybManager(sybManager);
	                    	   }
	                       }else if("大区编码".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   dqCode = getCellValue(cell);
	                    	   }
	                       }else if("大区名称".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   dq = getCellValue(cell);
	                    	   }
	                       }else if("大区经理".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   dqManager = getCellValue(cell);
	                    		   con.setDqManager(dqManager);
	                    	   }
	                       }else if("分公司编码".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   fgsCode = getCellValue(cell);
	                    	   }
	                       }else if("分公司名称".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   fgs = getCellValue(cell);
	                    	   }
	                       }else if("分公司经理".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   fgsManager = getCellValue(cell);
	                    		   con.setFgsManager(fgsManager);
	                    	   }
	                       }else if("营业部编码".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   yybCode = getCellValue(cell);
	                    	   }
	                       }else if("营业部名称".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   yyb = getCellValue(cell);
	                    	   }
	                       }else if("营业部经理".equals(titleDatas[cellNum].trim())){
	                    	   if(!"无".equals(getCellValue(cell)) && !"".equals(getCellValue(cell))){
	                    		   yybManager = getCellValue(cell);
	                    		   con.setYybManager(yybManager);
	                    	   }
	                       }else if("期数".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setPeriods(3);
	                    	   }else{
	                    		   con.setPeriods(Integer.valueOf(getCellValue(cell)));
	                    	   }
	                       }else if("年化收益率".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setRate("");
	                    	   }else{
	                    		   con.setRate(getCellValue(cell));
	                    	   }
	                       }else if("利息总额".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInterestAll(new BigDecimal(0.0));
	                    	   }else{
	                    		   con.setInterestAll(new BigDecimal(getCellValue(cell)));
	                    	   }
	                       }else if("月付利息".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInterestMonth(new BigDecimal(0.0));
	                    	   }else{
	                    		   con.setInterestMonth(new BigDecimal(getCellValue(cell)));
	                    	   }
	                       }else if("划扣日期".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setPaymentDate("");
	                    	   }else{
	                    		   if(getCellValue(cell).contains("/")){
	                    			   con.setPaymentDate(getCellValue(cell).replace("/", "-"));
	                    		   }else{
	                    			   con.setPaymentDate(getCellValue(cell));
	                    		   }
	                    	   }
	                       }else if("初始出借日期".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setStartDate("");
	                    	   }else{
	                    		   if(getCellValue(cell).contains("/")){
	                    			   con.setStartDate(getCellValue(cell).replace("/", "-"));
	                    		   }else{
	                    			   con.setStartDate(getCellValue(cell));
	                    		   }
	                    	   }
	                       }else if("到期日".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setEndDate("");
	                    	   }else{
	                    		   if(getCellValue(cell).contains("/")){
	                    			   con.setEndDate(getCellValue(cell).replace("/", "-"));
	                    		   }else{
	                    			   con.setEndDate(getCellValue(cell));
	                    		   }
	                    	   }
	                       }else if("账单日".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setStatementDate("");
	                    	   }else{
	                    		   con.setStatementDate(getCellValue(cell));
	                    	   }
	                       }else if("即将到期天数".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setSurplusDate("");
	                    	   }else{
	                    		   con.setSurplusDate(getCellValue(cell));
	                    	   }
	                       }else if("状态".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setStatus("");
	                    	   }else{
	                    		   con.setStatus(StatusEnum.getKey(getCellValue(cell)));
	                    	   }
	                       }else if("pos机终端号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setPosNo("");
	                    	   }else{
	                    		   con.setPosNo(getCellValue(cell));
	                    	   }
	                       }else if("出借人姓名".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setTenderName("");
	                    	   }else{
	                    		   con.setTenderName(getCellValue(cell));
	                    	   }
	                       }else if("证件类型".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setIdType("");
	                    	   }else{
	                    		   con.setIdType(getCellValue(cell));
	                    	   }
	                       }else if("身份证号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setIdNo("");
	                    	   }else{
	                    		   con.setIdNo(getCellValue(cell));
	                    	   }
	                       }else if("非续投/续投".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setContinueFlg("");
	                    	   }else{
	                    		   con.setContinueFlg(ContinueFlgEnum.getKey(getCellValue(cell)));
	                    	   }
	                       }else if("联系方式".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setTel("");
	                    	   }else{
	                    		   con.setTel(getCellValue(cell));
	                    	   }
	                       }else if("推广渠道".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setSpreadType("");
	                    	   }else{
	                    		   con.setSpreadType(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setBank("");
	                    	   }else{
	                    		   con.setBank(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行支行名称".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setBranch("");
	                    	   }else{
	                    		   con.setBranch(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行账号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setCardNo("");
	                    	   }else{
	                    		   con.setCardNo(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行开户人姓名".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setCardName("");
	                    	   }else{
	                    		   con.setCardName(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行开卡省份".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setCardProvince("");
	                    	   }else{
	                    		   con.setCardProvince(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行开卡城市".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setCardCity("");
	                    	   }else{
	                    		   con.setCardCity(getCellValue(cell));
	                    	   }
	                       }else if("汇入银行行号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setCardLine("");
	                    	   }else{
	                    		   con.setCardLine(getCellValue(cell));
	                    	   }
	                       }else if("回款银行".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInBank("");
	                    	   }else{
	                    		   con.setInBank(getCellValue(cell));
	                    	   }
	                       }else if("回款银行支行名称".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInBranch("");
	                    	   }else{
	                    		   con.setInBranch(getCellValue(cell));
	                    	   }
	                       }else if("回款银行账号".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInCardNo("");
	                    	   }else{
	                    		   con.setInCardNo(getCellValue(cell));
	                    	   }
	                       }else if("回款银行开户人姓名".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInCardName("");
	                    	   }else{
	                    		   con.setInCardName(getCellValue(cell));
	                    	   }
	                       }else if("回款银行开卡省份".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInCardProvince("");
	                    	   }else{
	                    		   con.setInCardProvince(getCellValue(cell));
	                    	   }
	                       }else if("回款银行开卡城市".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setInCardCity("");
	                    	   }else{
	                    		   con.setInCardCity(getCellValue(cell));
	                    	   }
	                       }else if("出借人地址".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setBorrowAddress("");
	                    	   }else{
	                    		   con.setBorrowAddress(getCellValue(cell));
	                    	   }
	                       }else if("紧急联系人".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setContactName("");
	                    	   }else{
	                    		   con.setContactName(getCellValue(cell));
	                    	   }
	                       }else if("紧急联系人电话".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setContactTel("");
	                    	   }else{
	                    		   con.setContactTel(getCellValue(cell));
	                    	   }
	                       }else if("与出借人关系".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setContactRelationship("");
	                    	   }else{
	                    		   con.setContactRelationship(getCellValue(cell));
	                    	   }
	                       }else if("备注".equals(titleDatas[cellNum].trim())){
	                    	   if("无".equals(getCellValue(cell)) || "".equals(getCellValue(cell))){
	                    		   con.setRemark("");
	                    	   }else{
	                    		   con.setRemark(getCellValue(cell));
	                    	   }
	                       }
	                      
	                  }
	                  //和架构表关联
	                  OrganizationCodeVO oc = new OrganizationCodeVO();
	                  oc.setVlevel(Constant.FLAG_ZERO);
	                  OrganizationConditionVO orcon = new OrganizationConditionVO();
	                  orcon.setVlevel(Constant.FLAG_ZERO);
	                  //syb
	                  if(!"".equals(sybCode) && !"A001".equals(sybCode)){
	                	  oc.setOid(sybCode);
	                	  oc.setLevelType(Constant.FLAG_ZERO);
	        			  //oc表
	        			  List<OrganizationCodeVO> syboc = organizationService.selectOrganizationCodeByCondition(oc);
	        			  //不存在
						  if(syboc == null || syboc.size() == 0){
	                		  orcon.setOid(sybCode);
	                		  orcon.setOname(syb);
	                		  orcon.setMname(sybManager);
		                	  orcon.setLevelType(Constant.FLAG_ZERO);
		                	  organizationService.insertOrganizationCode(orcon);
						  }//已存在
						  else{
						   //有变化
	                		 if(!syb.equals(syboc.get(0).getOname()) || !sybManager.equals(syboc.get(0).getMname())){
	                			 orcon.setOid(sybCode);
	                			 orcon.setOname(syb);
	                			 orcon.setMname(sybManager);
	                			 organizationService.updateOrganizationCode(orcon); 
	                		 }
						  }
	                	  //o表
	                	  orcon.setDid(sybCode);
	                	  orcon.setLevelType(Constant.FLAG_ZERO);
	                	  orcon.setPid("B001");
               		   	  orcon.setFid("C001");
               		      orcon.setYid("D001");
	                	  List<OrganizationVO> sybolist = organizationService.selectOrganizationByCondition(orcon); 
	                	//不存在新增
	                	   if(sybolist.size() == 0){
	                		   organizationService.insertOrganization(orcon);
	                	   }
	                  }
	                  con.setSyb(sybCode);
	                //dq
	                  if(!"".equals(dqCode) && !"B001".equals(dqCode)){
	                	  oc.setOid(dqCode);
	                	  oc.setLevelType(Constant.FLAG_ONE);
	        			  //oc表
	        			  List<OrganizationCodeVO> dqoc = organizationService.selectOrganizationCodeByCondition(oc);
	        			  //不存在
						  if(dqoc == null || dqoc.size() == 0){
	                		  orcon.setOid(dqCode);
	                		  orcon.setOname(dq);
	                		  orcon.setMname(dqManager);
		                	  orcon.setLevelType(Constant.FLAG_ONE);
		                	  organizationService.insertOrganizationCode(orcon);
						  }//已存在
						  else{
						   //有变化
	                		 if(!dq.equals(dqoc.get(0).getOname()) || !dqManager.equals(dqoc.get(0).getMname())){
	                			 orcon.setOid(dqCode);
	                			 orcon.setOname(dq);
	                			 orcon.setMname(dqManager);
	                			 orcon.setLevelType(Constant.FLAG_ONE);
	                			 organizationService.updateOrganizationCode(orcon); 
	                		 }
						  }
	                	  //o表
	                	  orcon.setDid(sybCode);
	                	  orcon.setPid(dqCode);
	                	  orcon.setLevelType(Constant.FLAG_ONE);
	                	  orcon.setFid("C001");
               		   	  orcon.setYid("D001");
	                	  List<OrganizationVO> dqolist = organizationService.selectOrganizationByCondition(orcon); 
	                	//不存在新增
	                	   if(dqolist.size() == 0){
	                		   organizationService.insertOrganization(orcon);
	                	   }
	                  }
	                  con.setDq(dqCode);
	                //fgs
	                  if(!"".equals(fgsCode) && !"C001".equals(fgsCode)){
	                	  oc.setOid(fgsCode);
	                	  oc.setLevelType(Constant.FLAG_TWO);
	        			  //oc表
	        			  List<OrganizationCodeVO> fgsoc = organizationService.selectOrganizationCodeByCondition(oc);
	        			  //不存在
						  if(fgsoc == null || fgsoc.size() == 0){
	                		  orcon.setOid(fgsCode);
	                		  orcon.setOname(fgs);
	                		  orcon.setMname(fgsManager);
		                	  orcon.setLevelType(Constant.FLAG_TWO);
		                	  organizationService.insertOrganizationCode(orcon);
						  }//已存在
						  else{
						   //有变化
	                		 if(!fgs.equals(fgsoc.get(0).getOname()) || !fgsManager.equals(fgsoc.get(0).getMname())){
	                			 orcon.setOid(fgsCode);
	                			 orcon.setOname(fgs);
	                			 orcon.setMname(fgsManager);
	                			 orcon.setLevelType(Constant.FLAG_TWO);
	                			 organizationService.updateOrganizationCode(orcon); 
	                		 }
						  }
	                	  //o表
	                	  orcon.setDid(sybCode);
	                	  orcon.setPid(dqCode);
	                	  orcon.setFid(fgsCode);
	                	  orcon.setLevelType(Constant.FLAG_TWO);
	                	  orcon.setYid("D001");
	                	  List<OrganizationVO> fgsolist = organizationService.selectOrganizationByCondition(orcon); 
	                	//不存在新增
	                	   if(fgsolist.size() == 0){
	                		   organizationService.insertOrganization(orcon);
	                	   }
	                  }
	                  con.setFgs(fgsCode);
	                //yyb
	                  if(!"".equals(yybCode) && !"D001".equals(yybCode)){
	                	  oc.setOid(yybCode);
	                	  oc.setLevelType(Constant.FLAG_THREE);
	        			  //oc表
	        			  List<OrganizationCodeVO> yyboc = organizationService.selectOrganizationCodeByCondition(oc);
	        			  //不存在
						  if(yyboc == null || yyboc.size() == 0){
	                		  orcon.setOid(yybCode);
	                		  orcon.setOname(yyb);
	                		  orcon.setMname(yybManager);
		                	  orcon.setLevelType(Constant.FLAG_THREE);
		                	  organizationService.insertOrganizationCode(orcon);
						  }//已存在
						  else{
						   //有变化
	                		 if(!yyb.equals(yyboc.get(0).getOname()) || !yybManager.equals(yyboc.get(0).getMname())){
	                			 orcon.setOid(yybCode);
	                			 orcon.setOname(yyb);
	                			 orcon.setMname(yybManager);
	                			 orcon.setLevelType(Constant.FLAG_THREE);
	                			 organizationService.updateOrganizationCode(orcon); 
	                		 }
						  }
	                	  //o表
	                	  orcon.setDid(sybCode);
	                	  orcon.setPid(dqCode);
	                	  orcon.setFid(fgsCode);
	                	  orcon.setYid(yybCode);
	                	  orcon.setLevelType(Constant.FLAG_THREE);
	                	  List<OrganizationVO> yybolist = organizationService.selectOrganizationByCondition(orcon); 
	                	//不存在新增
	                	   if(yybolist.size() == 0){
	                		   organizationService.insertOrganization(orcon);
	                	   }
	                  }
	                  con.setYyb(yybCode);
	                //td
	                  if(tManager != null && !"".equals(tManager)){
	                	  String tCode = "";
	                	  String tTeam = "";
	                	  orcon.setOid(yybCode);
	                	  orcon.setLevelType(Constant.FLAG_FOUR);
	                	  //oc表
	            		  List<OrganizationCodeVO> tdoclist = organizationService.fuzzySelectOrganizationCode(orcon);
	            		//营业部下存在团队
	            		  if(tdoclist.size() != 0){
	            			//所有团队经理
	            			   List<String> mlist = new ArrayList<String>();
	            			   for(int i = 0; i < tdoclist.size(); i++){
	            				   OrganizationCodeVO tdoc = tdoclist.get(i);
	            				   mlist.add(tdoc.getMname());
	            			   }
	            			 //不存在就新增团队
	            			   if(!mlist.contains(tManager)){
	            				   if(mlist.size() == 1){
	            					   tCode = yybCode + Constant.FLAG_B;
	            					   tTeam = tManager + "Team";
	            					   orcon.setOid(tCode);
	            					   orcon.setOname(tTeam);
	            					   orcon.setMname(tManager);
	            					   organizationService.insertOrganizationCode(orcon);
	            				   }else if(mlist.size() == 2){
	            					   tCode = yybCode + Constant.FLAG_C;
	            					   tTeam = tManager + "Team";
	            					   orcon.setOid(tCode);
	            					   orcon.setOname(tTeam);
	            					   orcon.setMname(tManager);
	            					   organizationService.insertOrganizationCode(orcon);
	            				   }else if(mlist.size() == 3){
	            					   tCode = yybCode + Constant.FLAG_D;
	            					   tTeam = tManager + "Team";
	            					   orcon.setOid(tCode);
	            					   orcon.setOname(tTeam);
	            					   orcon.setMname(tManager);
	            					   organizationService.insertOrganizationCode(orcon);
	            				   }else if(mlist.size() == 4){
	            					   tCode = yybCode + Constant.FLAG_E;
	            					   tTeam = tManager + "Team";
	            					   orcon.setOid(tCode);
	            					   orcon.setOname(tTeam);
	            					   orcon.setMname(tManager);
	            					   organizationService.insertOrganizationCode(orcon);
	            				   }else if(mlist.size() == 5){
	            					   tCode = yybCode + Constant.FLAG_F;
	            					   tTeam = tManager + "Team";
	            					   orcon.setOid(tCode);
	            					   orcon.setOname(tTeam);
	            					   orcon.setMname(tManager);
	            					   organizationService.insertOrganizationCode(orcon);
	            				   }  
	            			   }
	            		  }//营业部下不存在团队
	            		  else{
	            			  tCode = yybCode + "A";
	            			  tTeam = tManager + "Team";
	            			  orcon.setOid(tCode);
	            			  orcon.setOname(tTeam);
	            			  orcon.setMname(tManager);
	            			  organizationService.insertOrganizationCode(orcon);
	            		  }
	            		   orcon.setDid(sybCode);
	            		   orcon.setPid(dqCode);
	            		   orcon.setFid(fgsCode);
	            		   orcon.setYid(yybCode);
	                	   orcon.setTid(tCode);
	                	   //o表
	                	   List<OrganizationVO> tdolist = organizationService.selectOrganizationByCondition(orcon);
	                	 //不存在新增
	                	   if(tdolist.size() == 0){
	                		   organizationService.insertOrganization(orcon);
	                	   }
	                  }
	                  con.setTmanager(tManager);
	                 //不存在contract
	                  if(!"".equals(contract)){
	                	  con.setInsUser(userId);
	                	  insDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
	          			  con.setInsDate(insDate);
	                	  conlist.add(con); 
	                  }//存在contract
	                  else{
	                	  UserVO user = new UserVO();
	                	  user.setUserId(userId);
	                	  informationService.updateByPrimaryKeySelective(con,user);
	                  }
	                }  
			}
		}
		return conlist;
	}
	
	/**
	 * 从0开始根据模板中获取数据行数
	 * 
	 * @return 当前数据行数
	 */
	public static int getDataRowNumByTel(Sheet sheet) {
		int curDataRowNum = 0;
		int dataColNum = 0;
		for (Row row : sheet) {
			for(int i = 0; i < row.getPhysicalNumberOfCells(); i++){
				String str = getCellValue(row.getCell(i)).trim(); 
				if (str.contains("手机号") || str.contains("联系方式")) {
					dataColNum = i;
					break;
				}
				if(dataColNum == i && str.startsWith("1")){
					curDataRowNum = row.getRowNum();
					return curDataRowNum;
				}
			}
		}
		return curDataRowNum;
	}
	
	public static String getCellValue(Cell cell){
        String cellValue = "";
        if(cell == null){
            return cellValue;
        }
        //把数字当成String来读，避免出现1读成1.0的情况
        if(cell.getCellType() == Cell.CELL_TYPE_NUMERIC){
            cell.setCellType(Cell.CELL_TYPE_STRING);
        }
        //判断数据的类型
        switch (cell.getCellType()){
            case Cell.CELL_TYPE_NUMERIC: //数字
                cellValue = String.valueOf(cell.getNumericCellValue());
                break;
            case Cell.CELL_TYPE_STRING: //字符串
                cellValue = String.valueOf(cell.getStringCellValue());
                break;
            case Cell.CELL_TYPE_BOOLEAN: //Boolean
                cellValue = String.valueOf(cell.getBooleanCellValue());
                break;
            case Cell.CELL_TYPE_FORMULA: //公式
                cellValue = String.valueOf(cell.getCellFormula());
                break;
            case Cell.CELL_TYPE_BLANK: //空值
                cellValue = "";
                break;
            case Cell.CELL_TYPE_ERROR: //故障
                cellValue = "非法字符";
                break;
            default:
                cellValue = "未知类型";
                break;
        }
        return cellValue;
    }
	
	/**
	 * 获取模板中标题行数
	 */
	public static int getDataRowNumByName(Sheet sheet,String name){
		//要获取的数据行数
		int dataRowNum = 0;
		//获得当前sheet的结束行
        int lastRowNum = sheet.getLastRowNum();
        for(int i = 0; i < lastRowNum; i++){
        	Row row = sheet.getRow(i);
        	// 总列数
    		int colNum = row.getPhysicalNumberOfCells();
//    		String[] data = new String[colNum];
    		for(int j = 0; j < colNum; j++){
    			if(name.equals(getCellValue(row.getCell((short) j)))){
    				dataRowNum = i;
    				return dataRowNum;
    			}
    		}
        }
		return dataRowNum;
	}
	
	/**
	 * 获取模板中标题data[]
	 */
	public static String[] getDatas(Sheet sheet, int dataRowNum) {
		Row row = sheet.getRow(dataRowNum);
		// 总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] data = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			data[i] = getCellValue(row.getCell((short) i));
		}
		return data;
	}
	
	/**
	 * 获取模板中（大区）的index
	 */
	public static int getDataIndex(String[] datas, String data) {
		int index = 0;
		boolean isExit = false;
		for (int i = 0; i < datas.length; i++) {
			if (datas[i].equals(data)) {
				index = i;
				isExit = true;
			}
		}
		if (!isExit) {
			logger.info("文件中未找到: " + data);
		}
		return index;
	}
	
	public static void uploadFile(byte[] file, String filePath, String fileName) throws Exception { 
        File targetFile = new File(filePath);  
        if(!targetFile.exists()){    
            targetFile.mkdirs();    
        }       
        FileOutputStream out = new FileOutputStream(filePath + "\\" + fileName);
        out.write(file);
        out.flush();
        out.close();
    }
	
	/**
	 * 多文件上传
	 */
	public static List<FileVO> uploadBatchFiles(HttpServletRequest req, MultipartHttpServletRequest multiReq,UserVO user,String contract,FileMapper fileMapper) {
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
		        String newfileName = DateUtil.getDate() + "_" + uploadFileName;
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
		        fileMapper.insertFilePath(fileVO);
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
			fileList.add(fileVO);
		}
		return fileList;
	}
	
	/**
	 * 将服务器文件压缩到压缩包中
	 */
	public static void zipFile(File inputFile, ZipOutputStream zipoutputStream) {
		try {
			if (inputFile.exists()) {
				if (inputFile.isFile()) {
					FileInputStream inStream = new FileInputStream(inputFile);
					BufferedInputStream bInStream = new BufferedInputStream(inStream);
					ZipEntry entry = new ZipEntry(inputFile.getName());
					zipoutputStream.putNextEntry(entry);
					//通过available方法取得流的最大字符数 
					streamTotal = bInStream.available();
					//取得流文件需要分开的数量
					streamNum = (int)Math.floor(streamTotal / MAX_BYTE);
					//分开文件之后剩余的数量
					leaveByte = (int)streamTotal % MAX_BYTE;
					if(streamNum > 0){
						for(int i = 0; i < streamNum; i++){
							inOutByte = new byte[MAX_BYTE];
							//读入流，保存在byte数组
							bInStream.read(inOutByte,0,MAX_BYTE);
							zipoutputStream.write(inOutByte,0,MAX_BYTE);
						}
					}
					//写出剩下的流数据
					inOutByte = new byte[leaveByte];
					bInStream.read(inOutByte,0,leaveByte);
					zipoutputStream.write(inOutByte);
					//关闭zipentry
					zipoutputStream.closeEntry();
					//close
					bInStream.close();
					inStream.close();
				}
			} else{
				throw new ServletException("文件不存在");
			}
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	/**
	 * 下载文件（是否将服务器文件删除）
	 */
	public static boolean downloadFile(File file, HttpServletResponse res, boolean isDelete){
		boolean flag = false;
		try{
			//以流的形式下载文件
			BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file.getPath()));
			byte[] buffer = new byte[fis.available()];
			fis.read(buffer);
			fis.close();
			//清空response
			res.reset();
			OutputStream toClient = new BufferedOutputStream(res.getOutputStream());
			res.setContentType("application/octet-stream");
			res.setHeader("content-type", "application/octet-stream");
			res.setHeader("Content-Disposition", "attachment;filename=" + file.getName());
			toClient.write(buffer);
			toClient.flush();
			toClient.close();
			//是否将生成的服务器端文件删除
			if(isDelete){
				file.delete();
			}
		} catch (IOException ex){
			ex.printStackTrace();
		}
		return flag;
	}
}
