package com.zwb.ums.mapper;

import com.entcms.sdk.BaseMapper;
import com.zwb.ums.model.po.Company;

import java.util.List;

/**
 * <p>
 * Title: CompanyMapper
 * </p>
 * <p>
 * Description: 公司Mapper接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 * 
 * @author zhouwenbo
 * @date 2018年5月3日 下午5:46:15
 * @version 1.0
 */
public interface CompanyMapper extends BaseMapper<Company>{

    /**
     * @Title: selectAllCompany
     * @Description: 查询全部公司信息
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 15:53
     * @version 1.0
     */
    public List<Company> selectAllCompany(Company company);

    /**
     * @Title: 
     * @Description: 根据用户Id查询该用户的所属公司
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 15:53
     * @version 1.0
     */
    public List<Company> selectByUserId(String userId);

    /**
     * @Title: 
     * @Description: 通过部门id查询部门的所属公司
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 15:53
     * @version 1.0
     */
    public Company selectCompanyByDepartmentId(String departmentId);
    
    /**
     * @Title:
     * @Description: 根据职位Id查询该职位的所属公司
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/7 15:53
     * @version 1.0
     */
    public Company selectCompanyByEmploymentId(String employmentId);

}