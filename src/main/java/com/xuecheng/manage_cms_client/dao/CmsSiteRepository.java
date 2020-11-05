package com.xuecheng.manage_cms_client.dao;

import com.xuecheng.framework.domain.cms.CmsSite;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * @author wuangjing
 * @create 2020/11/5-11:51
 * @Description:Cms网站 查询站点信息，主要获取站点物理路径
 */
public interface CmsSiteRepository extends MongoRepository<CmsSite, String> {
}
