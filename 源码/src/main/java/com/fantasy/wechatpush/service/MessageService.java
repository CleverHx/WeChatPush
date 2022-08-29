package com.fantasy.wechatpush.service;

import com.fantasy.wechatpush.entity.weather;

import java.util.Date;

public interface MessageService {
   public weather weather(String cityName, String cityApi);
   public String vxPush(String appid,String appsecret,String[] userId,String tempId,weather weaS,Date birth1,Date birth2,Date love,Date meet,String rainapi,String xzapi,String star);
   public int birthDay(Date birth);
   public int loveDay(Date love);
   public String rainbow(String rainApi);
   public String constellation(String xzApi,String xz);
}
