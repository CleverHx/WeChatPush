package com.fantasy.wechatpush.service.impl;


import com.alibaba.fastjson.JSONObject;
import com.fantasy.wechatpush.entity.weather;
import com.fantasy.wechatpush.service.MessageService;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.mp.api.WxMpInMemoryConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.impl.WxMpServiceImpl;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateData;
import me.chanjar.weixin.mp.bean.template.WxMpTemplateMessage;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;


@Service
public class MessageServiceImpl implements MessageService {

   @Override
   public weather weather(String cityName,String cityApi){
       try {
           cityName=URLEncoder.encode(cityName ,"UTF-8");
       } catch (UnsupportedEncodingException e) {
           e.printStackTrace();
       }
        String httpUrl = "http://api.tianapi.com/tianqi/index?key="+cityApi+"&city="+cityName;
        String jsonResult = request(httpUrl);
        JSONObject jsonObject = JSONObject.parseObject(jsonResult);
        JSONObject objectData1 = (JSONObject)jsonObject.getJSONArray("newslist").get(0);
        weather wea = JSONObject.toJavaObject(objectData1,weather.class);

        return wea;
   }

    @Override
    public String vxPush(String appid,String appsecret,String[] userId,String tempId,weather wea,Date birth1,Date birth2,Date love,Date meet,String rainpi,String xzapi,String star){
        WxMpInMemoryConfigStorage wxStorage = new WxMpInMemoryConfigStorage();
        wxStorage.setAppId(appid);
        wxStorage.setSecret(appsecret);
        WxMpService wxMpService = new WxMpServiceImpl();
        wxMpService.setWxMpConfigStorage(wxStorage);
        String[] color = {"#EA0000","#ff7575","#FF359A","#FF0080","#9F0050","#930093","#E800E8","#8600FF","#BE77FF","#3A006F","#0000C6",
        "#6A6AFF","#0066CC","#009393","#00FFFF","#02F78E","#006000","#79FF79","#8CEA00","#8C8C00","#FFD306","#BB5E00","#FF9224","#F75000","#AE57A4",
        "#5A5AAD","#984B4B","#6FB7B7"};
        List<String> colors = new ArrayList<>();
        while (true) {
            Random r = new Random();
            if(!colors.contains(color[r.nextInt(color.length)])){
                colors.add(color[r.nextInt(color.length)]);
            }
            if (colors.size()==17) break;
        }

        for (String s : userId) {
            WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
                    .toUser(s)
                    .templateId(tempId)
                    .build();
            templateMessage.addData(new WxMpTemplateData("city", wea.getArea(), colors.get(0)));
            templateMessage.addData(new WxMpTemplateData("date", wea.getDate(), colors.get(1)));
            templateMessage.addData(new WxMpTemplateData("week", wea.getWeek(), colors.get(2)));
            templateMessage.addData(new WxMpTemplateData("weather", wea.getWeather(), colors.get(3)));
            templateMessage.addData(new WxMpTemplateData("lowest", wea.getLowest(), colors.get(4)));
            templateMessage.addData(new WxMpTemplateData("highest", wea.getHighest(), colors.get(5)));
            templateMessage.addData(new WxMpTemplateData("sunrise", wea.getSunrise(), colors.get(6)));
            templateMessage.addData(new WxMpTemplateData("sunset", wea.getSunset(), colors.get(7)));
            templateMessage.addData(new WxMpTemplateData("moonrise", wea.getMoonrise(), colors.get(8)));
            templateMessage.addData(new WxMpTemplateData("moondown", wea.getMoondown(), colors.get(9)));
            templateMessage.addData(new WxMpTemplateData("tips", wea.getTips(), colors.get(10)));
            templateMessage.addData(new WxMpTemplateData("birthday1", birthDay(birth1)+"", colors.get(11)));
            templateMessage.addData(new WxMpTemplateData("birthday2", birthDay(birth2)+"", colors.get(12)));
            templateMessage.addData(new WxMpTemplateData("loveday", loveDay(love)+"", colors.get(13)));
            templateMessage.addData(new WxMpTemplateData("meetday", loveDay(meet)+"", colors.get(14)));
            templateMessage.addData(new WxMpTemplateData("rain", rainbow(rainpi), colors.get(15)));
            templateMessage.addData(new WxMpTemplateData("xz", constellation(xzapi,star), colors.get(16)));
            try {
                String msg = wxMpService.getTemplateMsgService().sendTemplateMsg(templateMessage);
            } catch (WxErrorException e) {
                System.out.println("推送失败，请检查配置");
            }
        }
        return "推送成功啦";
    }

    @Override
    public int birthDay(Date birth){
        Date thisDay = new Date();
        SimpleDateFormat sdf1 = new SimpleDateFormat("yyyy-MM-dd");
        Calendar today = Calendar.getInstance();
        today.setTime(thisDay);
        Calendar CBirthday = Calendar.getInstance();
        CBirthday.setTime(birth);
        CBirthday.set(Calendar.YEAR,today.get(Calendar.YEAR));
        int days = 0;
        if (CBirthday.get(Calendar.DAY_OF_YEAR)<today.get(Calendar.DAY_OF_YEAR)){
            days = today.getActualMaximum(Calendar.DAY_OF_YEAR) - today.get(Calendar.DAY_OF_YEAR);
            CBirthday.set(Calendar.YEAR, today.get(Calendar.YEAR)+1);
            days += CBirthday.get(Calendar.DAY_OF_YEAR);
        }else{
            days = CBirthday.get(Calendar.DAY_OF_YEAR)-today.get(Calendar.DAY_OF_YEAR);
        }
       return days;
    }

    @Override
    public int loveDay(Date love){
       Date thisDay = new Date();
       return Math.abs((int) ((thisDay.getTime() - love.getTime()) / (1000 * 3600 * 24)));
    }
    @Override
    public String rainbow(String rainApi){
       if(rainApi.equals("") || rainApi == null){
           return "";
       }else {
           String jsonResult = request("http://api.tianapi.com/caihongpi/index?key=" + rainApi);
           JSONObject jsonObject = JSONObject.parseObject(jsonResult);
           JSONObject objectData1 = (JSONObject) jsonObject.getJSONArray("newslist").get(0);
           return objectData1.getString("content");
       }
    }
    @Override
    public String constellation(String xzApi,String xz){
        if(xzApi.equals("") || xzApi == null){
            return "";
        }else {
            String jsonResult = request("http://api.tianapi.com/star/index?key=" + xzApi + "&astro=" + xz);
            JSONObject jsonObject = JSONObject.parseObject(jsonResult);
            JSONObject objectData1 = (JSONObject) jsonObject.getJSONArray("newslist").get(1);
            JSONObject objectData2 = (JSONObject) jsonObject.getJSONArray("newslist").get(5);
            JSONObject objectData3 = (JSONObject) jsonObject.getJSONArray("newslist").get(6);
            JSONObject objectData4 = (JSONObject) jsonObject.getJSONArray("newslist").get(8);
            return objectData1.getString("type") + "：" + objectData1.getString("content") + "\n" +
                    objectData2.getString("type") + "：" + objectData2.getString("content") + "\n" +
                    objectData3.getString("type") + "：" + objectData3.getString("content") + "\n" +
                    objectData4.getString("type") + "：" + objectData4.getString("content") + "\n";
        }
    }

    public static String request(String httpUrl) {
        BufferedReader reader = null;
        String result = null;
        StringBuffer sbf = new StringBuffer();
        try {
            URL url = new URL(httpUrl);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();
            connection.setRequestMethod("GET");
            InputStream is = connection.getInputStream();
            reader = new BufferedReader(new InputStreamReader(is, "UTF-8"));
            String strRead = null;
            while ((strRead = reader.readLine()) != null) {
                sbf.append(strRead);
                sbf.append("\r\n");
            }
            reader.close();
            result = sbf.toString();
        } catch (Exception e) {
            System.out.println("请检查城市名称和key是否正确");
        }
        return result;
    }

}

