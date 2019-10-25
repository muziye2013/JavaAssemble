package com.tison.basic.service.impl;

import com.tison.basic.model.SearchResult;
import com.tison.basic.service.ISearchService;
import com.tison.basic.util.MathUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import static com.tison.basic.util.TimeUtil.getNowTime;

/**
 * @author tison
 * @date 2019/10/22
 * @description 文字搜素
 */
@Slf4j
@Service("words")
public class WordsSearchService implements ISearchService {

    @Override
    public SearchResult search(String text) {
        long begin = getNowTime();
        try{
            Thread.sleep(MathUtil.getRdTime(1000));
        }catch (InterruptedException e){
            log.warn("search error, e = {}", e);
        }
        log.info("words search end cost={} ms", getNowTime()-begin);
        return new SearchResult("words search result!");
    }
}
