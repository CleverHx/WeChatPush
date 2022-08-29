## 如何下载文件

由于有很多小白朋友，不知道如何使用码云和Github，这里做简要介绍：

```
博客教程地址为：https://5hx.ink/492.html
该项目的Github地址为：https://github.com/CleverHx/WeChatPush
该项目的码云地址为：https://gitee.com/cleverhx/we-chat-push
```

**Github下载文件方法**

![image](https://user-images.githubusercontent.com/102504985/187115195-18009b09-634d-47f1-8294-446c02070a7d.png)


点击`Code`，再点击 `Download ZIP`

**码云下载文件方法**

![image](https://user-images.githubusercontent.com/102504985/187115229-e710245f-a637-4edb-b454-167bc76c21e5.png)


先点击`克隆/下载`，再点击`下载ZIP`

前置条件

注册一个微信公众号，利用下面的链接注册一个微信公众测试号

https://mp.weixin.qq.com/debug/cgi-bin/sandbox?t=sandbox/login

扫码登录成功后，就可以生成微信公众测试号的appID和appsecret这两串数字需要记住，后面会用到

![image](https://user-images.githubusercontent.com/102504985/187115277-7bc44dc4-8128-4774-98fa-5cd8fb7bb1ca.png)


扫描测试号二维码后会生成微信号，哪个账号需要接收推送信息就需要哪个账号扫码

![image](https://user-images.githubusercontent.com/102504985/187115298-6f51b7ff-8656-4f1e-9c83-a5bd1e059443.png)


这里需要记住的是对应账号的微信号，也就是user id，后面需要用

## 新增测试模板

![image](https://user-images.githubusercontent.com/102504985/187115333-b4940e30-1b2e-43e1-8cee-254a3b5fa168.png)


这里点击`新增测试模板`

模板标题: 自定义，例如: 亲爱的，早上好!
模板内容参考:

```
今天是{{date.DATA}}{{week.DATA}}
城市：{{city.DATA}}
天气：{{weather.DATA}}
最低(高)温度：{{lowest.DATA}}/{{highest.DATA}}
日出(落)时间：{{sunrise.DATA}}/{{sunset.DATA}}
月出(落)时间：{{moonrise.DATA}}/{{moondown.DATA}}
今日建议：{{tips.DATA}}
今天是我们恋爱的第{{loveday.DATA}}天 
距离我们见面还有{{meetday.DATA}}天 
距离小宝生日还有{{birthday1.DATA}}天 
距离我的生日还有{{birthday2.DATA}}天 
 
{{rain.DATA}}
 
{{xz.DATA}}
今天是{{date.DATA}}{{week.DATA}} 
 
城市：{{city.DATA}}🏡 
 
天气：{{weather.DATA}}⛅ 
 
最低温度：{{lowest.DATA}} ❄️ 
 
最高温度：{{highest.DATA}}🌞 
 
日出/落时间：{{sunrise.DATA}}/{{sunset.DATA}} 🌝 
 
今天是我们恋爱的第{{loveday.DATA}}天💞
 
距离我们见面还有{{meetday.DATA}}天🐾 
 
距离小宝生日还有{{birthday1.DATA}}天 💕 
 
{{rain.DATA}}
{{xz.DATA}}
```

配置文件（application详解），开发环境是1.8。

```
server:
    port: 8080          #端口
date:
    loveDay: "2016-12-02"   #恋爱开始时间
    birthDay1: "2001-02-13"  #对象生日
    birthDay2: "2001-07-25"  #自己生日
    meetDay: "2022-09-09"    #将要见面日期
WeChat:
    appid: "w97a4e711e89"    #appid
    appsecret: "5d80a96ce7b474" #appsecret
    template_id: "JhftDx9dHsulTk1l_d3vWw18f4"  #模板id
    openid: "o44TR-ggGXFC7Y" #网页获取到的微信号,多个用英文逗号分隔
Weather:
    weaapi: "b0111972efa6a" #天气api
    city: "科尔沁左翼后旗"   #地址，支持到三级
    xz: "狮子座"       #星座
    rainapi: ""         #彩红屁api
    xzapi: ""        #星座api
```

教程开始

①将下载后的压缩包解压，打开呆瓜版，修改里面的application.yml配置文件，将自己网页获取到的信息填入到里面。

②点击文件路径，输入cmd回车确定，会弹出一个命令窗口


![image](https://user-images.githubusercontent.com/102504985/187115364-a5253b4f-c069-493e-a1eb-d08f2c661597.png)


③在黑窗口命令界面输入“java -jar wechatpush-0.0.1-SNAPSHOT.jar”，回车确定之后程序就运行起来了，等到程序出现如图界面时，浏览器打开localhost:8080，(这个8080是跟application.yml中的port对应的)就推送成功啦~

一定要改完配置文件再打开黑窗口


![image](https://user-images.githubusercontent.com/102504985/187115434-3f0a51b5-fa4c-410a-9905-2eaa7ea50416.png)

![image](https://user-images.githubusercontent.com/102504985/187115462-259dbcba-1606-417e-8d65-67139826f865.png)
