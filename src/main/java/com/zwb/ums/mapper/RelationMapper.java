package com.zwb.ums.mapper;

import com.entcms.sdk.BaseMapper;
import com.zwb.ums.model.po.Relation;

import java.util.List;

/**
 * 
 * <p>
 * Title: RelationMapper
 * </p>
 * <p>
 * Description: 公司、部门、职位、用户关系Mapper接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author Stone
 * @date 2017年6月7日 下午4:26:21
 * @version 1.0
 */
public interface RelationMapper extends BaseMapper<Relation> {
   
	/**
	 * 
	 * @Title: selectByParams
	 * @Description: 根据自定义参数对象查询
	 * @param relation
	 * @return   
	 * @author Stone
	 * @date 2017年6月16日 下午3:26:17
	 * @version 1.0
	 */
	public List<Relation> selectByParams(Relation relation);
	
	/**
	 * 
	 * @Title: selectByUserId
	 * @Description: 查询该用户的关联信息
	 * @param userId 用户id
	 * @return  
	 * @author zhouwenbo
	 * @date 2017年6月20日 下午5:23:14
	 * @version 1.0
	 */
	public List<Relation> selectByUserId(String userId);
}