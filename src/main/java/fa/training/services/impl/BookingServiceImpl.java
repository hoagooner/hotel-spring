package fa.training.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import fa.training.converter.BookingConverter;
import fa.training.dto.BookingDTO;
import fa.training.entities.BookingEntity;
import fa.training.exception.ConflictedSqlException;
import fa.training.repositories.BookingRepository;
import fa.training.services.BookingService;
import fa.training.services.RoomService;

@Service
public class BookingServiceImpl implements BookingService{

	@Autowired
	private BookingRepository bookingRepository;
	
	@Autowired
	private BookingConverter bookingConverter;
	
	@Autowired
	private RoomService roomServiceImpl;
	
	@Override
	@Transactional(rollbackFor = { ConflictedSqlException.class })
	public BookingDTO save(BookingDTO booking) {
		BookingEntity bookingEntity = bookingConverter.toEntity(booking);
		bookingEntity.getBookingDetails().forEach(bookingDetail->{
			List<Integer> roomIds = roomServiceImpl.getBookedRoom(bookingDetail.getCheckin(), bookingDetail.getCheckout());
			roomIds.forEach(id ->{
				if(id == bookingDetail.getRoom().getId()) {
					throw new ConflictedSqlException("room "+bookingDetail.getRoom().getRoomNumber()+" has been booked");
				}
			});
			bookingDetail.setBooking(bookingEntity);
		});
		bookingRepository.save(bookingEntity);
		return booking;
	}

}
