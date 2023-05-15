//package com.org.froom.service.impl;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.org.froom.dao.FroomDAO;
//import com.org.froom.dto.*;
//import com.org.froom.entity.*;
//import com.org.froom.exception.FroomException;
//import com.org.froom.service.FroomService;
//import freemarker.template.TemplateException;
//import org.apache.tomcat.util.json.ParseException;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//
//import javax.mail.MessagingException;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.List;
//@Service
//public class FroomServiceImpl implements FroomService {
//
//    @Autowired
//    private FroomDAO froomDAO;
//
//    @Override
//    public List<FroomZIP> getFroomZips() {
//        return froomDAO.getFroomZips();
//    }
//
//    @Override
//    public List<FroomZIPDTO> getFroomLocations(Integer zipID) throws FroomException, JsonProcessingException {
//        return froomDAO.getFroomLocations(zipID);
//    }
//
//    @Override
//    public FroomZIP getFroomLocationsbyUUID(Long uuID) {
//        return froomDAO.getFroomLocationsbyUUID(uuID);
//    }
//
//    @Override
//    public List<FroomRoles> getFroomRoles() {
//        return froomDAO.getFroomRoles();
//    }
//
//    @Override
//    public List<FroomCustomerDetails> getFroomCustomerDetails() {
//        return froomDAO.getFroomCustomerDetails();
//    }
//
//    @Override
//    public List<FroomPolicy> getFroomPolicies() {
//        return froomDAO.getFroomPolicies();
//    }
//
//    @Override
//    public List<FroomOrderStatus> getFroomOrderStatusCodes() {
//        return froomDAO.getFroomOrderStatusCodes();
//    }
//
//    @Override
//    public List<FroomMerchantMaster> getAllMerchants() {
//        return froomDAO.getAllMerchants();
//    }
//
//    @Override
//    public List<FroomOrders> getAllFroomOrders() {
//        return froomDAO.getAllFroomOrders();
//    }
//
//    @Override
//    public List<FroomOrderDetails> getFroomOrderDetails() {
//        return froomDAO.getFroomOrderDetails();
//    }
//
//    public Long addNewFroomOrder(FroomOrder froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException {
//       return froomDAO.addNewFroomOrder(froomOrder);
//    }
//
//    @Override
//    public Long addNewFroomLocation(FroomZIP froomLocation) throws JsonProcessingException {
//        return froomDAO.addNewFroomLocation(froomLocation);
//    }
//
//    @Override
//    public FroomOrdersDTO getFroomOrdersByUUID(Long uuID) throws JsonProcessingException, ParseException, FroomException {
//        return froomDAO.getFroomOrdersByUUID(uuID);
//    }
//
//    @Override
//    public Boolean updateFroomOrderStatus(FroomOrderUpdate froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
//        return froomDAO.updateFroomOrderStatus(froomOrder);
//    }
//
//    @Override
//    public FroomOrdersDTO updateFroomOrderManufacturer(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
//
//        return froomDAO.updateFroomOrderManufacturer(froomOrder);
//    }
//
//    @Override
//    public FroomOrdersDTO froomRecievedOrder(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
//        return froomDAO.froomRecievedOrder(froomOrder);
//    }
//}
