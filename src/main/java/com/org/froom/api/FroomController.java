/*
package com.org.froom.api;

import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.froom.dto.*;
import com.org.froom.entity.*;
import com.org.froom.exception.FroomException;
import com.org.froom.service.FroomService;
import com.org.froom.service.impl.EmailService;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.Hidden;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.ws.rs.PathParam;

@RestController
@RequestMapping("/froom")
//@OpenAPIDefinition(
//        info = @Info( title = "DEPRECATED: Froom API Controller V1 Version",
//                description = "Contains all the Froom API CRUD Operations",
//        contact = @Contact(email = "buywithfroom@gmail.com")))
@Deprecated
@Hidden
public class FroomController {

    @Autowired
    FroomService froomService;

    @Autowired
    EmailService emailService;

    @GetMapping("/healthcheck")
    public String healthCheck() {
        return "I am Running";
    }

    @GetMapping("/zipcodes")
    public List<FroomZIP> getZips() {
        return froomService.getFroomZips();
    }

    @GetMapping("/zipID")
    public List<FroomZIPDTO> getFroomLocations(@PathParam("zipID") Integer zipID) throws FroomException, JsonProcessingException {
        System.out.println("zipID -- "+zipID);
        return froomService.getFroomLocations(zipID);
    }

    @GetMapping("/zip/uuID")
    public FroomZIP getFroomLocationsByID(@PathParam("uuID") Long uuID) {
        System.out.println("uuID -- "+uuID);
        return froomService.getFroomLocationsbyUUID(uuID);
    }

    @GetMapping("/roles")
    public List<FroomRoles> getRoles() {
        return froomService.getFroomRoles();
    }

    @GetMapping("/policies")
    public List<FroomPolicy> getPolicies() {
        return froomService.getFroomPolicies();
    }

    @GetMapping("/orderstatusCodes")
    public List<FroomOrderStatus> getOrderStatusCodes() {
        return froomService.getFroomOrderStatusCodes();
    }

    @GetMapping("/orders")
    public List<FroomOrders> getFroomOrders() {
        return froomService.getAllFroomOrders();
    }

    @GetMapping("/orders/items")
    public List<FroomOrderDetails> getFroomOrderItemWiseDetails() {
        return froomService.getFroomOrderDetails();
    }

    @GetMapping("/merchants")
    public List<FroomMerchantMaster> getAllFroomMerchants() {
        return froomService.getAllMerchants();
    }

    @GetMapping("/customers")
    public List<FroomCustomerDetails> getCustomers() {
        return froomService.getFroomCustomerDetails();
    }

    @PostMapping("/froom/order")
    public Long addNewFroomOrder(@RequestBody FroomOrder froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException {
        Long result = froomService.addNewFroomOrder(froomOrder);
        return result;
    }

    @PostMapping("/location")
    public Long addNewFroomLocation(@RequestBody FroomZIP froomZIP) throws JsonProcessingException {
        Long result = froomService.addNewFroomLocation(froomZIP);
        return result;
    }

    @GetMapping("/order/uuID")
    public FroomOrdersDTO getFroomOrdersByUUID(@PathParam("uuID") Long uuID) throws JsonProcessingException, ParseException, FroomException {
        System.out.println("uuID -- "+uuID);
        return froomService.getFroomOrdersByUUID(uuID);
    }

    @PostMapping("/merchant/order")
    public Boolean updateFroomOrderStatus(@RequestBody FroomOrderUpdate froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
        Boolean result = froomService.updateFroomOrderStatus(froomOrder);
        return result;
    }
    @RequestMapping(method = RequestMethod.POST,path = "/register")
    @ResponseBody
    public String sentNotification(@RequestBody EmailUserInfo emailNotificationDtls) throws Exception {
        emailService.sendEmail(emailNotificationDtls);
        return "Email Sent..!";
    }

    @GetMapping(path = "/getQR")
    public String getQR(@PathParam("uuID") Long uuID) throws URISyntaxException, IOException {
        String qr = emailService.getQR(uuID);
        return qr;
    }


    @GetMapping(path ="/merchant/{id}")
    public FroomOrdersDTO getFroomOrderDetailsForManufacturer(@PathParam("id") Long uuID) throws JsonProcessingException, ParseException, FroomException {
        System.out.println("id -- "+uuID);
        return froomService.getFroomOrdersByUUID(uuID);
    }

}
*/
