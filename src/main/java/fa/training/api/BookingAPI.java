package fa.training.api;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import fa.training.dto.BookingDTO;
import fa.training.services.BookingService;
import fa.training.services.RoomService;

@RestController
public class BookingAPI {
	
	@Autowired
	private RoomService roomService;
	
	@Autowired
	private BookingService bookingService;
	
	@GetMapping("api/bookings/search")
	public Object getBookedRoom(@RequestParam(name="checkin",required=true) @DateTimeFormat(pattern="yyyy-MM-dd")  Date checkin,
			@RequestParam(name="checkout",required=true) @DateTimeFormat(pattern="yyyy-MM-dd")  Date checkout  ) {
		return roomService.getBookedRoom(checkin, checkout);
	}
	
	@PostMapping("api/bookings")
	public Object createBooking(@RequestBody BookingDTO bookingDTO) {
		return bookingService.save(bookingDTO);
	}
}