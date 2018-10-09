package com.zwb.ums.service;

import com.entcms.sdk.BaseServiceImpl;
import com.entcms.sdk.ReturnMessage;
import com.entcms.sdk.SplitPage;
import com.entcms.sdk.exception.ENTCMSException;
import com.zwb.ums.mapper.UmsuserMapper;
import com.zwb.ums.model.po.Umsuser;
import com.zwb.ums.model.vo.UmsuserVO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * Title: UmsuserServiceImpl
 * </p>
 * <p>
 * Description: 用户Service实现类
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/9 15:24
 */
@Service("umsuserService")
public class UmsuserServiceImpl extends BaseServiceImpl<Umsuser> implements UmsuserService{

    @Autowired
    private UmsuserMapper umsuserMapper;

    @Override
    public ReturnMessage<UmsuserVO> queryAllInsideUserList(UmsuserVO umsuserVO) throws ENTCMSException {
        int rowCount = umsuserMapper.selectAllInsideUserCount(umsuserVO);
        List<UmsuserVO> list = umsuserMapper.selectAllInsideUserList(umsuserVO);
        // 删除最后一页后返回上一页
        SplitPage splitPage = umsuserVO.getSplitPage();
        if (list.size() == 0 && splitPage.getPageNum() > 1) {
            splitPage.setPageNum(splitPage.getPageNum() - 1);
            list = umsuserMapper.selectAllInsideUserList(umsuserVO);
        }
        splitPage.setRowCount(rowCount);
        umsuserVO.setSplitPage(splitPage);
        ReturnMessage<UmsuserVO> returnMessage = new ReturnMessage<UmsuserVO>("100", "查询用户成功！",list,splitPage);
        return returnMessage;
    }

    @Override
    public ReturnMessage<Umsuser> queryAllInsideUser() throws ENTCMSException {
        List<Umsuser> list = umsuserMapper.selectAllInsideUser();
        if (list != null && list.size() > 0) {
            return new ReturnMessage<Umsuser>("100", "查询用户成功！", list);
        }
        return new ReturnMessage<Umsuser>("1001", "未查询到相关用户！");
    }

    @Override
    public ReturnMessage<UmsuserVO> queryInsideUserByUserId(String userId) throws ENTCMSException {
        UmsuserVO umsuserVO = umsuserMapper.selectInsideUserByUserId(userId);
        if (umsuserVO != null) {
            return new ReturnMessage<UmsuserVO>("100", "查询用户成功！", umsuserVO);
        }
        return new ReturnMessage<UmsuserVO>("1001", "未查询到相关用户！");
    }

    @Override
    public ReturnMessage<Umsuser> fdeleteUmsUserById(String id) throws ENTCMSException {
        Umsuser umsuser = new Umsuser();
        umsuser.setId(id);
        umsuser.setStatus("10");
        int i = umsuserMapper.updateByPrimaryKeySelective(umsuser);
        if(i==1){
            return new ReturnMessage<>("100","删除成功");
        }
        return new ReturnMessage<>("999","删除失败");
    }

    @Override
    public ReturnMessage<Umsuser> fdeleteUmsUserByIds(String[] ids) throws ENTCMSException {
        int count = 0;
        Umsuser umsuser = new Umsuser();
        umsuser.setStatus("10");
        for (String id: ids) {
            umsuser.setId(id);
            int i = umsuserMapper.updateByPrimaryKeySelective(umsuser);
            if(i==1){
                count++;
            }
        }
        return new ReturnMessage<>("100","删除成功",count);
    }
}
