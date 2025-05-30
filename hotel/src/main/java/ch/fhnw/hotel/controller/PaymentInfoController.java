package ch.fhnw.hotel.controller;

import ch.fhnw.hotel.business.service.PaymentInfoService;
import ch.fhnw.hotel.data.domain.PaymentInfo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/hotel/paymentinfo")
public class PaymentInfoController {

    @Autowired
    private PaymentInfoService paymentInfoService;

    @GetMapping(path="/{id}", produces = "application/json")
    public ResponseEntity<?> getPaymentInfo(@PathVariable Long id) {
        try{
            PaymentInfo dto = paymentInfoService.findPaymentInfoById(id);
            return ResponseEntity.ok(dto);
        }
        catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No payment info found with given id");
        }
    }

    @GetMapping(produces = "application/json")
    public List<PaymentInfo> getPaymentInfoList() {
        List<PaymentInfo> paymentInfoList = paymentInfoService.getAllPaymentInfo();

        return paymentInfoList;
    }

    @PostMapping(consumes="application/json", produces = "application/json")
    public ResponseEntity<?> addPaymentInfo(@RequestBody PaymentInfo paymentInfo) {
        try{
            return ResponseEntity.ok(paymentInfoService.savePaymentInfo(paymentInfo));

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());
        }
    }

    @PutMapping(path="/{id}", consumes="application/json", produces = "application/json")
    public ResponseEntity<?> updatePaymentInfo(@PathVariable Long id, @RequestBody PaymentInfo paymentInfo) {
        try{
            return ResponseEntity.ok(paymentInfoService.updatePaymentInfo(id, paymentInfo));
            
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());

        }        
    }

    @DeleteMapping(path="/{id}")
    public ResponseEntity<String> deletePaymentInfo(@PathVariable Long id) {
        try{
            paymentInfoService.deletePaymentInfo(id);
            return ResponseEntity.ok("PaymentInfo with id " + id + " deleted");
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("PaymentInfo not found");
        }
    }
}
