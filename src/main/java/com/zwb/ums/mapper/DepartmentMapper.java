package com.zwb.ums.mapper;

import com.entcms.sdk.BaseMapper;
import com.zwb.ums.model.po.Department;
import com.zwb.ums.model.vo.DepartmentVO;

import java.util.List;

/**
 * <p>
 * Title: DepartmentMapper
 * </p>
 * <p>
 * Description: 部门Mapper接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:47:16
 * @version 1.0
 */
public interface DepartmentMapper extends BaseMapper<Department>{

//    public List<DepartmentVO> selectAllObj(DepartmentVO departmentVO);

    /**
     * @Title: selectAllDepartment
     * @Description: 查询所有部门
     * @param: department
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 12:48
     * @version 1.0
     */
    public List<Department> selectAllDepartment(Department department);

    /**
     * @Title: selectDepartmentById
     * @Description: 通过Id查询部门详情信息
     * @param: departmentId 部门Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 12:48
     * @version 1.0
     */
    public Department selectDepartmentById(String departmentId);

    /**
     * @Title: selectByCompanyIdAndRelationType
     * @Description: 根据公司Id和关联类型查询该公司的所有部门
     * @param: companyId 公司Id
     * @return:
     * @author zhouwenbo
     * @date 2018/5/7 12:49
     * @version 1.0
     */
    public List<Department> selectByCompanyIdAndRelationType(String companyId);

    /**
     * @Title: selectByParams
     * @Description: 根据部门信息查询部门
     * @param: departmentVO
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/8 10:59
     * @version 1.0
     */
    public List<Department> selectByParams(DepartmentVO departmentVO);

    /**
     * @Title: selectDepartmentByEmploymentId
     * @Description: 根据职位Id查询该职位的所属部门
     * @param: employmentId 职位Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/8 10:59
     * @version 1.0
     */
    public Department selectDepartmentByEmploymentId(String employmentId);


}