package com.tison.basic.facade;

import com.tison.basic.model.SearchResult;
import com.tison.basic.service.AsyncService;
import com.tison.basic.service.ISearchService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author tison
 * @date 2019/10/22
 * @description 搜索Facade层，放置跨service调用，避免过大的service体
 */
@Slf4j
@Service
public class SearchFacade {

    @Resource
    private AsyncService asyncService;

    @Autowired
    private final Map<String, ISearchService> searchServiceMap = new ConcurrentHashMap<>();

    public SearchFacade(Map<String, ISearchService> searchServiceMap) {
        this.searchServiceMap.clear();
        searchServiceMap.forEach((k, v)-> this.searchServiceMap.put(k, v));
    }

    /**
     * 聚合搜索
     * @param context
     * @return
     */
    public SearchResult searchAll(String context){
        log.info("search method begin, context={}", context);
        //聚合结果Map
        Map<String, Object> resultMap = new ConcurrentHashMap<>(searchServiceMap.size());
        try{
            //Future集合
            List<CompletableFuture<SearchResult>> futureList = new ArrayList<>();
            //遍历执行异步查询
            for(Map.Entry<String, ISearchService> entry : searchServiceMap.entrySet()){
                String type = entry.getKey();
                CompletableFuture<SearchResult> future = asyncService.search(type, context);
                futureList.add(future);
                future.thenAccept(searchResult -> resultMap.put(type, searchResult.getData()));
            }
            //阻塞等待最后一个返回
            CompletableFuture.allOf(futureList.toArray(new CompletableFuture[futureList.size()])).join();
            log.info("<=====search all end=====>\n resultMap = {}, time = {}", resultMap, System.currentTimeMillis());
            return new SearchResult(resultMap);
        }catch (Exception e){
            log.error("method error, e={}", e);
        }
        return new SearchResult(500, "service error");
    }
}
