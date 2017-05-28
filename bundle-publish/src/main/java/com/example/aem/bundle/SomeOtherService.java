package com.example.aem.bundle;

import org.apache.felix.scr.annotations.Component;
import org.apache.felix.scr.annotations.Reference;
import org.apache.felix.scr.annotations.Service;

@Service(SomeOtherService.class)
@Component(immediate = true)
public class SomeOtherService {

    @Reference
    private HelloService helloService;

}
