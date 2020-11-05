package com.xuecheng.manage_cms_client.mq;

import com.alibaba.fastjson.JSON;
import com.xuecheng.framework.domain.cms.CmsPage;
import com.xuecheng.framework.exception.ExceptionCast;
import com.xuecheng.framework.model.response.CommonCode;
import com.xuecheng.manage_cms_client.dao.CmsPageRepository;
import com.xuecheng.manage_cms_client.service.PageService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.Optional;

/**
 * @author wuangjing
 * @create 2020/11/5-11:41
 * @Description: 消费端，监听
 */
@Component
public class ConsumerPostPage {

    @Autowired
    private CmsPageRepository cmsPageRepository;
    @Autowired
    private PageService pageService;
    private static final Logger LOGGER = LoggerFactory.getLogger(ConsumerPostPage.class);

    /**
     * 监听方法
     *
     * @param message pageId参数
     */
    @RabbitListener(queues = {"${xuecheng.mq.queue}"})
    public void postPage(String message) {
        try {
            //解析消息
            Map map = JSON.parseObject(message, Map.class);
            //打印日志
            LOGGER.info("receive cms post page:{}", message.toString());
            //取出页面id
            String pageId = (String) map.get("pageId");
            //查询页面信息
            Optional<CmsPage> optional = cmsPageRepository.findById(pageId);
            //判断是否存在相关页面
            if (!optional.isPresent()) {
                LOGGER.error("receive cms page,cmsPage is null:{}", message.toString());
                return;
            }
            //将页面保存到服务器物理路径
            pageService.savePageToServerPath(pageId);
        } catch (Exception e) {
            ExceptionCast.cast(CommonCode.INCORRECT_DATA_TYPE);

        }
    }
}
