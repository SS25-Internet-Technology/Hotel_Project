package ch.fhnw.hotel;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import ch.fhnw.hotel.business.service.ExtraServiceService;
import ch.fhnw.hotel.data.domain.ExtraService;
import ch.fhnw.hotel.data.enumtype.ExtraServiceType;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class HotelApplication {

	@Autowired
    private ExtraServiceService extraServiceService;

    HotelApplication(ExtraServiceService extraServiceService) {
        this.extraServiceService = extraServiceService;
    }

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	

	// Use this method to initialize placeholder data without using Postman
	// If you are persisting data in a file (see application.properties), initializing data that already exists will cause an error during starting the application
	// To resolve the error, delete the file and restart the application
	@PostConstruct
	private void initPlaceholderData() throws Exception {
		ExtraService extraService = new ExtraService();
		extraService.setType(ExtraServiceType.PAYTV);
		extraService.setPrice(BigDecimal.valueOf(20));
		extraServiceService.addExtraService(extraService);

		extraService = new ExtraService();
		extraService.setType(ExtraServiceType.MINIBAR);
		extraService.setPrice(BigDecimal.valueOf(40));
		extraServiceService.addExtraService(extraService);

	}

}
