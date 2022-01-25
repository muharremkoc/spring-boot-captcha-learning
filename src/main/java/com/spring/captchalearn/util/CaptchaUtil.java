package com.spring.captchalearn.util;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

import javax.imageio.ImageIO;

import cn.apiclub.captcha.Captcha;
import cn.apiclub.captcha.backgrounds.GradiatedBackgroundProducer;
import cn.apiclub.captcha.noise.CurvedLineNoiseProducer;
import cn.apiclub.captcha.text.producer.DefaultTextProducer;
import cn.apiclub.captcha.text.renderer.DefaultWordRenderer;

public class CaptchaUtil {

    public static Captcha create(Integer width,Integer height){
        return new Captcha.Builder(width,height)
                .addBackground(new GradiatedBackgroundProducer())
                .addText(new DefaultTextProducer(),new DefaultWordRenderer())
                .addNoise(new CurvedLineNoiseProducer())
                .build();
    }
    public static String encode(Captcha captcha){
        String captchaImage = null;
        try {
            ByteArrayOutputStream bos= new ByteArrayOutputStream();
            ImageIO.write(captcha.getImage(),"jpg", bos);
            byte[] byteArray= Base64.getEncoder().encode(bos.toByteArray());
            captchaImage = new String(byteArray);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return captchaImage;
    }
}
