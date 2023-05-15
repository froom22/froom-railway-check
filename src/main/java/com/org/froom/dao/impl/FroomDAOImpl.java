/*
package com.org.froom.dao.impl;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.org.froom.dao.FroomDAO;
import com.org.froom.dto.*;
import com.org.froom.entity.*;
import com.org.froom.exception.FroomException;
import com.org.froom.repository.*;
import com.org.froom.service.impl.EmailService;
import freemarker.template.TemplateException;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.mail.MessagingException;
import java.io.IOException;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class FroomDAOImpl implements FroomDAO {

    @Autowired
    FroomZipRepository froomZipRepository;

    @Autowired
    FroomRoleRepository froomRoleRepository;

    @Autowired
    FroomPolicyRepository froomPolicyRepository;

    @Autowired
    FroomCustomerRepository froomCustomerRepository;

    @Autowired
    FroomOrdersRepository froomOrdersRepository;

    @Autowired
    FroomOrderDetailsRepository froomOrderDetailsRepository;

    @Autowired
    FroomMerchantRepository froomMerchantRepository;

    @Autowired
    FroomOrderStatusRepository froomOrderStatusRepository;

    @Autowired
    EmailService emailService;


    @Override
    public List<FroomZIP> getFroomZips() {
        return froomZipRepository.findAll();
    }

    @Override
    public List<FroomRoles> getFroomRoles() {
        return froomRoleRepository.findAll();
    }

    @Override
    public List<FroomCustomerDetails> getFroomCustomerDetails() {
        return froomCustomerRepository.findAll();
    }

    @Override
    public List<FroomPolicy> getFroomPolicies() {
        return froomPolicyRepository.findAll();
    }

    @Override
    public List<FroomOrderStatus> getFroomOrderStatusCodes() {
        return froomOrderStatusRepository.findAll();
    }

    @Override
    public List<FroomMerchantMaster> getAllMerchants() {
        return froomMerchantRepository.findAll();
    }

    @Override
    public List<FroomOrders> getAllFroomOrders() {
        return froomOrdersRepository.findAll();
    }

    @Override
    public List<FroomOrderDetails> getFroomOrderDetails() {
        return froomOrderDetailsRepository.findAll();
    }

    @Override
    public List<FroomZIPDTO> getFroomLocations(Integer zipID) throws FroomException, JsonProcessingException {
        List<FroomZIP> froomlocations = null;
        List<FroomZIPDTO> response = new ArrayList<>();
        FroomZIPDTO zipLocationDetails = new FroomZIPDTO();
        if (zipID != null) {
            froomlocations = froomZipRepository.findFroomZIPByZipCode(zipID);
            if (!CollectionUtils.isEmpty(froomlocations)) {
                for(FroomZIP zip: froomlocations) {
                    BeanUtils.copyProperties(zip, zipLocationDetails);
                    ObjectMapper mapper = new ObjectMapper();
                    mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);

                    FroomZIPStoreDetailsDTO storeDetailsDTO
                            = mapper.readValue(zip.getStores(), FroomZIPStoreDetailsDTO.class);
                    zipLocationDetails.setStores(new FroomZIPStoreDetailsDTO());
                    BeanUtils.copyProperties(storeDetailsDTO, zipLocationDetails.getStores());
                    response.add(zipLocationDetails);
                }
                return response;
            } else {
                throw new FroomException("No Froom Locations Found in this Zip Code.");
            }
        }
        return response;
    }


    @Override
    public FroomZIP getFroomLocationsbyUUID(Long uUID) {
        FroomZIP froomlocations = null;
        if (uUID != null) {
            froomlocations = froomZipRepository.findFroomZIPByuuid(uUID);
            return froomlocations;
        }
        return null;
    }

    public Long addNewFroomOrder(FroomOrder froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException {
        FroomZIP selectedFroomLocation = null;
        Long froomOrderID = null;
        FroomCustomerDetails customerDetails = new FroomCustomerDetails();
        FroomMerchantMaster merchantDetails = new FroomMerchantMaster();
        FroomOrderDetails froomOrderDetails = new FroomOrderDetails();
        FroomOrders froomOrders = new FroomOrders();
        LocalDateTime dateNow = LocalDateTime.now();
        // Check Role

        // Check ZIP from UUID
        if (froomOrder.getZipUUID() != null) {
            selectedFroomLocation = getFroomLocationsbyUUID(froomOrder.getZipUUID());
        }

        // Check Customer - First time then add details.
        Long customerID = null;
        if (froomOrder.getCustomerDetails() != null && froomOrder.getCustomerDetails().getUuid() == null) {
            customerID = manageCustomer(froomOrder, customerDetails, dateNow);
        } else if (froomOrder.getOrderDetails().get(0).getCustomerId() != null) {
            customerID = froomOrder.getOrderDetails().get(0).getCustomerId();
        }
        // Check Merchant - First time then add details.
        Long merchantID = null;
        if (froomOrder.getMerchantDetails() != null && froomOrder.getMerchantDetails().getUuid() == null) {
            merchantID = manageMerchantDetails(froomOrder, merchantDetails, dateNow);
        } else if (froomOrder.getOrderDetails().get(0).getMerchantId() != null) {
            merchantID = froomOrder.getOrderDetails().get(0).getMerchantId();
        }
        // Add Froom order and items and respective status.

        if (!CollectionUtils.isEmpty(froomOrder.getOrderDetails())) {
            froomOrderID = manageFroomOrderDetails(froomOrder, froomOrderDetails, customerID, merchantID, selectedFroomLocation);
        }

        // send notification
        EmailUserInfo emailNotification = new EmailUserInfo();
        emailNotification.setOrderDetails(froomOrder);
        emailNotification.setFroomOrderID(froomOrderID.toString());
        emailNotification.setAction("NEW");
        emailNotification.setTransactionID(froomOrder.getTransactionID());
        emailNotification.setEmail(customerDetails.getUserEmail());
        emailNotification.setUsername(customerDetails.getUserName());
        emailNotification.setName(customerDetails.getUserName());
//        emailService.sendEmail(emailNotification);
//

        return froomOrderID;
    }

    private Long manageFroomOrderDetails(FroomOrder froomOrder, FroomOrderDetails orderDetails,
                                         Long customerID, Long merchantID, FroomZIP zip) {

        FroomOrders froomOrders = new FroomOrders();
        LocalDateTime dateNow;
        dateNow = LocalDateTime.now();
        froomOrders.setCustomerId(customerID);
        froomOrders.setMerchantId(merchantID);
        froomOrders.setTransactionID(froomOrder.getTransactionID());
        froomOrders.setIsActive("Y");
        froomOrders.setCreatedBy("Froom_Admin");
        froomOrders.setCreatedDate(dateNow);
        froomOrders.setFroomOrderStatus("ORDERED");
        froomOrders.setComments(froomOrder.getComments());
        froomOrders.setFroomZipId(zip.getUuid());

        FroomOrders savedFroomOrders = froomOrdersRepository.save(froomOrders);

        dateNow = LocalDateTime.now();
        List<FroomOrderDetailsDTO> orderDetails1 = froomOrder.getOrderDetails();


        for (FroomOrderDetailsDTO order : froomOrder.getOrderDetails()) {
            orderDetails.setCustomerId(customerID);
            orderDetails.setMerchantId(merchantID);
            orderDetails.setIsActive("Y");
            orderDetails.setCreatedBy("Froom_Admin");
            orderDetails.setCreatedDate(dateNow);
            Gson gson = new Gson();
            String productDetails = gson.toJson(order.getProductDetails());
            orderDetails.setProductDetails(productDetails);
            orderDetails.setFroomOrders(savedFroomOrders);
            froomOrderDetailsRepository.save(orderDetails);
        }
        return savedFroomOrders.getUuID();
    }

    private Long manageMerchantDetails(FroomOrder froomOrder, FroomMerchantMaster merchantDetails, LocalDateTime dateNow) {
        if (froomOrder.getMerchantDetails() != null && froomOrder.getMerchantDetails().getUuid() == null) {
            merchantDetails.setCreatedBy("Froom_Admin");
            merchantDetails.setCreatedDate(dateNow);
            merchantDetails.setIsActive("Y");
            merchantDetails.setMerchantDetails(froomOrder.getMerchantDetails().getMerchantDetails());
            merchantDetails.setMerchantName(froomOrder.getMerchantDetails().getMerchantName());
            FroomMerchantMaster savedMerchantDtls = froomMerchantRepository.save(merchantDetails);
            return savedMerchantDtls.getUuid();

        } else {
            // update the merchant details if any changes found in mail or phone number.
            FroomMerchantMaster existingMerchantDetails = froomMerchantRepository.getReferenceById(froomOrder.getOrderDetails().get(0).getMerchantId());

            return existingMerchantDetails.getUuid();
        }
    }

    private Long manageCustomer(FroomOrder froomOrder, FroomCustomerDetails customerDetails, LocalDateTime dateNow) {
        if (froomOrder.getCustomerDetails() != null && froomOrder.getCustomerDetails().getUuid() == null) {
            // New  Customer Adding
            customerDetails.setCreatedBy("Froom_Admin");
            customerDetails.setCreatedDate(dateNow);
            customerDetails.setIsActive("Y");
            customerDetails.setUserName(froomOrder.getCustomerDetails().getUserName());
            customerDetails.setUserAddress(froomOrder.getCustomerDetails().getUserAddress());
            customerDetails.setUserConsent(froomOrder.getCustomerDetails().getUserConsent());
            customerDetails.setUserEmail(froomOrder.getCustomerDetails().getUserEmail());
            customerDetails.setUserPhoneNumber(froomOrder.getCustomerDetails().getUserPhoneNumber());
            customerDetails.setUserRemarks(froomOrder.getCustomerDetails().getUserRemarks());
            FroomCustomerDetails savedCustomerDtls = froomCustomerRepository.save(customerDetails);
            return savedCustomerDtls.getUuid();

        } else {
            // update the customer details if any changes found in mail or phone number.
            FroomCustomerDetails existingCustomerDtls = froomCustomerRepository.getReferenceById(froomOrder.getOrderDetails().get(0).getCustomerId());

            return existingCustomerDtls.getUuid();
        }
    }

    public FroomOrdersDTO getFroomOrdersByUUID(Long uuID) throws JsonProcessingException, ParseException, FroomException {

        Optional<FroomOrders> froomOrders = null;
        FroomOrdersDTO froomOrdersDTO = new FroomOrdersDTO();
        FroomOrderDetailsDTO froomOrderDetailsDTO = new FroomOrderDetailsDTO();
        try {
            if (uuID != null) {
                froomOrders = froomOrdersRepository.findById(uuID);
                if (froomOrders != null && froomOrders.isPresent()) {
                    List<FroomOrderDetails> froomOrderDetails = froomOrderDetailsRepository.fetchDetailsByFroomId(froomOrders.get().getUuID());
                    if ( !CollectionUtils.isEmpty(froomOrderDetails)) {

                        BeanUtils.copyProperties(froomOrders.get(), froomOrdersDTO);

                        BeanUtils.copyProperties(froomOrderDetails.get(0), froomOrderDetailsDTO);
                        ObjectMapper mapper = new ObjectMapper();
                        mapper.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
                        List<ProductDetails> productDetails
                                = mapper.readValue(froomOrders.get().getFroomOrderDetails().getProductDetails(), new TypeReference<ArrayList<ProductDetails>>() {
                        });

                        if (!CollectionUtils.isEmpty(productDetails)) {
                            froomOrderDetailsDTO.setProductDetails(productDetails);
                        }

                        froomOrdersDTO.setFroomOrderDetailsDTO(froomOrderDetailsDTO);

                        return froomOrdersDTO;
                    }
                } else {
                    throw new FroomException("No Such Froom Order ID Found");
                }
            }
        } catch (JsonProcessingException ex) {
            throw new FroomException("Exception Occurred. Reach out to Froom Support");
        }
        return froomOrdersDTO;
    }

    @Override
    public Boolean updateFroomOrderStatus(FroomOrderUpdate froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
        if (froomOrder.getUuID() == null) {
            // throw exception
        }
        LocalDateTime dateNow = LocalDateTime.now();
        if (froomOrder.getUuID() != null) {
            FroomOrdersDTO existingOrder = getFroomOrdersByUUID(froomOrder.getUuID());
            existingOrder.setFroomOrderStatus(froomOrder.getNewStatus());
            existingOrder.setComments(froomOrder.getComments());
            existingOrder.setUpdatedBy("Merchant");
            existingOrder.setUpdatedDate(dateNow);
            existingOrder.setTrackingID(froomOrder.getTrackingID());
//            existingOrder.setTrackingInfo(froomOrder.getTrackingInfo());


//           froomOrdersRepository.save(existingOrder);
            // TODO - Email sending to Froomer.
            // send notification
            notifyViaEmail(froomOrder.getUuID(), "2", null, "MERCHANT_UPDATE", "catchshar", "Sharath Linga");
        }

        return true;
    }

    private void notifyViaEmail(Long uuID, String trackingId, TrackingInfoDTO trackingInfoDTO, String action, String userName, String name) throws MessagingException, IOException, TemplateException, URISyntaxException {
        EmailUserInfo emailNotification = new EmailUserInfo();
//            emailNotification.setOrderDetails(froomOrder);
        emailNotification.setFroomOrderID(String.valueOf(uuID));
        emailNotification.setAction(action);
        emailNotification.setTransactionID(trackingId);
        emailNotification.setEmail("buywithfroom@gmail.com");
        emailNotification.setUsername(userName);
        emailNotification.setName(name);
        if(trackingInfoDTO!= null) {
            emailNotification.setTrackingID(trackingId);
            emailNotification.setTrackingDetails(trackingInfoDTO);
        }
        emailService.sendEmail(emailNotification);
    }

    @Override
    public Long addNewFroomLocation(FroomZIP froomLocation) throws JsonProcessingException {
        LocalDateTime now = LocalDateTime.now();
        froomLocation.setCreatedDate(now);
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        FroomLocation location = mapper.readValue(froomLocation.getStores(), FroomLocation.class);
        froomLocation.setZipDetails(location.getStoreName());

        FroomZIP savedLocation = froomZipRepository.save(froomLocation);
        if (savedLocation != null) {
            return 1l;
        } else {
            return 0l;
        }
    }

    @Override
    public FroomOrdersDTO updateFroomOrderManufacturer(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {

        FroomOrdersDTO response = new FroomOrdersDTO();
        response = getFroomOrdersByUUID(froomOrder.getFroomID());

        response.setTrackingID(String.valueOf(froomOrder.getTrackingInfo().getTrackingId()));
        Gson gson = new Gson();
        String trackingInfo = gson.toJson(froomOrder.getTrackingInfo());
        response.setTrackingInfo(froomOrder.getTrackingInfo());
        response.setFroomOrderStatus("Order_Shipped");

        // Save Froom Order Details.

        FroomOrders froomOrders = new FroomOrders();

        BeanUtils.copyProperties(response, froomOrders);

        froomOrders.setTrackingInfo(trackingInfo);

        LocalDateTime dateNow = LocalDateTime.now();

        froomOrders.setUpdatedBy("FROOMER_ID");
        froomOrders.setUpdatedDate(dateNow);

        FroomOrders persistedFroomOrder = froomOrdersRepository.save(froomOrders);

        if(persistedFroomOrder != null) {
            // EMAIL
            notifyViaEmail(froomOrder.getFroomID(), String.valueOf(froomOrder.getTrackingInfo().getTrackingId()), froomOrder.getTrackingInfo(), froomOrder.getRequestType(), "catchshar", "Sharath Lingas");
        }

        if(response!= null) {
            return response;
        } else if(response == null) {
            throw new FroomException("No Such Froom Order ID Found");
        }


        return response;
    }

    @Override
    public FroomOrdersDTO froomRecievedOrder(FroomUpdateDTO froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
        FroomOrdersDTO response = new FroomOrdersDTO();
        response = getFroomOrdersByUUID(froomOrder.getFroomID());

        response.setFroomOrderStatus("ORDER_RECIEVED_AT_FROOM");

        // Save Froom Order Details.

        FroomOrders froomOrders = new FroomOrders();

        BeanUtils.copyProperties(response, froomOrders);


        LocalDateTime dateNow = LocalDateTime.now();

        froomOrders.setUpdatedBy("FROOMER_ID");
        froomOrders.setUpdatedDate(dateNow);

        FroomOrders persistedFroomOrder = froomOrdersRepository.save(froomOrders);



        if(persistedFroomOrder != null) {
            // EMAIL to Manufactuerer
            notifyViaEmail(froomOrder.getFroomID(),
                    froomOrder.getTrackingInfo()!= null ? String.valueOf(froomOrder.getTrackingInfo().getTrackingId()): null,
                    froomOrder.getTrackingInfo()!=null ? froomOrder.getTrackingInfo() : null,
                    froomOrder.getRequestType(), "catchshar", "Sharath Lingas");

            // EMAIL to Customer
            FroomCustomerDetails customerInfo =
                    froomCustomerRepository.findByuuid(persistedFroomOrder.getCustomerId());
            notifyViaEmail(froomOrder.getFroomID(),
                    froomOrder.getTrackingInfo()!= null ? String.valueOf(froomOrder.getTrackingInfo().getTrackingId()): null,
                    froomOrder.getTrackingInfo()!=null ? froomOrder.getTrackingInfo() : null,
                    "CUSTOMER_NOTIFICATION",
                    customerInfo.getUserName(),
                    customerInfo.getUserEmail());

        }

        if(response!= null) {
            return response;
        } else if(response == null) {
            throw new FroomException("No Such Froom Order ID Found");
        }


        return response;
    }
}*/
