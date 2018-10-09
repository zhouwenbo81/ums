package com.zwb.ums.service;

import com.entcms.sdk.BaseService;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Employment;
import com.zwb.ums.model.vo.EmploymentVO;

/**
 * <p>
 * Title: EmploymentService
 * </p>
 * <p>
 * Description: 职位Service
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/7 13:01
 */
public interface EmploymentService extends BaseService<Employment> {
    
    /**
     * @Title: fdeleteEmploymentById
     * @Description: 根据Id删除职位信息（软删除）
     * @param: employmentId 职位Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:31
     * @version 1.0
     */
    public ReturnMessage<Employment> fdeleteEmploymentById(String employmentId) throws ENTCMSException;

    /**
     * @Title: fdeleteEmploymentByIds
     * @Description: 批量软删除职位信息
     * @param: employmentIds
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:32
     * @version 1.0
     */
    public ReturnMessage<Employment> fdeleteEmploymentByIds(String[] employmentIds) throws ENTCMSException;

    /**
     * @Title: queryEmploymentList
     * @Description: 查询职位列表数据
     * @param: employmentVO
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:34
     * @version 1.0
     */
    public ReturnMessage<EmploymentVO> queryEmploymentList(EmploymentVO employmentVO) throws ENTCMSException;

    /**
     * @Title: queryAllEmployment
     * @Description: 查询全部职位信息
     * @param: employment
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:35
     * @version 1.0
     */
    public ReturnMessage<Employment> queryAllEmployment(Employment employment) throws ENTCMSException;

    /**
     * @Title: queryEmploymentById
     * @Description: 根据职位Id查询职位信息
     * @param: employmentId 职位Id
     * @return:
     * @author zhouwenbo
     * @date 2018/5/7 14:35
     * @version 1.0
     */
    public ReturnMessage<EmploymentVO> queryEmploymentById(String employmentId) throws ENTCMSException;

    /**
     * @Title: queryEmploymentByDepartmentIdAndRelationType
     * @Description: 通过部门Id和关联类型查询职位信息
     * @param: departmentId 部门Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 14:36
     * @version 1.0
     */
    public ReturnMessage<Employment> queryEmploymentByDepartmentIdAndRelationType(String departmentId) throws ENTCMSException;

    /**
     * @Title: addEmployment
     * @Description: 添加职位信息
     * @param: employment 职位信息
     * @param: companyId 所属公司Id
     * @param: departmentId 所属部门Id
     * @return:
     * @author zhouwenbo
     * @date 2018/5/9 9:23
     * @version 1.0
     */
    public ReturnMessage<Employment> addEmployment(Employment employment, String companyId, String departmentId) throws ENTCMSException;

    /**
     * @Title: modifyEmployment
     * @Description: 修改职位信息
     * @param: employmentVO 待更新的职位信息
     * @return:
     * @author zhouwenbo
     * @date 2018/5/9 9:23
     * @version 1.0
     */
    public ReturnMessage<Employment> modifyEmployment(EmploymentVO employmentVO) throws ENTCMSException;
}
