package org.zerock.controller;

import org.springframework.security.access.annotation.Secured;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.extern.log4j.Log4j;

@Log4j
@RequestMapping("/sample/*")
@Controller
public class SampleController
{
    @GetMapping("/all")
    public void doAll()
    {
        log.info("do all can access everybody");
    }
    
    @GetMapping("/member")
    public void doMember()
    {
        log.info("logined member");
    }
    
    @GetMapping("/admin")
    public void doAdmin()
    {
        log.info("admin only");
    }
    
    /*
     *      어노테이션을 이용하는 스프링 시큐리티 설정
     *      - security-context.xml이 아닌 servlet-context.xml에 관련 설정이 추가된다
     */
    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_MEMBER')")
    @GetMapping("/annoMember")
    public void doMember2()
    {
        log.info("logined annotation member");
    }
    
    @Secured({"ROLE_ADMIN"})
    @GetMapping("/annoAdmin")
    public void doAdmin2()
    {
        log.info("admin annotatino only");
    }
}
