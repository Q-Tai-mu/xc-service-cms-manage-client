package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsPage;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wuangjing
 * @create 2020/11/5-11:50
 * @Description:Cms页面 信息
 *
 */
public interface CmsPageRepository extends MongoRepository<CmsPage, String> {
}
