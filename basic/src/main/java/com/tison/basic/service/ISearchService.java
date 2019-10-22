package com.tison.basic.service;

import com.tison.basic.model.SearchResult;

/**
 * @author tison
 * @date 2019/10/22
 * @description 搜索服务接口
 */
public interface ISearchService {

    /**
     * 搜索接口定义
     * @param text 内容
     * @return
     */
    SearchResult search(String text);
}
