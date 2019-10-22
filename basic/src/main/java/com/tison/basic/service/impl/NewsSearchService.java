package com.tison.basic.service.impl;

import com.tison.basic.model.SearchResult;
import com.tison.basic.service.ISearchService;
import com.tison.basic.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tison
 * @date 2019/10/22
 * @description 新闻搜素
 */
@Slf4j
@Service("news")
public class NewsSearchService implements ISearchService {

    @Override
    public SearchResult search(String text) {
        try{
            Thread.sleep(MathUtil.getRdTime(1000));
        }catch (InterruptedException e){
            log.warn("search error, e = {}", e);
        }
        log.info("news search end {}", System.currentTimeMillis());
        return new SearchResult("news search result!");
    }
}
