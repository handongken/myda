package com.bqhx.yyb.dao;

import java.math.BigDecimal;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import com.bqhx.yyb.vo.InformationVO;
@Mapper
public interface InformationVOMapper {
	int deleteByPrimaryKey(InformationVO record);

	int insert(InformationVO record);

	int insertSelective(InformationVO record);

    List<InformationVO> selectByPrimaryKey(InformationVO record);
    
    int updateByPrimaryKeySelective(InformationVO record);

    int updateByPrimaryKey(InformationVO record);
    
    /**
	 * @author Administrator 
	 * 获取绩效
	 */
    InformationVO getPerformance(String contract);
    /**
	 * @author Administrator
	 * 获取利息
	 */
    InformationVO getInterest(String contract);
    /**
	 * @author Administrator
	 * 获取银行信息
	 */
    InformationVO getBank(String contract);
    
    void updateInterest(@Param("contract")String contract,@Param("interest_all")BigDecimal interest_all,@Param("interest_month")BigDecimal interest_month);

}