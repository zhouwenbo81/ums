package com.zwb.ums.service;

import com.entcms.sdk.BaseServiceImpl;
import com.entcms.sdk.HandleUtil;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.mapper.RelationMapper;
import com.zwb.ums.model.po.Relation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * 
 * <p>
 * Title: RelationServiceImpl
 * </p>
 * <p>
 * Description: 公司、部门、职位、用户关系接口实现
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author Stone
 * @date 2017年6月7日 下午4:23:47
 * @version 1.0
 */

@Service(value="relationService")
public class RelationServiceImpl extends BaseServiceImpl<Relation> implements RelationService {

	@Autowired
	private RelationMapper relationMapper;

	@Override
	public ReturnMessage<Relation> addRelation(Relation relation) throws ENTCMSException {
		relation.setRelationId(HandleUtil.UUID32());
		int i = relationMapper.insertSelective(relation);
		if(i == 1){
			return new ReturnMessage<Relation>("100", "添加成功！");
		}
		return new ReturnMessage<>("999", "添加失败！");
	}

	@Override
	public ReturnMessage<Relation> modifyRelation(Relation relation) throws ENTCMSException {
		int i= relationMapper.updateByPrimaryKeySelective(relation);
		if(i == 1){
			return new ReturnMessage<Relation>("100", "修改成功！");
		}
		return new ReturnMessage<Relation>("100", "修改失败！");
	}

	@Override
	public ReturnMessage<Relation> queryRelationByParams(Relation relation) throws ENTCMSException {
		List<Relation> list = relationMapper.selectByParams(relation);
		return new ReturnMessage<Relation>("100", "查询成功！", list);
	}

	@Override
	public ReturnMessage<Relation> queryRelationByUserId(String userId) throws ENTCMSException {
		List<Relation> list = relationMapper.selectByUserId(userId);
		return new ReturnMessage<Relation>("100", "查询成功！", list);
	}

}
