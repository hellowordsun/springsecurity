package com.newperson.springbootsecurity.core.validate.code.image;

import com.newperson.springbootsecurity.core.properties.SecurityProperties;
import com.newperson.springbootsecurity.core.validate.code.ValidateCodeGenerator;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.http.HttpServletRequest;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.util.Random;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 15:47
 */
public class ImageCodeGenerator implements ValidateCodeGenerator {


    @Autowired
    private SecurityProperties securityProperties;

    @Override
    public ImageCode generate(HttpServletRequest request) {
        int width = securityProperties.getCode().getImage().getWidth();
        // 图片的高度。
        int height = securityProperties.getCode().getImage().getHeight();
        // 验证码字符个数
        int length = securityProperties.getCode().getImage().getLength();
        // 验证码干扰线数
        int lineCount = securityProperties.getCode().getImage().getLines();
        //过期时间
        int expire=securityProperties.getCode().getImage().getExpire();
        // 验证码
        String code = null;
        //
        BufferedImage buffImg = null;


        char[] codeSequence = { 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'P', 'Q', 'R',
                'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '1', '2', '3', '4', '5', '6', '7', '8', '9' };
        int x = 0, fontHeight = 0, codeY = 0;
        int red = 0, green = 0, blue = 0;
        x = width / (length + 2);
        fontHeight = height - 2;
        codeY = height - 4;
        buffImg = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = buffImg.createGraphics();
        Random random = new Random();
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        Font font = new Font("Fixedsys", Font.PLAIN, fontHeight);
        g.setFont(font);
        for (int i = 0; i < lineCount; i++) {

            int xs = random.nextInt(width);
            int ys = random.nextInt(height);
            int xe = xs + random.nextInt(width / 8);
            int ye = ys + random.nextInt(height / 8);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawLine(xs, ys, xe, ye);
        }

        StringBuffer randomCode = new StringBuffer();
        for (int i = 0; i < length; i++) {
            String strRand = String.valueOf(codeSequence[random.nextInt(codeSequence.length)]);
            red = random.nextInt(255);
            green = random.nextInt(255);
            blue = random.nextInt(255);
            g.setColor(new Color(red, green, blue));
            g.drawString(strRand, (i + 1) * x, codeY);
            randomCode.append(strRand);
        }
        // 将四位数字的验证码保存到Session中。
        code = randomCode.toString();
        return new ImageCode(buffImg,code,expire);
    }
}
