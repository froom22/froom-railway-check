package com.org.froom.api;

import com.google.gson.*;
import com.google.json.JsonSanitizer;
import com.org.froom.dto.HeartBeatDTO;
import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.info.Contact;
import io.swagger.v3.oas.annotations.info.Info;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.lang.reflect.Type;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@RestController
@RestControllerAdvice
@RequestMapping("/froom/v2")
@OpenAPIDefinition(

        info = @Info( title = "Froom API Controller",
                version = "2.0",
                description = "Contains all the Froom API CRUD Operations",

                contact = @Contact(email = "buywithfroom@gmail.com")))
@Tag(name = "Froom Health Check", description = "Health Check API" )
public class FroomHealthCheckController {

    @GetMapping("/heartbeat")
    @Operation(summary = "API Health Check")
    public ResponseEntity<String> healthCheck() {
        HeartBeatDTO response = new HeartBeatDTO();
        response.setMessage("Instance is up and running!");
        response.setStatus("200");
        response.setComments("No Issues in railway app");
        ResponseEntity<String> responseEntity = new ResponseEntity<>(toJSON(response), HttpStatus.OK);
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
