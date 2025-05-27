package ch.fhnw.hotel.controller;

import ch.fhnw.hotel.business.service.ExtraServiceService;
import ch.fhnw.hotel.data.domain.ExtraService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/hotel")
public class ExtraServiceController {

    @Autowired
    private ExtraServiceService extraServiceService;

    @GetMapping(path="/extraservices/{id}", produces = "application/json")
    public ResponseEntity getExtraService(@PathVariable Long id) {
        try {
            ExtraService extraService = extraServiceService.findExtraServiceById(id);
            return ResponseEntity.ok(extraService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No extra service found with the given id");
        }
    }

    @GetMapping(path="/extraservices", produces = "application/json")
    public List<ExtraService> getExtraServiceList() {
        return extraServiceService.getAllExtraServices();
    }

    @PostMapping(path="/extraservices", consumes="application/json", produces = "application/json")
    public ResponseEntity addExtraService(@RequestBody ExtraService extraService) {
        try {
            extraService = extraServiceService.addExtraService(extraService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Extra service already exists or invalid data provided");
        }
        return ResponseEntity.ok(extraService);
    }

    @PutMapping(path="/extraservices/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity updateExtraService(@PathVariable Long id, @RequestBody ExtraService extraService) {
        try {
            extraService = extraServiceService.updateExtraService(id, extraService);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("No extra service found with the given id");
        }
        return ResponseEntity.ok(extraService);
    }

    @DeleteMapping(path="/extraservices/{id}")
    public ResponseEntity<String> deleteExtraService(@PathVariable Long id) {
        try {
            extraServiceService.deleteExtraService(id);
            return ResponseEntity.ok("Extra service with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Extra service not found");
        }
    }
}