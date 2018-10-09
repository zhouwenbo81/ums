package com.zwb.ums.mapper;

import com.entcms.sdk.BaseMapper;
import com.zwb.ums.model.po.Umsuser;
import com.zwb.ums.model.vo.UmsuserVO;

import java.util.List;

/**
 *
 * <p>
 * Title: UmsuserMapper
 * </p>
 * <p>
 * Description: 用户Mapper接口（新）
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author Stone
 * @date 2017年12月14日 下午2:00:28
 * @version 1.0
 */
public interface UmsuserMapper extends BaseMapper<Umsuser>{
    int deleteByPrimaryKey(String id);

    int insert(Umsuser record);

    int insertSelective(Umsuser record);

    Umsuser selectByPrimaryKey(String id);

    int updateByPrimaryKeySelective(Umsuser record);

    int updateByPrimaryKey(Umsuser record);

    /**
     * @Title: selectAllInsideUserCount
     * @Description: 查询所有内部用户的数量
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 15:11
     * @version 1.0
     */
    public int selectAllInsideUserCount(UmsuserVO umsuserVO);

    /**
     * @Title: selectAllInsideUserList
     * @Description: 查询所有内部用户列表（分页）
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 15:11
     * @version 1.0
     */
    public List<UmsuserVO> selectAllInsideUserList(UmsuserVO umsuserVO);

    /**
     * @Title: selectAllInsideUser
     * @Description: 查询所有内部用户
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 15:11
     * @version 1.0
     */
    public List<Umsuser> selectAllInsideUser();

    /**
     * @Title: selectInsideUserByUserId
     * @Description: 根据用户Id查询用户信息
     * @param: userId 用户Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 15:11
     * @version 1.0
     */
    public UmsuserVO selectInsideUserByUserId(String id);


}