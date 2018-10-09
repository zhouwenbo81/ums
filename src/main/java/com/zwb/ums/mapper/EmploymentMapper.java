package com.zwb.ums.mapper;

import com.entcms.sdk.BaseMapper;
import com.zwb.ums.model.po.Employment;
import com.zwb.ums.model.vo.EmploymentVO;

import java.util.List;


/**
 * <p>
 * Title: EmploymentMapper
 * </p>
 * <p>
 * Description: 职位Mapper接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:47:56
 * @version 1.0
 */
public interface EmploymentMapper extends BaseMapper<Employment>{

    public List<EmploymentVO> selectAllObj(EmploymentVO employmentVO);

    public List<Employment> selectAllEmployment(Employment employment);

    public EmploymentVO selectEmploymentById(String employmentId);

    public List<Employment> selectByDepartmentIdAndRelationType(String departmentId);



}