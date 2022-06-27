package com.avanza.poc.resource;

import com.avanza.poc.model.DecodedQrResponse;
import com.avanza.poc.model.GenerateQrRequest;
import com.avanza.poc.service.QrCodeService;
import com.google.zxing.NotFoundException;
import com.google.zxing.WriterException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.MissingRequestValueException;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.ValidationException;
import java.awt.image.DataBufferByte;
import java.io.IOException;

@RestController
public class QrCodeResource {

    @Autowired
    private QrCodeService qrCodeService;

    @PostMapping(path = "/api/qr/generate", produces = MediaType.IMAGE_JPEG_VALUE)
    public void generateQr(@RequestBody GenerateQrRequest request, HttpServletResponse response) throws MissingRequestValueException, WriterException, IOException {
        if( request==null || request.getQrString()==null || request.getQrString().trim().equals("") ) {
            throw new MissingRequestValueException("QR String is required");
        }
        qrCodeService.generateQr(request.getQrString(), response.getOutputStream());
        response.getOutputStream().flush();
    }

    @PostMapping(path = "/api/qr/decode")
    public DecodedQrResponse decodeQr(@RequestParam("qrCode") MultipartFile qrCode) throws IOException, NotFoundException {
        String qrCodeString =  qrCodeService.decodeQr(qrCode.getBytes());
        return new DecodedQrResponse(qrCodeString);
    }

}
