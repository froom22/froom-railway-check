/*
package com.org.froom.api;

import com.google.gson.*;
import com.google.json.JsonSanitizer;
import com.org.froom.dto.FroomOrder;
import com.org.froom.dto.FroomZIPDTO;
import com.org.froom.entity.FroomOrderStatus;
import com.org.froom.entity.FroomPolicy;
import com.org.froom.entity.FroomRoles;
import com.org.froom.service.FroomService;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@RestController
@RestControllerAdvice
@RequestMapping("/froom/admin/v2")
@Tag(name = "Froom Admin Operations", description = "Froom Admin Management API" )
public class FroomAdminController {
    @Autowired
    FroomService froomService;

    @GetMapping("/roles")
    @Operation(summary = " Fetch all the available Roles ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the Froom Roles",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomRoles.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Fetching failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> getRoles() {
        List<FroomRoles> roles = null;
        ResponseEntity<String> responseEntity = null;
        try {
            roles = froomService.getFroomRoles();
            responseEntity = new ResponseEntity<>(toJSON(roles), HttpStatus.OK);
        } catch (Exception ex) {
            responseEntity = new ResponseEntity<>(toJSON(ex), HttpStatus.INTERNAL_SERVER_ERROR);
        }
        return responseEntity;
    }

    @GetMapping("/orderstatusCodes")
    @Operation(summary = " Fetch all the available Status Codes ")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Get the Froom Status Codes",
                    content = {@Content(mediaType = "application/json",
                            schema = @Schema(implementation = FroomRoles.class))}),
            @ApiResponse(responseCode = "400", description = "Invalid Payload",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "Currently this URI Not available",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Fetching failed. Reach to Customer Support Froom",
                    content = @Content)})
    public ResponseEntity<String> getOrderStatusCodes() {
        List<FroomOrderStatus> roles = null;
        ResponseEntity<String> responseEntity = null;
        try {
            roles = froomService.getFroomOrderStatusCodes();
            responseEntity = new ResponseEntity<>(toJSON(roles), HttpStatus.OK);
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
