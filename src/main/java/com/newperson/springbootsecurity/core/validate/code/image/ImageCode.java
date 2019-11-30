package com.newperson.springbootsecurity.core.validate.code.image;

import com.newperson.springbootsecurity.core.validate.code.ValidateCode;
import lombok.Data;

import java.awt.image.BufferedImage;
import java.time.LocalDateTime;

/**
 * @author 孙连辉
 * @version 1.0
 * @date 2019/11/28 10:41
 */
@Data
public class ImageCode  extends ValidateCode {
    private BufferedImage bufferedImage;

    public ImageCode(BufferedImage bufferedImage,String code,LocalDateTime expireTime) {
        super(code, expireTime);
        this.bufferedImage=bufferedImage;

    }
    public ImageCode(BufferedImage bufferedImage,String code,int  expire) {

        super(code,expire);
        this.bufferedImage=bufferedImage;

    }

}
