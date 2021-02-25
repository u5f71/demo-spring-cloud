package com.jing.demo.userapi;

import com.jing.demo.userapi.bo.Person;
import org.springframework.web.bind.annotation.*;

/**
 * @description:
 * @author: jcwang
 * @create: 2021-02-24 16:48
 **/
@RequestMapping("/User")
public interface UserApi {

    /**
     * 是否可用
     *
     * @return
     */
    @GetMapping("/alive")
    String alive();

    /**
     * 按id查询
     *
     * @param id
     * @return
     */
    @GetMapping("/getById")
    Person getById(
        @RequestParam("id")
            Integer id);

    /**
     * post
     *
     * @param person
     * @return
     */
    @PostMapping("/postPerson")
    Person postPerson(
        @RequestBody
            Person person);
}
