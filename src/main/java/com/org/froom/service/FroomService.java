/*
package com.org.froom.service;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.org.froom.dto.*;
import com.org.froom.entity.*;
import com.org.froom.exception.FroomException;
import freemarker.template.TemplateException;
import org.apache.tomcat.util.json.ParseException;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;
public interface FroomService {
    public List<FroomZIP> getFroomZips();

    public List<FroomZIPDTO> getFroomLocations(Integer zipID) throws FroomException, JsonProcessingException;
    public List<FroomRoles> getFroomRoles();

    public List<FroomCustomerDetails> getFroomCustomerDetails();
    public List<FroomPolicy> getFroomPolicies();
    public List<FroomOrderStatus> getFroomOrderStatusCodes();
    public List<FroomMerchantMaster> getAllMerchants();
    public List<FroomOrders> getAllFroomOrders();
    public List<FroomOrderDetails> getFroomOrderDetails();

    public FroomZIP getFroomLocationsbyUUID(Long uuID);

    public Long addNewFroomOrder(FroomOrder froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException;

    public Long addNewFroomLocation(FroomZIP froomLocation) throws JsonProcessingException;

    public FroomOrdersDTO getFroomOrdersByUUID(Long uuID) throws JsonProcessingException, ParseException, FroomException;

    Boolean updateFroomOrderStatus(FroomOrderUpdate froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException;

    FroomOrdersDTO updateFroomOrderManufacturer(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException;

    FroomOrdersDTO froomRecievedOrder(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException;

}
*/
