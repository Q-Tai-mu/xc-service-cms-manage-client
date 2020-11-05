package com.xuecheng.manage_cms_client.mq;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author wuangjing
 * @create 2020/11/5-11:41
 * @Description: 消费端，监听
 */
@Component
public class ConsumerPostPage {

    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);

    @RabbitListener(queues = {"${xuecheng.mq.queue}"})
    public void postPage(String message) {
        //解析消息
        Map map = JSON.parseObject(message, Map.class);
        //打印日志
        LOGGER.info("receive cms post page:{}", message.toString());
        //取出页面id
        String pageId = (String)map.get("pageId");
        //查询页面信息

        //将页面保存到服务器物理路径

    }
}
