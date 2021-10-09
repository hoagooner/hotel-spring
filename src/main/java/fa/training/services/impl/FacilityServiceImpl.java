package fa.training.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import fa.training.converter.FacilityConverter;
import fa.training.dto.FacilityDTO;
import fa.training.entities.FacilityEntity;
import fa.training.exception.ConflictedSqlException;
import fa.training.repositories.FacilityRepository;
import fa.training.services.FacilityService;
import fa.training.services.RoomTypeService;

@Service
public class FacilityServiceImpl implements FacilityService {

	@Autowired
	private FacilityRepository facilityRepository;

	@Autowired
	private RoomTypeService roomTypeService;

	@Autowired
	private FacilityConverter facilityConverter;

	public Page<FacilityEntity> getAll(int pageNumber, int pageSize, String sortBy, String sortDirection) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection.toUpperCase()),
				sortBy);
		Page<FacilityEntity> page = facilityRepository.findAllByDeleteFlagFalse(pageable);
		return page;
	}

	public Page<FacilityEntity> findByName(String name, int pageNumber, int pageSize, String sortBy,
			String sortDirection) {
		pageNumber = pageNumber != 0 ? pageNumber - 1 : pageNumber;
		Pageable pageable = PageRequest.of(pageNumber, pageSize, Sort.Direction.fromString(sortDirection.toUpperCase()),
				sortBy);
		Page<FacilityEntity> page = facilityRepository.findByNameContainingAndDeleteFlagFalse(name, pageable);

		return page;
	}

	public FacilityDTO save(FacilityDTO facilityDTO) {
		FacilityEntity facilityEntity = facilityConverter.toEntity(facilityDTO);
		facilityRepository.save(facilityEntity);
		facilityDTO.setId(facilityEntity.getId());
		return facilityDTO;
	}

	public Optional<FacilityEntity> getById(int id) {
		return Optional.ofNullable(facilityRepository.findByIdAndDeleteFlagFalse(id));
	}

	public void delete(int id) {
		if (facilityRepository.existsById(id)) {
			Object[] obj = roomTypeService.findRoomTypeHasFacility(id);
			if (obj.length > 0) {
				List<String> roomTypes = new ArrayList<String>();
				for (Object object : obj) {
					Object[] roomType = (Object[]) object;
					roomTypes.add((String) roomType[1]);
				}
				throw new ConflictedSqlException(roomTypes.toString() + ((roomTypes.size() > 1) ? " are" : " is")
						+ " using this facility, can't delete");
			}
			FacilityEntity facility = facilityRepository.findById(id).get();
			facility.setDeleteFlag(true);
			facilityRepository.save(facility);
		}
	}

	@Override
	public FacilityEntity checkNameExists(String name) {
		return facilityRepository.findByNameAndDeleteFlagFalse(name);
	}

	@Override
	public List<FacilityEntity> getAll() {
		return facilityRepository.findAllByDeleteFlagFalse();
	}

}
