//package com.org.froom.service.impl;
//
//import com.fasterxml.jackson.databind.DeserializationFeature;
//import com.fasterxml.jackson.databind.ObjectMapper;
//import com.google.zxing.WriterException;
//import com.org.froom.dto.EmailUserInfo;
//import com.org.froom.service.QRCodeGenerator;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
//import freemarker.template.TemplateException;
//import org.apache.commons.lang3.text.StrSubstitutor;
//import org.apache.tomcat.util.http.fileupload.IOUtils;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ClassPathResource;
//import org.springframework.http.ResponseEntity;
//import org.springframework.mail.javamail.JavaMailSender;
//import org.springframework.mail.javamail.MimeMessageHelper;
//import org.springframework.stereotype.Service;
//import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;
//import org.springframework.web.client.RestTemplate;
//
//import javax.mail.MessagingException;
//import javax.mail.internet.MimeMessage;
//import java.io.File;
//import java.io.FileInputStream;
//import java.io.IOException;
//import java.io.StringWriter;
//import java.net.URI;
//import java.net.URISyntaxException;
//import java.nio.charset.StandardCharsets;
//import java.nio.file.Files;
//import java.nio.file.Paths;
//import java.time.LocalDateTime;
//import java.util.Base64;
//import java.util.HashMap;
//import java.util.Map;
//
//@Service
//public class EmailService {
//
//    @Autowired
//    RestTemplate restTemplate;
//
//    private static final String QR_CODE_IMAGE_PATH = "./src/main/resources/static/img/";
//    private static final String QR_CODE_IMAGE_PATH_2 = "static/img/";
//    private static final String SIZE = "100x100";
//
//
//    final Configuration configuration;
//    final JavaMailSender javaMailSender;
//
//    public EmailService(Configuration configuration, JavaMailSender javaMailSender) {
//        this.configuration = configuration;
//        this.javaMailSender = javaMailSender;
//    }
//
//    public void sendEmail(EmailUserInfo emailNotificationDtls) throws MessagingException, IOException, TemplateException, URISyntaxException {
//        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
//        MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, MimeMessageHelper.MULTIPART_MODE_MIXED, StandardCharsets.UTF_8.name());
//        Template t = null;
//        if("NEW".equalsIgnoreCase(emailNotificationDtls.getAction())) {
//            helper.setSubject("Purchase Order Status : FROOM");
//            t = configuration.getTemplate("froom-action-change.ftlh");
//        } else if("MERCHANT_UPDATE".equalsIgnoreCase(emailNotificationDtls.getAction())) {
//            helper.setSubject("Order Status Update From Merchant: FROOM");
//            t = configuration.getTemplate("froom-action-update.ftlh");
//        } else if("MANUFACTURER_SHIPPED".equalsIgnoreCase(emailNotificationDtls.getAction())) {
//            helper.setSubject("Merchant Shipped Order: FROOM");
//            t = configuration.getTemplate("froom-action-mf-update.ftlh");
//        } else if("FROOM_RECEIVED".equalsIgnoreCase(emailNotificationDtls.getAction())) {
//            helper.setSubject("Froom Received Order: FROOM");
//            t = configuration.getTemplate("froom-action-f2m-update.ftlh");
//        } else if("CUSTOMER_NOTIFICATION".equalsIgnoreCase(emailNotificationDtls.getAction())) {
//            helper.setSubject("Your Order is Ready to Pick: FROOM");
//            t = configuration.getTemplate("froom-action-cus-update.ftlh");
//        }
//        String imageName = getQR(Long.parseLong(emailNotificationDtls.getFroomOrderID()));
////        helper.addAttachment(imageName,
////                new ClassPathResource(QR_CODE_IMAGE_PATH_2+imageName));
//
//        helper.setTo(emailNotificationDtls.getEmail());
////        String emailContent = getEmailContent(emailNotificationDtls);
//
//
//        sendMail(emailNotificationDtls, mimeMessage, helper, t);
//
//
//    }
//
//    private void sendMail(EmailUserInfo emailNotificationDtls, MimeMessage mimeMessage, MimeMessageHelper helper, Template t) throws IOException, TemplateException, MessagingException {
//        ObjectMapper mapper = new ObjectMapper();
//        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
//        Map<String, Object> map =mapper.convertValue(emailNotificationDtls, HashMap.class);
//        StrSubstitutor sub  = new StrSubstitutor(map);
//        String emailContent = FreeMarkerTemplateUtils.processTemplateIntoString(t,map);
//        helper.setText(emailContent, true);
//        javaMailSender.send(mimeMessage);
//    }
//
//    String getEmailContent(EmailUserInfo user) throws IOException, TemplateException {
//        StringWriter stringWriter = new StringWriter();
//        Map<String, Object> model = new HashMap<>();
//        model.put("userName", user.getUsername());
//        model.put("name", user.getName());
//        model.put("froomOrderId", user.getFroomOrderID());
//
//
//        configuration.getTemplate("").process(model, stringWriter);
//        return stringWriter.getBuffer().toString();
//    }
//
//    public String getQR(Long anyuuID) throws URISyntaxException, IOException {
//
//        StringBuffer goQRURL = new StringBuffer("https://api.qrserver.com/v1/create-qr-code/?data=");
//        String dataStr = "http://192.168.0.108:8080/froom/order/uuID?uuID="+anyuuID;
//        goQRURL.append(dataStr);
//        String sizeStr = "&size="+SIZE;
//        goQRURL.append(sizeStr);
//        System.out.println("goQRURL  "+goQRURL);
//
//        ResponseEntity<byte[]> res = restTemplate.getForEntity(goQRURL.toString(), byte[].class);
//        long currentMilliseconds = System.currentTimeMillis();
//        String imageName = "qr_code_uuid_"+anyuuID+"_"+currentMilliseconds+".jpg";
//        Files.write(Paths.get(QR_CODE_IMAGE_PATH+imageName), res.getBody());
//
//
//        System.out.println(QR_CODE_IMAGE_PATH+imageName);
//        String finalImagePath = QR_CODE_IMAGE_PATH+imageName;
//        return imageName;
//    }
//
//
//
//
//
//}