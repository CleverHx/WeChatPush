package com.fantasy.wechatpush.controller;

import com.fantasy.wechatpush.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.text.SimpleDateFormat;
import java.util.Date;

@Controller
public class index {
    @Autowired
    MessageService messageService;

    @Value("${date.meetDay}")
    private String meetDay;
    @Value("${date.loveDay}")
    private String loveday;
    @Value("${date.birthDay1}")
    private String birthDay1;
    @Value("${date.birthDay2}")
    private String birthDay2;
    @Value("${WeChat.appid}")
    private String appid;
    @Value("${WeChat.appsecret}")
    private String appsecret;
    @Value("${WeChat.template_id}")
    private String template_id;
    @Value("#{'${WeChat.openid}'.split(',')}")
    private String[] openid;
    @Value("${Weather.weaapi}")
    private String api;
    @Value("${Weather.city}")
    private String city;
    @Value("${Weather.xz}")
    private String xz;
    @Value("${Weather.rainapi}")
    private String rainapi;
    @Value("${Weather.xzapi}")
    private String xzapi;

    @ResponseBody
    @RequestMapping(value = "/")
    public String index() throws Exception{

        String[] user = openid;
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyy-MM-dd");
        Date birth1 = dateformat.parse(birthDay1);
        Date birth2 = dateformat.parse(birthDay2);
        Date love = dateformat.parse(loveday);
        Date meet = dateformat.parse(meetDay);
        return messageService.vxPush(appid,appsecret,user,template_id, messageService.weather(city,api), birth1,birth2,love,meet,rainapi,xzapi,xz);
    }
}
