//package com.org.froom.dao;
//
//import com.fasterxml.jackson.core.JsonProcessingException;
//import com.org.froom.dto.*;
//import com.org.froom.entity.*;
//import com.org.froom.exception.FroomException;
//import freemarker.template.TemplateException;
//import org.apache.tomcat.util.json.ParseException;
//
//import javax.mail.MessagingException;
//import java.io.IOException;
//import java.net.URISyntaxException;
//import java.util.List;
//
//public interface FroomDAO {
//    public List<FroomZIP> getFroomZips();
//    public List<FroomRoles> getFroomRoles();
//    public List<FroomCustomerDetails> getFroomCustomerDetails();
//    public List<FroomPolicy> getFroomPolicies();
//    public List<FroomOrderStatus> getFroomOrderStatusCodes();
//    public List<FroomMerchantMaster> getAllMerchants();
//    public List<FroomOrders> getAllFroomOrders();
//    public List<FroomOrderDetails> getFroomOrderDetails();
//
//    List<FroomZIPDTO> getFroomLocations(Integer zipID) throws FroomException, JsonProcessingException;
//
//    public FroomZIP getFroomLocationsbyUUID(Long uUID);
//
//    public Long addNewFroomOrder(FroomOrder froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException;
//
//    public FroomOrdersDTO getFroomOrdersByUUID(Long uuID) throws JsonProcessingException, ParseException, FroomException;
//
//    Boolean updateFroomOrderStatus(FroomOrderUpdate froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException;
//
//    Long addNewFroomLocation(FroomZIP froomLocation) throws JsonProcessingException;
//
//    FroomOrdersDTO updateFroomOrderManufacturer(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException;
//    FroomOrdersDTO froomRecievedOrder(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException;
//
//}
