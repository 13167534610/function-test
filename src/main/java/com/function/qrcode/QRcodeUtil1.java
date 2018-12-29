package com.function.qrcode;

import net.glxn.qrgen.core.image.ImageType;
import net.glxn.qrgen.javase.QRCode;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;

/**
 * description:
 *
 * @author wangqiang
 */
public class QRcodeUtil1 {
    public static void getQRcode(HttpServletResponse response, String text) throws IOException {
        ByteArrayOutputStream stream = QRCode.from(text).to(ImageType.PNG).stream();
        response.setContentType("image/png");
        response.setContentLength(stream.size());
        OutputStream outStream = response.getOutputStream();
        outStream.write(stream.toByteArray());
        outStream.flush();
        outStream.close();
    }
}
