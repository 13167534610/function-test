package com.controller;

import com.function.qrcode.QRCodeUtil;
import com.function.qrcode.QRcodeUtil1;
import com.google.zxing.WriterException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

import static com.function.qrcode.QrCodeCreateUtil.createQrCode;


/**
 * description:生成二维码
 *
 * @author wangqiang
 */
@Controller
public class QRCodeTest {
    @RequestMapping("/produceQRCode.htm")
    public void produceQRCode(HttpServletResponse response) throws IOException, WriterException {
        ServletOutputStream outputStream = response.getOutputStream();
        String content = "www.baidu.com";
        Integer qrCodeSize = 200;
        String imageFormat = "JPEG";
        createQrCode(outputStream, content, qrCodeSize, imageFormat);
    }
    @RequestMapping("/produceQRCode1.htm")
    public void produceQRCode1(HttpServletResponse response) throws IOException, WriterException {
        QRCodeUtil.getQECode(response.getOutputStream());
    }

    @RequestMapping("/produceQRCode2.htm")
    public void produceQRCode2(HttpServletResponse response) throws IOException {
        String text = "www.baidu.com";
        QRcodeUtil1.getQRcode(response, text);
    }
}
