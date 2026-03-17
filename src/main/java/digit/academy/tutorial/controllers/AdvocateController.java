package digit.academy.tutorial.controllers;

import digit.academy.tutorial.models.AdvocateRequest;
import digit.academy.tutorial.models.AdvocateResponse;
import digit.academy.tutorial.models.AdvocateSearchRequest;
import digit.academy.tutorial.services.AdvocateService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/advocate/v1")
@Slf4j
public class AdvocateController {

    @Autowired
    private AdvocateService advocateService;

    @PostMapping("/_create")
    public ResponseEntity<?> create(
            @RequestBody AdvocateRequest request) {
        try {
            AdvocateResponse response = advocateService
                    .create(request);
            return new ResponseEntity<>(response,
                    HttpStatus.CREATED);
        } catch (Exception e) {
            log.error("Error creating advocate: "
                    + e.getMessage(), e);
            return new ResponseEntity<>(
                    "Error: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/_update")
    public ResponseEntity<?> update(
            @RequestBody AdvocateRequest request) {
        try {
            AdvocateResponse response = advocateService
                    .update(request);
            return new ResponseEntity<>(response,
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error updating advocate: "
                    + e.getMessage(), e);
            return new ResponseEntity<>(
                    "Error: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/_search")
    public ResponseEntity<?> search(
            @RequestBody AdvocateSearchRequest request) {
        try {
            AdvocateResponse response = advocateService
                    .search(request);
            return new ResponseEntity<>(response,
                    HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error searching advocate: "
                    + e.getMessage(), e);
            return new ResponseEntity<>(
                    "Error: " + e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}