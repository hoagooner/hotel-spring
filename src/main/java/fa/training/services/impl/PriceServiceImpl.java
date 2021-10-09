package fa.training.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import fa.training.entities.PriceEntity;
import fa.training.entities.RoomTypeEntity;
import fa.training.repositories.PriceRepository;
import fa.training.services.PriceService;

@Service
public class PriceServiceImpl implements PriceService {

	@Autowired
	private PriceRepository priceRepository;

	@Override
	public void save(PriceEntity price) {
		priceRepository.save(price);
	}

	public double getLatestPriceOfRoomType(int roomTypeId) {
		return priceRepository.getLatestPriceOfRoomType(roomTypeId);
	}

	@Override
	public boolean checkPriceAvailable(int roomTypeId) {
		if (priceRepository.findByRoomTypeId(roomTypeId).length > 0) {
			return true;
		}
		return false;
	}

}
