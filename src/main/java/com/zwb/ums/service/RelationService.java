package com.zwb.ums.service;

import com.entcms.sdk.BaseService;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Relation;

/**
 * 
 * <p>
 * Title: RelationService
 * </p>
 * <p>
 * Description: 公司、部门、职位、用户关系接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author Stone
 * @date 2017年6月7日 下午4:22:15
 * @version 1.0
 */
public interface RelationService extends BaseService<Relation> {
	
	/**
	 * 
	 * @Title: addRelation
	 * @Description: 添加关联关系记录
	 * @param 
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年6月9日 上午11:39:15
	 * @version 1.0
	 */
	public ReturnMessage<Relation> addRelation(Relation relation) throws ENTCMSException;
	
	/**
	 * 
	 * @Title: modifyRelation
	 * @Description: 修改关联记录
	 * @param 
	 * @return
	 * @throws ENTCMSException
	 * @author zhouwenbo
	 * @date 2017年6月9日 下午1:01:00
	 * @version 1.0
	 */
	public ReturnMessage<Relation> modifyRelation(Relation relation) throws ENTCMSException;
	
	/**
	 * 
	 * @Title: queryRelationByParams
	 * @Description: 根据自定义参数查询关联信息
	 * @param relation
	 * @return   
	 * @author Stone
	 * @date 2017年6月16日 下午3:27:59
	 * @version 1.0
	 */
	public ReturnMessage<Relation> queryRelationByParams(Relation relation) throws ENTCMSException;
	
	/**
	 * 
	 * @Title: queryRelationByUserId
	 * @Description: 根据用户Id查询关联信息
	 * @param userId 用户Id
	 * @return
	 * @throws ENTCMSException
	 * @author Stone
	 * @date 2018年2月27日 下午2:21:16
	 * @version 1.0
	 */
	public ReturnMessage<Relation> queryRelationByUserId(String userId) throws ENTCMSException;
	
}
