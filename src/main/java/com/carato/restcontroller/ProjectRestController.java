package com.carato.restcontroller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/p")
public class ProjectRestController {

    @GetMapping("/a")
    public ResponseEntity<String> name() {
        return new ResponseEntity<String>("sankar", HttpStatus.OK);
    }
}
