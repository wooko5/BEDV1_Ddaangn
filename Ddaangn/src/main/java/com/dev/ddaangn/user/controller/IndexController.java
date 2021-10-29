//package com.dev.ddaangn.user.controller;
//
//import com.dev.ddaangn.user.dto.SessionUser;
//import lombok.RequiredArgsConstructor;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.servlet.ModelAndView;
//
//import javax.servlet.http.HttpSession;
//
////@Controller
////@RequiredArgsConstructor
////public class IndexController {
////    private final HttpSession httpSession;
//
//
//    @Controller
//    @RequiredArgsConstructor
//    public class IndexController {
//
//        @GetMapping("/")
//        public String index(Model model){
////            model.addAttribute("posts", postsService.findAllDesc());
//
////            if(user != null){
////                System.out.println("여기도!@@@@");
//////                model.addAttribute("userName", user.getName());
////            }
//            return "index";
//        }
//
//        @GetMapping("/login")
//        public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
//            System.out.println("#########" + code);
////            String access_Token = kakaoService.getAccessToken(code);
////            System.out.println("###access_Token#### : " + access_Token);
//            return "testPage";
//        }
//
//    }
////    @RequestMapping("/login")
////    public String home(@RequestParam(value = "code", required = false) String code) throws Exception{
////        System.out.println("#########" + code);
////        return "testPage";
////    }
//
//
//
//
////}