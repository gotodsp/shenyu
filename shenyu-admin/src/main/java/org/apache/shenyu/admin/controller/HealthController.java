package org.apache.shenyu.admin.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;


/**
 * @author dsp
 */
@RestController
public class HealthController {

    @GetMapping("/health")
    public String check() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(300);
        return "I'm fine!";
    }

}
