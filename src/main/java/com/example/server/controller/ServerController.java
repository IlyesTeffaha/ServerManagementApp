package com.example.server.controller;


import com.example.server.enumeration.Status;
import com.example.server.models.Server;
import com.example.server.models.response.Response;
import com.example.server.service.ServerServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;

import javax.validation.Valid;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Map;

import static org.springframework.http.MediaType.IMAGE_PNG_VALUE;

@RestController
@RequestMapping("/server")
@RequiredArgsConstructor
public class ServerController {
    private final ServerServiceImpl serverService;
@GetMapping("/list")
public ResponseEntity<Response> getServers(){
    return  ResponseEntity.ok(
            Response.builder()
                    .timestamp(LocalDateTime.now())
                    .data(Map.of("servers",serverService.list(30)))
                    .message("Server retrieved")
                    .status(HttpStatus.OK)
                    .statusCode(HttpStatus.OK.value())
                    .build()
    );
}


    @GetMapping("/ping/{ipAddress}")
    public ResponseEntity<Response> pingServer(@PathVariable("ipAddress") String ipAddress) throws IOException {
        Server server = serverService.ping(ipAddress);
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", server))
                        .message(server.getStatus() == Status.SERVER_UP ? "ping success" : "ping failure")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }


    @PostMapping("/save")
    public ResponseEntity<Response> saveServer(@RequestBody @Valid Server server){

        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.create(server)))
                        .message("Server created")
                        .status(HttpStatus.CREATED)
                        .statusCode(HttpStatus.CREATED.value())
                        .build()
        );

    }




    @GetMapping("/get/{id}")
    public ResponseEntity<Response> getServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.get(id)))
                        .message("server retrieved")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Response> deleteServer(@PathVariable("id") Long id)  {
        return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("deleted", serverService.delete(id)))
                        .message("server deleted")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }


    @PutMapping("/update/{id}")
    public ResponseEntity<Response> updateServer(@PathVariable("id") Long id)  {
    Server server=serverService.get(id);
    return ResponseEntity.ok(
                Response.builder()
                        .timestamp(LocalDateTime.now())
                        .data(Map.of("server", serverService.update(server)))
                        .message("server updated")
                        .status(HttpStatus.OK)
                        .statusCode(HttpStatus.OK.value())
                        .build()
        );

    }


    @GetMapping(path = "/image/{filename}",produces = IMAGE_PNG_VALUE)
    public byte[] getServerImage(@PathVariable("filename") String filename) throws IOException {
        return Files.readAllBytes(Paths.get(System.getProperty("Users.Elyes")+"Desktop/"+filename));


    }





}
