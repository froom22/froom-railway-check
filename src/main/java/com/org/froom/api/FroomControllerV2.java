/*
package com.org.froom.api;

import com.google.gson.*;
import com.google.json.JsonSanitizer;
import com.org.froom.dto.*;
import com.org.froom.entity.FroomPolicy;
import com.org.froom.entity.FroomRoles;
import com.org.froom.entity.FroomZIP;
import com.org.froom.exception.FroomException;
import com.org.froom.service.FroomService;
import com.org.froom.service.impl.EmailService;
import freemarker.template.TemplateException;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.apache.tomcat.util.json.ParseException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import javax.ws.rs.PathParam;
import java.io.IOException;
import java.lang.reflect.Type;
import java.net.URISyntaxException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/froom/v2")
@Tag(name = "Froom Operations", description = "Access Froom APIs" )
public class FroomControllerV2 {
    @Autowired
    FroomService froomService;

    @Autowired
    EmailService emailService;



    @PostMapping("/froom/order")
    @Operation(summary = "Add a new Froom order")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Added the Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomOrder.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Froom Order Adding failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> addNewFroomOrder(@RequestBody FroomOrder froomOrder) throws MessagingException, TemplateException, IOException, URISyntaxException, FroomException {
        Long result = null;
        ResponseEntity<String> responseEntity = null;
        try {
            if ("NEW".equalsIgnoreCase(froomOrder.getRequestType())) {
                result = froomService.addNewFroomOrder(froomOrder);
            } else {
                throw new FroomException("Invalid Froom Request Type for Add");
            }
            FroomOrderResponseDTO response = new FroomOrderResponseDTO();
            if (result != null) {
                response.setFroomOrderId(result);
                response.setOrderStatus("ORDERED");
                response.setMessage("Froom received your order");
            }
            responseEntity = new ResponseEntity<>(toJSON(response), HttpStatus.OK);
        } catch (FroomException ex) {
            responseEntity = new ResponseEntity<>(toJSON(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/froom/order/{froom_id}")
    @Operation(summary = "Get the full details of the Given Froom Order ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Fetches the Froom Order Details",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomOrder.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Froom Id fetch failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> getFroomOrderDetails(@PathVariable Long froom_id) throws MessagingException, TemplateException, IOException, URISyntaxException, ParseException, FroomException {
        FroomOrdersDTO response = null;
        ResponseEntity<String> responseEntity = null;
        try {
            response = froomService.getFroomOrdersByUUID(froom_id);
//        if(response != null) {
//            response.setFroomOrderId(result);
//            response.setOrderStatus("ORDERED");
//            response.setMessage("Froom received your order");
//        }
            responseEntity = new ResponseEntity<>(toJSON(response), HttpStatus.OK);
        } catch (FroomException exception) {
            responseEntity = new ResponseEntity<>(toJSON(exception), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @PutMapping("/froom/order/{froom_id}")
    @Operation(summary = "Update Froom Order for the Given Froom Id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomOrder.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Froom Order Updation failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> updateFroomOrder(@RequestBody FroomUpdateDTO froomOrder, @PathVariable Long froom_id) throws MessagingException, TemplateException, IOException, URISyntaxException, FroomException {
        FroomOrdersDTO result = null;
        ResponseEntity<String> responseEntity = null;
        try {
            if ("MANUFACTURER_SHIPPED".equalsIgnoreCase(froomOrder.getRequestType())) {
                result = froomService.updateFroomOrderManufacturer(froomOrder);
            } else if ("FROOM_RECEIVED".equalsIgnoreCase(froomOrder.getRequestType())) {
                result = froomService.froomRecievedOrder(froomOrder);
            } else {
                throw new FroomException("Invalid Froom Request Type for Add");
            }
            FroomOrderResponseDTO response = new FroomOrderResponseDTO();
//            if (result != null) {
//                response.setFroomOrderId(result);
//                response.setOrderStatus("ORDERED");
//                response.setMessage("Froom received your order");
//            }
            responseEntity = new ResponseEntity<>(toJSON(result), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(toJSON(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/froom/order/status/{froom_id}")
    @Operation(summary = "After Receiving the Froom Order at Froom Location ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Updated the Order",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomOrder.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Froom Order Updation failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> updateFroomOrderReceived(@PathVariable Long froom_id) throws MessagingException, TemplateException, IOException, URISyntaxException, FroomException {
        FroomOrdersDTO result = null;
        ResponseEntity<String> responseEntity = null;
        FroomUpdateDTO request = new FroomUpdateDTO();
            request.setRequestType("FROOM_RECEIVED");
            request.setFroomID(froom_id);
        try {
            result = froomService.froomRecievedOrder(request);
            responseEntity = new ResponseEntity<>(toJSON(result), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(toJSON(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/zipID")
    @Operation(summary = " Get the Nearest Froom locations for the Given ZIP ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the Froom Locations",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomOrder.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Fetching failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> getFroomLocations(@PathParam("zipID") Integer zipID) {
        List<FroomZIPDTO> froomLocations = null;
        ResponseEntity<String> responseEntity = null;
        try {
            froomLocations = froomService.getFroomLocations(zipID);
            responseEntity = new ResponseEntity<>(toJSON(froomLocations), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(toJSON(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/policies")
    @Operation(summary = " Fetch all the available Policies ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the Froom Policies",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomRoles.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Fetching failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> getPolicies() {
        List<FroomPolicy> froomPolicies = null;
        ResponseEntity<String> responseEntity = null;
        try {
            froomPolicies = froomService.getFroomPolicies();
            responseEntity = new ResponseEntity<>(toJSON(froomPolicies), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(toJSON(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    private String toJSON(Object inputRequest) {
        Gson builder = new GsonBuilder().registerTypeAdapter(LocalDateTime.class, new JsonSerializer<LocalDateTime>() {
            @Override
            public JsonElement serialize(LocalDateTime localDateTime, Type type, JsonSerializationContext jsonSerializationContext) {
                return new JsonPrimitive(localDateTime.format(DateTimeFormatter.ISO_DATE_TIME));
            }
        }).create();
        return JsonSanitizer.sanitize(builder.toJson(inputRequest));
    }
}
*/
