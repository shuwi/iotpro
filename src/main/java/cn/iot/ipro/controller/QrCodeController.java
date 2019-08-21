package cn.iot.ipro.controller;

import cn.hutool.crypto.symmetric.RC4;
import cn.hutool.extra.qrcode.QrCodeUtil;
import cn.iot.ipro.entity.QrCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.misc.BASE64Encoder;

import javax.imageio.ImageIO;
import javax.validation.Valid;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.nio.charset.Charset;

@CrossOrigin(origins = "*", maxAge = 3600)
@Controller
@RequestMapping("/quote/")
public class QrCodeController {
    /**
     * 产生二维码加密字符串
     *
     * @param usrid  用户ID
     * @param viewId 区域ID
     * @param begin  有效开始时间，格式为yyyyMMddHHmmss，可为空
     * @param end    二维码的有效结束时间，格式为 yyyyMMddHHmmss。
     * @return 加密字符串
     */
    private String genQrCode(String usrid, String viewId, String begin, String end) {
        String key = "CB1712345678";
        String start = "CB01";
        RC4 rc4 = new RC4(key);
        StringBuilder sb = new StringBuilder();
        sb.append("[");
        sb.append(String.join(",", new String[]{usrid, viewId, begin, end}));
        sb.append("]");
        return start + rc4.encryptHex(sb.toString(), Charset.forName("UTF-8"));
    }

    /**
     * 根据传入的二维码信息返回二维码base64数据
     * @param qrCode 传入的二维码信息
     * @param results 二维码base64数据
     * @return 二维码base64数据
     * @throws IOException 图片处理异常
     */
    @PreAuthorize("hasRole('USER')")
    @RequestMapping(value = "gating")
    @ResponseBody
    public ResponseEntity getQrCode(@RequestBody @Valid QrCode qrCode, BindingResult results) throws IOException {
        if (results.hasErrors())
            return null;
        if (!StringUtils.isEmpty(qrCode.getUserId())) {
            String code = genQrCode(qrCode.getUserId(), qrCode.getViewId(), qrCode.getBegin(), qrCode.getEnd());
            BufferedImage bang = QrCodeUtil.generate(code, 300, 300);
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bang, "jpg", outputStream);
            BASE64Encoder encoder = new BASE64Encoder();
            String base64Img = encoder.encode(outputStream.toByteArray());
            return ResponseEntity.ok("data:image/png;base64," + base64Img);
        } else
            return null;
    }
}
