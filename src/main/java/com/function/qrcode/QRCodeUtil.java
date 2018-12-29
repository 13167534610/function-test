package com.function.qrcode;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.WriterException;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.QRCodeWriter;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.HashMap;

/**
 * description:
 *
 * @author wangqiang
 */
public class QRCodeUtil {
    public static void getQECode(OutputStream outputStream){
        int size=600;
        String format="JPEG";
        String contents="www.baidu.com";
        HashMap map = new HashMap();
        map.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.M);
        map.put(EncodeHintType.CHARACTER_SET, "utf-8");
        map.put(EncodeHintType.MARGIN, 0);
        try {
            BitMatrix bm = new QRCodeWriter().encode(contents, BarcodeFormat.QR_CODE, size/2, size/2, map);
            int width = bm.getWidth();
            BufferedImage image = new BufferedImage(width, width, BufferedImage.TYPE_INT_RGB);
            Graphics2D graphics = image.createGraphics();
            graphics.setColor(Color.blue);
            graphics.fillRect(0, 0, width, width);
            graphics.setColor(Color.BLACK);
            for (int i = 0; i < width; i++) {
                for (int j = 0; j < width; j++) {
                    if (bm.get(i, j)) {
                        graphics.fillRect(i, j, 1, 1);
                    }
                }
            }
            ImageIO.write(image,format,outputStream);
            outputStream.close();
        } catch (WriterException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) throws IOException {
        getQECode(new FileOutputStream(new File("d:\\qrcode.jpg")));
        QrCodeCreateUtil.readQrCode(new FileInputStream(new File("d:\\qrcode.jpg")));
    }
}
