package ch.fhnw.hotel.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.hotel.data.domain.PaymentInfo;
import ch.fhnw.hotel.data.repository.PaymentInfoRepository;

@Service
public class PaymentInfoService {

    @Autowired
    private PaymentInfoRepository paymentInfoRepository;

    public PaymentInfo findPaymentInfoById(Long id) {
        try {
            PaymentInfo paymentInfo = paymentInfoRepository.findById(id).get();
            return paymentInfo;
        } catch (Exception e) {
            throw new RuntimeException("PaymentInfo with id " + id + " not found");
        }
    }

    public List<PaymentInfo> getAllPaymentInfo() {
        return paymentInfoRepository.findAll();
    }

    public PaymentInfo savePaymentInfo(PaymentInfo paymentInfo) throws Exception {
        if (paymentInfo.getFirstName() == null || paymentInfo.getFirstName().isEmpty() ||
            paymentInfo.getLastName() == null || paymentInfo.getLastName().isEmpty() ||
            paymentInfo.getEmail() == null || paymentInfo.getEmail().isEmpty() ||
            paymentInfo.getPhoneNumber() == null || paymentInfo.getPhoneNumber().isEmpty() ||
            paymentInfo.getCreditCard() == null || paymentInfo.getCreditCard().isEmpty()) {
            throw new Exception("All fields must be filled and not empty.");
        }
        
        return paymentInfoRepository.save(paymentInfo);
    }

    public PaymentInfo updatePaymentInfo(Long id, PaymentInfo paymentInfo) throws Exception {
        PaymentInfo paymentInfoToUpdate = paymentInfoRepository.findById(id)
            .orElseThrow(() -> new RuntimeException("PaymentInfo with id " + id + " does not exist"));

        if (paymentInfo.getFirstName() != null && !paymentInfo.getFirstName().isEmpty()) {
            paymentInfoToUpdate.setFirstName(paymentInfo.getFirstName());
        }
        if (paymentInfo.getLastName() != null && !paymentInfo.getLastName().isEmpty()) {
            paymentInfoToUpdate.setLastName(paymentInfo.getLastName());
        }
        if (paymentInfo.getEmail() != null && !paymentInfo.getEmail().isEmpty()) {
            paymentInfoToUpdate.setEmail(paymentInfo.getEmail());
        }
        if (paymentInfo.getPhoneNumber() != null && !paymentInfo.getPhoneNumber().isEmpty()) {
            paymentInfoToUpdate.setPhoneNumber(paymentInfo.getPhoneNumber());
        }
        if (paymentInfo.getCreditCard() != null && !paymentInfo.getCreditCard().isEmpty()) {
            paymentInfoToUpdate.setCreditCard(paymentInfo.getCreditCard());
        }
        return paymentInfoRepository.save(paymentInfoToUpdate);
    }

    public void deletePaymentInfo(Long id) throws Exception {
        if(paymentInfoRepository.existsById(id)) {
            paymentInfoRepository.deleteById(id);
        } else
            throw new Exception("PaymentInfo with id " + id + " does not exist");
    }
        
}
