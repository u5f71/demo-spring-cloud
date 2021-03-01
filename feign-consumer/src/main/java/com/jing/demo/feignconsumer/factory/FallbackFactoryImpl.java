package com.jing.demo.feignconsumer.factory;

import com.jing.demo.userapi.UserApi;
import com.jing.demo.userapi.bo.Person;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @description: 使用 fallbackFactory 全局处理降级逻辑
 * @author: jcwang
 * @create: 2021-03-01 17:06
 **/
@Component
@Slf4j
public class FallbackFactoryImpl implements FallbackFactory<UserApi> {
    @Override
    public UserApi create(Throwable throwable) {
        return new UserApi() {
            /**
             * 用于降级时, 返回一个默认的用户
             * @return
             */
            private Person buildDefaultPerson() {
                Person p = new Person();
                p.setName("system");
                p.setAge(18);
                return p;
            }

            @Override
            public String alive() {
                log.info("alive throwable: {}", throwable);
                return "alive fallback";
            }

            @Override
            public Person getById(Integer id) {
                log.info("getById throwable: {}", throwable.getLocalizedMessage());
                return this.buildDefaultPerson();
            }

            @Override
            public Person postPerson(Person person) {
                log.info("postPerson throwable: {}", throwable.getLocalizedMessage());
                return this.buildDefaultPerson();
            }
        };
    }
}
