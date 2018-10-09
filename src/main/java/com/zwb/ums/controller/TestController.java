package com.zwb.ums.controller;

import com.zwb.ums.exception.ResponseBankException;
import com.zwb.ums.mapper.MoodMapper;
import com.zwb.ums.model.po.Mood;
import com.zwb.ums.service.WebSocket;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;

/**
 * <p>
 * Title: TestController
 * </p>
 * <p>
 * Description: 测试Controller
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/4 11:19
 */
@Controller
public class TestController {

    @Resource
    private MoodMapper moodMapper;

    @Autowired
    private WebSocket webSocket;

    @RequestMapping("/test/mood/detail/{moodId}")
    @ResponseBody
    public Mood queryMoodById(@PathVariable("moodId") String moodId) {
        if("403".equals(moodId)){
            throw new ResponseBankException();
        }

        Mood mood = moodMapper.selectByPrimaryKey(moodId);
        //发送webSocket消息
        webSocket.sendMessage("测试查询心情数据。。。");
        return mood;
    }

}
