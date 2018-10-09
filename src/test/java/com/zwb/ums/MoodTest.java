package com.zwb.ums;

import com.zwb.ums.mapper.MoodMapper;
import com.zwb.ums.model.po.Mood;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * <p>
 * Title: MoodTest
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Company: 都市放牛集团
 * </p>
 *
 * @author zhouwenbo
 * @version 1.0
 * @date 2018/5/5 16:06
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class MoodTest {

    private final Logger logger = LoggerFactory.getLogger(LoggerTest.class);

    @Autowired
    private MoodMapper moodMapper;

    @Test
    public void testMood(){
        String moodId = "b45c8dc60c08923c9d28b4a5489cfb1";
        Mood mood = moodMapper.selectByPrimaryKey(moodId);
        logger.info("查询成功："+mood.getMoodTitle());
    }

}
