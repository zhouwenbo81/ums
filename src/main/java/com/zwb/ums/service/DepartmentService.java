package com.zwb.ums.service;

import com.entcms.sdk.BaseService;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Department;
import com.zwb.ums.model.po.Relation;
import com.zwb.ums.model.vo.DepartmentVO;

/**
 * <p>
 * Title: DepartmentService
 * </p>
 * <p>
 * Description: 部门Service接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月4日 下午1:41:20
 * @version 1.0
 */
public interface DepartmentService extends BaseService<Department> {
	
	/**
	 * @Title: fdeleteDepartmentByIds
	 * @Description: TODO
	 * @param ids
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午1:46:17
	 * @version 1.0
	 */
	public ReturnMessage<Department> fdeleteDepartmentByIds(String[] ids)throws ENTCMSException;
	
	/**
	 * @Title: fdeleteDepartmentById
	 * @Description: TODO
	 * @param departmentId
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午1:46:22
	 * @version 1.0
	 */
	public ReturnMessage<Department> fdeleteDepartmentById(String departmentId) throws ENTCMSException;
	
	/**
	 * @Title: queryDepartmentList
	 * @Description: TODO
	 * @param departmentVO
	 * @return
	 * @throws ENTCMSException   
	 * @author zhouwenbo
	 * @date 2018年5月4日 下午1:46:27
	 * @version 1.0
	 */
	public ReturnMessage<Department> queryDepartmentList(DepartmentVO departmentVO)throws ENTCMSException;

	/**
	 * @Title: queryDepartmentByParams
	 * @Description: 根据自定义参数对象查询部门信息
	 * @param: departmentVO
	 * @return: 
	 * @author zhouwenbo
	 * @date 2018/5/8 13:15
	 * @version 1.0
	 */
	ReturnMessage<Department> queryDepartmentByParams(DepartmentVO departmentVO) throws ENTCMSException;

	/**
	 * @Title: addDepartment
	 * @Description: 添加部门
	 * @param: department 部门信息
	 * @param: companyId 部门所属公司Id
	 * @return:
	 * @author zhouwenbo
	 * @date 2018/5/8 11:16
	 * @version 1.0
	 */
	ReturnMessage<Department> addDepartment(Department department, String companyId) throws ENTCMSException;

	/**
	 * @Title: queryDepartmentById
	 * @Description: 根据Id查询部门信息
	 * @param: departmentId 部门信息
	 * @return: 
	 * @author zhouwenbo
	 * @date 2018/5/8 14:15
	 * @version 1.0
	 */
	public ReturnMessage<Department> queryDepartmentById(String departmentId) throws ENTCMSException;

	/**
	 * @Title: modifyDepartment
	 * @Description: 修改部门信息及关联信息
	 * @param: department
	 * @return:
	 * @author zhouwenbo
	 * @date 2018/5/8 11:16
	 * @version 1.0
	 */
	ReturnMessage<Department> modifyDepartment(DepartmentVO departmentVO) throws ENTCMSException;

	/**
	 * @Title: queryDepartmentByCompanyIdAndRelationType
	 * @Description: 根据公司Id和关联类型查询该公司的所有部门
	 * @param: companyId 公司Id
	 * @return: 
	 * @author zhouwenbo
	 * @date 2018/5/8 17:13
	 * @version 1.0
	 */
	public ReturnMessage<Department> queryDepartmentByCompanyIdAndRelationType(String companyId) throws ENTCMSException;
}
