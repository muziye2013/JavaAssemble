package com.tison.basic.web;

import com.tison.basic.facade.SearchFacade;
import com.tison.basic.model.SearchResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author tison
 * @date 2019/10/22
 * @description
 */
@RestController
@RequestMapping("/search")
public class SearchController {

    @Resource
    private SearchFacade searchFacade;

    @GetMapping("/all")
    public SearchResult searchAll(String text){
        return searchFacade.searchAll(text);
    }
}
