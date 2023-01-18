package ru.prostopodpis.api.services;

import com.google.zxing.WriterException;

import java.io.IOException;

/**
 * author: Sergey Khaylov
 * email: <a href="mailto:info@proextlab.ru">info@proextlab.ru</a>
 * org: ProextLab
 * site: <a href="https://proextlab.ru">ProextLab</a>
 **/

public interface QRCodeGenerator {
    byte[] generateQRCode(String text, int width, int height) throws WriterException, IOException;
}
