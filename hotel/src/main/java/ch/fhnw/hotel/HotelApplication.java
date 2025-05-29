package ch.fhnw.hotel;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;
import ch.fhnw.hotel.business.service.ExtraServiceService;
import ch.fhnw.hotel.business.service.RoomCategoryService;
import ch.fhnw.hotel.business.service.RoomService;
import ch.fhnw.hotel.data.domain.ExtraService;
import ch.fhnw.hotel.data.domain.Room;
import ch.fhnw.hotel.data.domain.RoomCategory;
import ch.fhnw.hotel.data.enumtype.ExtraServiceType;
import ch.fhnw.hotel.data.enumtype.RoomType;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class HotelApplication {

	@Autowired
    private ExtraServiceService extraServiceService;

	@Autowired
    private RoomCategoryService roomCategoryService;

	@Autowired
	private RoomService roomService;

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
		/*
		Room room = new Room();
		room.setPrice(BigDecimal.valueOf(100));
		room.setRoomAvailability(true);
		room.setRoomNumber("101");
		roomService.addRoom(room);
	
		room = new Room();
		room.setPrice(BigDecimal.valueOf(150));
		room.setRoomAvailability(false);
		room.setRoomNumber("202");
		roomService.addRoom(room);
		*/

		ExtraService extraService = new ExtraService();
		extraService.setType(ExtraServiceType.PAYTV);
		extraService.setPrice(BigDecimal.valueOf(20));
		extraServiceService.addExtraService(extraService);

		extraService = new ExtraService();
		extraService.setType(ExtraServiceType.MINIBAR);
		extraService.setPrice(BigDecimal.valueOf(40));
		extraServiceService.addExtraService(extraService);

		RoomCategory roomCategory = new RoomCategory();
		roomCategory.setRoomType(RoomType.SINGLE);
		roomCategory.setSmokeAllowed(true);
		roomCategory.setSeasonalMultiplier(BigDecimal.valueOf(1.5));
		roomCategoryService.addRoomCategory(roomCategory);

		roomCategory = new RoomCategory();
		roomCategory.setRoomType(RoomType.SINGLE);
		roomCategory.setSmokeAllowed(false);
		roomCategory.setSeasonalMultiplier(BigDecimal.valueOf(1.2));
		roomCategoryService.addRoomCategory(roomCategory);

		roomCategory = new RoomCategory();
		roomCategory.setRoomType(RoomType.DOUBLE);
		roomCategory.setSmokeAllowed(true);
		roomCategory.setSeasonalMultiplier(BigDecimal.valueOf(2.0));
		roomCategoryService.addRoomCategory(roomCategory);

		roomCategory = new RoomCategory();
		roomCategory.setRoomType(RoomType.DOUBLE);
		roomCategory.setSmokeAllowed(false);
		roomCategory.setSeasonalMultiplier(BigDecimal.valueOf(1.8));
		roomCategoryService.addRoomCategory(roomCategory);
		
	}

}
