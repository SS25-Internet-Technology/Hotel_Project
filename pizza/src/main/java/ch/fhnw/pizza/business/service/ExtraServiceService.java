package ch.fhnw.pizza.business.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import ch.fhnw.pizza.data.domain.ExtraService;
import ch.fhnw.pizza.data.repository.ExtraServiceRepository;

@Service
public class ExtraServiceService {

    @Autowired
    private ExtraServiceRepository extraServiceRepository;

    public ExtraService findExtraServiceById(Long id) {
        try {
            return extraServiceRepository.findById(id).orElseThrow(() -> 
                new RuntimeException("ExtraService with id " + id + " not found"));
        } catch (Exception e) {
            throw new RuntimeException("Error retrieving ExtraService with id " + id, e);
        }
    }

    public List<ExtraService> getAllExtraServices() {
        return extraServiceRepository.findAll();
    }

    public ExtraService addExtraService(ExtraService extraService) throws Exception {
        if (extraService.getPrice() == null || extraService.getPrice().doubleValue() <= 0) {
            throw new Exception("Invalid price. Price must be greater than 0.");
        }
        return extraServiceRepository.save(extraService);
    }

    public ExtraService updateExtraService(Long id, ExtraService extraService) throws Exception {
        ExtraService extraServiceToUpdate = extraServiceRepository.findById(id).orElseThrow(() -> 
            new Exception("ExtraService with id " + id + " does not exist"));

        if (extraService.getPrice() != null && extraService.getPrice().doubleValue() > 0) {
            extraServiceToUpdate.setPrice(extraService.getPrice());
        }
        if (extraService.getType() != null) {
            extraServiceToUpdate.setType(extraService.getType());
        }
        return extraServiceRepository.save(extraServiceToUpdate);
    }

    public void deleteExtraService(Long id) throws Exception {
        if (extraServiceRepository.existsById(id)) {
            extraServiceRepository.deleteById(id);
        } else {
            throw new Exception("ExtraService with id " + id + " does not exist");
        }
    }
}