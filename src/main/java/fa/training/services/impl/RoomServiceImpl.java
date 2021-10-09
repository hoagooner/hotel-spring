package fa.training.services.impl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fa.training.converter.RoomConverter;
import fa.training.dto.RoomDTO;
import fa.training.entities.RoomEntity;
import fa.training.repositories.RoomRepository;
import fa.training.services.RoomService;
import fa.training.utils.DateUtils;

@Service
public class RoomServiceImpl implements RoomService {

	@Autowired
	private RoomRepository roomRepository;

	@Autowired
	private RoomConverter roomConverter;

	@Override
	public Page<RoomEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection.toUpperCase()),
				sortBy);
		Page<RoomEntity> page = roomRepository.findAllByDeleteFlagFalse(pageable);
		return page;
	}

	@Override
	public List<RoomEntity> getAll() {
		return roomRepository.findAllByDeleteFlagFalse();
	}

	@Override
	public Page<RoomEntity> findByName(String query, int pageNumber, int pageSize, String sortBy, String sortDirection) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		try {
			int number = Integer.valueOf(query);
			Pageable pageable = PageRequest.of(number, pageSize, Sort.Direction.fromString(sortDirection.toUpperCase()), sortBy);
			Page<RoomEntity> page = roomRepository
					.findByDeleteFlagFalseAndRoomNumber(number, pageable);
			return page;
		} catch (Exception e) {
			return Page.empty();
		}
	}

	@Override
	public RoomDTO save(RoomDTO roomDTO) {
		RoomEntity room = roomConverter.toEntity(roomDTO);
		roomRepository.save(room);
		roomDTO.setId(room.getId());
		return roomDTO;
	}

	@Override
	public Optional<RoomDTO> getById(int id) {
		RoomEntity room = roomRepository.findByIdAndDeleteFlagFalse(id);
		RoomDTO roomDTO = roomConverter.toDTO(room);
		return Optional.ofNullable(roomDTO);
	}

	@Override
	public void delete(int id) {
		if (roomRepository.existsById(id)) {
			RoomEntity room = roomRepository.findById(id).get();
			room.setDeleteFlag(true);
			roomRepository.save(room);
		}

	}

	@Override
	public RoomEntity checkNameExists(int name) {
		return roomRepository.findByRoomNumberAndDeleteFlagFalse(name);
	}

	@Override
	public List<Integer> getBookedRoom(Date checkin, Date checkout) {
		checkin = DateUtils.addTime(checkin, 14);
		checkout = DateUtils.addTime(checkout, 12);
		return roomRepository.getBookedRoom(checkin, checkout);
	}

}
