package com.zwb.ums.service;

import com.entcms.sdk.BaseService;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.model.po.Umsuser;
import com.zwb.ums.model.vo.UmsuserVO;

/**
 * <p>
 * Title: UmsuserService
 * </p>
 * <p>
 * Description: 用户Service接口
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/9 15:25
 */
public interface UmsuserService extends BaseService<Umsuser>{

    /**
     * @Title: queryAllInsideUserList
     * @Description: 分页查询所有内部用户
     * @param:
     * @return:
     * @author zhouwenbo
     * @date 2018/5/9 15:29
     * @version 1.0
     */
    public ReturnMessage<UmsuserVO> queryAllInsideUserList(UmsuserVO umsuserVO) throws ENTCMSException;

    /**
     * @Title: queryAllInsideUser
     * @Description: 查询所有内部用户
     * @param: 
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 15:30
     * @version 1.0
     */
    public ReturnMessage<Umsuser> queryAllInsideUser() throws ENTCMSException;

    /**
     * @Title: queryInsideUserByUserId
     * @Description: 根据用户Id查询内部用户信息
     * @param: userId 用户Id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/9 15:31
     * @version 1.0
     */
    public ReturnMessage<UmsuserVO> queryInsideUserByUserId(String userId) throws ENTCMSException;

    /**
     * @Title: fdeleteUmsUserById
     * @Description: 根据用户Id软删除用户信息
     * @param: id
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:54
     * @version 1.0
     */
    public ReturnMessage<Umsuser> fdeleteUmsUserById(String id) throws ENTCMSException;

    /**
     * @Title: fdeleteUmsUserByIds
     * @Description: 批量软删除用户信息
     * @param: ids
     * @return: 
     * @author zhouwenbo
     * @date 2018/5/11 9:55
     * @version 1.0
     */
    public ReturnMessage<Umsuser> fdeleteUmsUserByIds(String[] ids) throws ENTCMSException;






}
