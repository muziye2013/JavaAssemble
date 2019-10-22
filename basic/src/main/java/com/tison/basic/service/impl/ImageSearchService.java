package com.tison.basic.service.impl;

import com.tison.basic.model.SearchResult;
import com.tison.basic.service.ISearchService;
import com.tison.basic.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

/**
 * @author tison
 * @date 2019/10/22
 * @description 图片搜素
 */
@Slf4j
@Service("image")
public class ImageSearchService implements ISearchService {

    @Override
    public SearchResult search(String text) {
        try{
            Thread.sleep(MathUtil.getRdTime(1000));
        }catch (InterruptedException e){
            log.warn("search error, e = {}", e);
        }
        log.info("image search end {}", System.currentTimeMillis());
        return new SearchResult("image search result!");
    }
}
