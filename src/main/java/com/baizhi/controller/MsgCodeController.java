package com.baizhi.controller;

import com.aliyuncs.DefaultAcsClient;
import com.aliyuncs.IAcsClient;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsRequest;
import com.aliyuncs.dysmsapi.model.v20170525.SendSmsResponse;
import com.aliyuncs.exceptions.ClientException;
import com.aliyuncs.http.MethodType;
import com.aliyuncs.profile.DefaultProfile;
import com.aliyuncs.profile.IClientProfile;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Random;

@Controller
@RequestMapping("/identify")
public class MsgCodeController {

    @RequestMapping("/code")
    @ResponseBody
    public void getCode(String phone) {
        char[] array = "0123456789".toCharArray();
        StringBuilder sbu = new StringBuilder();
        Random random = new Random();

        int cycleLength = 4;
        for (int i = 1; i <= cycleLength; i++) {
            int index = random.nextInt(array.length);
            sbu.append(array[index]);
        }
        String codeContain = sbu.toString();

        System.setProperty("sun.net.client.defaultConnectTimeout", "10000");
        System.setProperty("sun.net.client.defaultReadTimeout", "10000");
        final String product = "Dysmsapi";//短信API产品名称（短信产品名固定，无需修改）
        final String domain = "dysmsapi.aliyuncs.com";//短信API产品域名（接口地址固定，无需修改）
        final String accessKeyId = "LTAIFBm4MggRQCE6";//你的accessKeyId,参考本文档步骤2
        final String accessKeySecret = "QvZuybTABR8Xmrkt2QH3cmex5Nqs2Q";//你的accessKeySecret，参考本文档步骤2
        IClientProfile profile = DefaultProfile.getProfile("cn-hangzhou", accessKeyId,
                accessKeySecret);
        try {
            DefaultProfile.addEndpoint("cn-hangzhou", "cn-hangzhou", product, domain);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        IAcsClient acsClient = new DefaultAcsClient(profile);
        SendSmsRequest request = new SendSmsRequest();
        request.setMethod(MethodType.POST);
        request.setPhoneNumbers(phone);
        request.setSignName("何腾飞");
        request.setTemplateCode("SMS_141606919");
        //request.setTemplateParam("{\"code\":\"8888\"}");
        String prefix = "{code:" + codeContain + "}";
        //codeContain设置到redis
        request.setTemplateParam(prefix);
        request.setOutId("yourOutId");

        SendSmsResponse sendSmsResponse = null;
        try {
            sendSmsResponse = acsClient.getAcsResponse(request);
        } catch (ClientException e) {
            e.printStackTrace();
        }
        if (sendSmsResponse.getCode() != null && sendSmsResponse.getCode().equals("OK")) {
        }
    }
}
