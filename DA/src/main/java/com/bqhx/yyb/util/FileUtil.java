package com.bqhx.yyb.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.ResourceUtils;

import com.bqhx.yyb.constant.Constant;
import com.bqhx.yyb.dao.OrganizationMapper;
import com.bqhx.yyb.dao.UserMapper;
import com.bqhx.yyb.vo.OrganizationCodeVO;
import com.bqhx.yyb.vo.OrganizationConditionVO;
import com.bqhx.yyb.vo.OrganizationVO;
import com.bqhx.yyb.vo.UserConditionVO;
import com.bqhx.yyb.vo.UserVO;

public class FileUtil {
	
	
	/**
	 * 根据路径及文件名获取Excel文件
	 * @param uploadFilePath
	 * @param uploadFileName
	 */
	public static FileInputStream getFile(String filePath,String fileName) throws FileNotFoundException {
        return new FileInputStream(ResourceUtils.getFile(filePath+fileName));
    }
	
	public  List<String[]> readExcelFile(Workbook wb,OrganizationMapper organizationMapper,UserMapper userMapper){
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
        
		 List<String[]> list = new ArrayList<String[]>();
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
                 int dataRowNum = getDataRowNum(sheet);//4
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
                       
                       if("事业部编码".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   sybCode = "";
                    	   }else{
                    		   sybCode = getCellValue(cell);
                    	   }
                       }else if("事业部".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   syb = "";
                    	   }else{
                    		   syb = getCellValue(cell);
                    	   }
                       }else if("事业部经理".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   sybManager = "";
                    	   }else{
                    		   sybManager = getCellValue(cell);
                    	   }
                       }else if("大区编码".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   dqCode = "";
                    	   }else{
                    		   dqCode = getCellValue(cell);
                    	   }
                       }else if("大区".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   dq = "";
                    	   }else{
                    		   dq = getCellValue(cell);
                    	   }
                       }else if("大区经理".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   dqManager = "";
                    	   }else{
                    		   dqManager = getCellValue(cell);
                    	   }
                       }else if("分公司编码".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   fgsCode = "";
                    	   }else{
                    		   fgsCode = getCellValue(cell);
                    	   }
                       }else if("分公司".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   fgs = "";
                    	   }else{
                    		   fgs = getCellValue(cell);
                    	   }
                       }else if("分公司经理".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   fgsManager = "";
                    	   }else{
                    		   fgsManager = getCellValue(cell);
                    	   }
                       }else if("营业部编码".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   yybCode = "";
                    	   }else{
                    		   yybCode = getCellValue(cell);
                    	   }
                       }else if("营业部".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   yyb = "";
                    	   }else{
                    		   yyb = getCellValue(cell);
                    	   }
                       }else if("营业部经理".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   yybManager = "";
                    	   }else{
                    		   yybManager = getCellValue(cell);
                    	   }
                       }else if("团队经理".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   tdManager = "";
                    	   }else{
                    		   tdManager = getCellValue(cell);
                    	   }
                       }else if("姓名".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   name = "";
                    	   }else{
                    		   name = getCellValue(cell);
                    	   }
                       }else if("员工编号".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   userId = "";
                    	   }else{
                    		   userId = getCellValue(cell);
                    	   }
                       }else if("手机号".equals(titleDatas[cellNum])){
                    	   if("无".equals(getCellValue(cell))){
                    		   tel = "";
                    	   }else{
                    		   tel = getCellValue(cell);
                    	   }
                       }else if("岗位名称".equals(titleDatas[cellNum])){
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
                	   OrganizationCodeVO syboc = organizationMapper.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(syboc != null){
                		   if(!syb.equals(syboc.getOname()) || !sybManager.equals(syboc.getMname())|| !Constant.FLAG_ZERO.equals(syboc.getDelFlg())){
                			   orCon.setOname(syb);
                			   orCon.setMname(sybManager);
                			   orCon.setDelFlg(Constant.FLAG_ZERO);
                			   organizationMapper.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(syb); 
                		   orCon.setMname(sybManager);
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganizationCode(orCon); 
                	   }
                	   orCon.setDid(sybCode);
                	   orCon.setDelFlg("");
                	   //o表
                	   List<OrganizationVO> sybolist = organizationMapper.selectOrganizationByCondition(orCon); 
                	   //不存在新增
                	   if(sybolist == null || sybolist.size() == 0){
                		   orCon.setPid("B001");
                		   orCon.setFid("C001");
                		   orCon.setYid("D001");
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganization(orCon);
                	   }
                   }else{
                	   sybCode = "A001";  
                   }
                   //dq
                   if(dqCode != null && !"".equals(dqCode)){
                	   orCon.setOid(dqCode); 
                	   orCon.setLevelType(Constant.FLAG_ONE);
                	   orCon.setDelFlg("");
                	 //oc表
                	   OrganizationCodeVO dqoc = organizationMapper.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(dqoc != null){
                		   if(!dq.equals(dqoc.getOname()) || !dqManager.equals(dqoc.getMname()) || !Constant.FLAG_ZERO.equals(dqoc.getDelFlg())){
                			   orCon.setOname(dq);
                			   orCon.setMname(dqManager);
                			   orCon.setDelFlg(Constant.FLAG_ZERO);
                			   organizationMapper.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(dq);
                		   orCon.setMname(dqManager);
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganizationCode(orCon); 
                	   }
                	   orCon.setDid(sybCode);
                	   orCon.setPid(dqCode);
                	   orCon.setDelFlg("");
                	   //o表
                	   List<OrganizationVO> dqolist = organizationMapper.selectOrganizationByCondition(orCon);
                	   //不存在新增
                	   if(dqolist == null || dqolist.size() == 0){
                		   orCon.setFid("C001");
                		   orCon.setYid("D001");
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganization(orCon);
                	   }
                   }else{
                	   dqCode = "B001";
                   }
                 //fgs
                   if(fgsCode != null && !"".equals(fgsCode)){
                	   orCon.setOid(fgsCode); 
                	   orCon.setLevelType(Constant.FLAG_TWO);
                	   orCon.setDelFlg("");
                	 //oc表
                	   OrganizationCodeVO fgsoc = organizationMapper.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(fgsoc != null){
                		   if(!fgs.equals(fgsoc.getOname()) || !fgsManager.equals(fgsoc.getMname())){
                			   orCon.setOname(fgs);
                			   orCon.setMname(fgsManager);
                			   orCon.setDelFlg(Constant.FLAG_ZERO);
                			   organizationMapper.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(fgs);
            			   orCon.setMname(fgsManager);
            			   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganizationCode(orCon); 
                	   }
                	   orCon.setDid(sybCode);
                	   orCon.setPid(dqCode);
                	   orCon.setFid(fgsCode);
                	   orCon.setDelFlg("");
                	   //o表
                	   List<OrganizationVO> fgsolist = organizationMapper.selectOrganizationByCondition(orCon);
                	   //不存在新增
                	   if(fgsolist == null || fgsolist.size() == 0){
                		   orCon.setYid("D001");
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganization(orCon);
                	   }
                   }else{
                	   fgsCode = "C001";
                   }
                 //yyb
                   if(yybCode != null && !"".equals(yybCode)){
                	   orCon.setOid(yybCode); 
                	   orCon.setLevelType(Constant.FLAG_THREE);
                	   orCon.setDelFlg("");
                	 //oc表
                	   OrganizationCodeVO yyboc = organizationMapper.selectOrganizationCodeByOid(orCon);
                	   //若已存在
                	   if(yyboc != null){
                		   if(!yyb.equals(yyboc.getOname()) || !yybManager.equals(yyboc.getMname())|| !Constant.FLAG_ZERO.equals(yyboc.getDelFlg())){
                			   orCon.setOname(yyb);
                			   orCon.setMname(yybManager);
                			   orCon.setDelFlg(Constant.FLAG_ZERO);
                			   organizationMapper.updateOrganizationCode(orCon);
                		   }
                	   }//不存在新增
                	   else{
                		   orCon.setOname(yyb);
            			   orCon.setMname(yybManager);
            			   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganizationCode(orCon); 
                	   }
            		   orCon.setDid(sybCode);
            		   orCon.setPid(dqCode);
            		   orCon.setFid(fgsCode);
                	   orCon.setYid(yybCode);
                	   orCon.setDelFlg("");
                	   //o表
                	   List<OrganizationVO> yybolist = organizationMapper.selectOrganizationByCondition(orCon);
                	   //不存在新增
                	   if(yybolist == null || yybolist.size() == 0){
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganization(orCon);
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
            		   orCon.setDelFlg("");
            		   //oc表
            		   List<OrganizationCodeVO> tdoclist = organizationMapper.fuzzySelectOrganizationCode(orCon);
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
            					   orCon.setDelFlg(Constant.FLAG_ZERO);
            					   organizationMapper.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 2){
            					   tdCode = yybCode + Constant.FLAG_B;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   orCon.setDelFlg(Constant.FLAG_ZERO);
            					   organizationMapper.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 3){
            					   tdCode = yybCode + Constant.FLAG_C;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   orCon.setDelFlg(Constant.FLAG_ZERO);
            					   organizationMapper.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 4){
            					   tdCode = yybCode + Constant.FLAG_D;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   orCon.setDelFlg(Constant.FLAG_ZERO);
            					   organizationMapper.insertOrganizationCode(orCon);
            				   }else if(mlist.size() == 5){
            					   tdCode = yybCode + Constant.FLAG_E;
            					   orCon.setOid(tdCode);
            					   tdteam = tdManager + "Team";
            					   orCon.setOid(tdCode); 
            					   orCon.setOname(tdteam);
            					   orCon.setMname(tdManager);
            					   orCon.setDelFlg(Constant.FLAG_ZERO);
            					   organizationMapper.insertOrganizationCode(orCon);
            				   }
            			   }
            		   }//营业部下不存在团队
            		   else{
            			   tdCode = yybCode + "A";
            			   tdteam = tdManager + "Team";
            			   orCon.setOid(tdCode);
            			   orCon.setOname(tdteam);
            			   orCon.setMname(tdManager);
            			   orCon.setDelFlg(Constant.FLAG_ZERO);
            			   organizationMapper.insertOrganizationCode(orCon); 
            		   }
            		   orCon.setDid(sybCode);
            		   orCon.setPid(dqCode);
            		   orCon.setFid(fgsCode);
            		   orCon.setYid(yybCode);
                	   orCon.setTid(tdCode);
                	   orCon.setDelFlg("");
                	   //o表
                	   List<OrganizationVO> tdolist = organizationMapper.selectOrganizationByCondition(orCon);
                	   //不存在新增
                	   if(tdolist == null || tdolist.size() == 0){
                		   orCon.setDelFlg(Constant.FLAG_ZERO);
                		   organizationMapper.insertOrganization(orCon);
                	   }                   }
                   //user表
                   UserConditionVO ucon = new UserConditionVO();
                   if(!"".equals(userId)){
                	   ucon.setUserId(userId);
                   }else{
                	   ucon.setTel(tel); 
                   }
                   ucon.setDelFlg("");
                   UserVO user = userMapper.selectUserByPrimaryKey(ucon);
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
                		   //更新时间
                		   String updDate = DateUtil.formatDate(new Date(), Constant.PATTERN_HMS);
                		   ucon.setUpdDate(updDate);
                		   ucon.setDelFlg(Constant.FLAG_ZERO);
                		   userMapper.updateUserByPrimaryKeySelective(ucon);
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
            		   ucon.setDelFlg(Constant.FLAG_ZERO);
            		   userMapper.insertUserSelective(ucon);
                   }
                   list.add(cells);  
                 }
			 } 
		 }
		 return list;
	}
	
	/**
	 * 从0开始根据模板中获取数据行数
	 * 
	 * @return 当前数据行数
	 */
	public static int getDataRowNum(Sheet sheet) {
		int curDataRowNum = 0;
		int dataColNum = 0;
		for (Row row : sheet) {
			for(int i = 0; i < row.getPhysicalNumberOfCells(); i++){
				String str = getCellValue(row.getCell(i)).trim(); 
				if (str.contains("手机号")) {
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
    		String[] data = new String[colNum];
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
	@SuppressWarnings("deprecation")
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
			System.out.println("文件中未找到: " + data);
		}
		return index;
	}
}
