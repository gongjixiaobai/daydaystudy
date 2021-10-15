package com.gongjixiaobai.controller;

import com.gongjixiaobai.model.Metaspace;
import com.gongjixiaobai.model.User;
import com.google.common.collect.Lists;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author weiming02
 */
@RestController
public class MemoryController {
    private List<User> userList = Lists.newArrayList();
    private List<Class> classList = Lists.newArrayList();


    /**
     * @return
     */
    @GetMapping("heap")
    public String heap() {

        int i = 0;
        while (true) {
            userList.add(new User(i++, RandomStringUtils.random(8)));
        }
    }

    /**
     * @return
     */
    @GetMapping("nonheap")
    public String nonheap() {
        while (true) {
            classList.addAll(Metaspace.createClasses());
        }
    }
}
