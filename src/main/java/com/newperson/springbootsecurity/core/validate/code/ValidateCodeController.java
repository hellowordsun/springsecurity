package com.newperson.springbootsecurity.core.validate.code;

import com.newperson.springbootsecurity.core.validate.code.image.ImageCode;
import com.newperson.springbootsecurity.core.validate.code.sms.SmsCodeSender;
import com.newperson.springbootsecurity.demo.exception.GlobalException;
import com.newperson.springbootsecurity.demo.utils.JsonData;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.social.connect.web.HttpSessionSessionStrategy;
import org.springframework.social.connect.web.SessionStrategy;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.ServletWebRequest;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 10:53
 */
@RestController
public class ValidateCodeController {


    @Autowired
    private ValidateCodeGenerator imageCodeGenerator;

    @Autowired
    private SmsCodeSender smsCodeSender;

    @Autowired
    private ValidateCodeGenerator smsCodeGenerator;

    public static final String SESSION_KEY="SESSION_IMAGE_KEY";

    private SessionStrategy sessionStrategy=new HttpSessionSessionStrategy();

    @GetMapping("/code/image")
    public void createCode(HttpServletRequest request, HttpServletResponse response) throws IOException {

        ImageCode imageCode=(ImageCode)imageCodeGenerator.generate(request);

        //将图片验证码放入session
        sessionStrategy.setAttribute(new ServletWebRequest(request),SESSION_KEY,imageCode);
        //使用流的方式，传到前台
        ImageIO.write(imageCode.getBufferedImage(),"JPG",response.getOutputStream());

    }

    @PostMapping("/code/sms")
    public JsonData sendSmsCode( String mobile, HttpServletRequest request) throws Exception {
        if (StringUtils.isBlank(mobile)) {
            throw new GlobalException("手机号不能为空",-1,null);
        }
        ValidateCode generate = smsCodeGenerator.generate(request);
        smsCodeSender.smsSend(mobile,generate);
        return JsonData.buildSuccess(generate);
    }


}
