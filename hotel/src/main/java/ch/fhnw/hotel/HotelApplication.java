package ch.fhnw.hotel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.RestController;

import ch.fhnw.hotel.business.service.RoomService;
import ch.fhnw.hotel.data.domain.Room;
import io.swagger.v3.oas.annotations.Hidden;
import jakarta.annotation.PostConstruct;

@SpringBootApplication
@RestController
@Hidden // Hide this controller from the Swagger UI
public class HotelApplication {

	@Autowired
	private RoomService roomService;

	public static void main(String[] args) {
		SpringApplication.run(HotelApplication.class, args);
	}
	

	// Use this method to initialize placeholder data without using Postman
	// If you are persisting data in a file (see application.properties), initializing data that already exists will cause an error during starting the application
	// To resolve the error, delete the file and restart the application
	@PostConstruct
	private void initPlaceholderData() throws Exception {
		Room room = new Room();
		room.setPrice(100);
		room.setRoomAvailability(true);
		roomService.addRoom(room);
	
		room = new Room();
		room.setPrice(150);
		room.setRoomAvailability(false);
		roomService.addRoom(room);
		
	}

}
