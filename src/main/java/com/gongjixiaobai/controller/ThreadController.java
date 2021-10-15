package com.gongjixiaobai.controller;


import com.gongjixiaobai.util.ThreadUtil;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ThreadController {
    @GetMapping("api/stackinfo")
    public String stackinfo() {
        String str = "<strong>Memory:</strong>";
        str += "<ol>";
        str += "<li>freeMemory=" + Runtime.getRuntime().freeMemory() / (1024 * 1024) + "M</li>";
        str += "<li>totalMemory=" + Runtime.getRuntime().totalMemory() / (1024 * 1024) + "M</li>";
        str += "<li>maxMemory=" + Runtime.getRuntime().maxMemory() / (1024 * 1024) + "M</li>";
        str += "</ol>";
        str += "<br/>";
        str += "<strong>Thread:</strong>";
        str += "<ol>";
        for (Thread t : ThreadUtil.listThreads()) {
            str += "<li>" + t.getName() + "," + t.getState() + ":" + t.getClass().getName() + "</li>";
            StackTraceElement[] elems = t.getStackTrace();
            str += "<ol>";
            for (StackTraceElement elem : elems) {
                str += "<li>    " + elem.toString() + "</li>";
            }
            str += "</ol>";
        }
        str += "</ol>";
        return str;
    }
}
