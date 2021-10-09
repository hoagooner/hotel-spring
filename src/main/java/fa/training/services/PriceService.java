package fa.training.services;

import fa.training.entities.PriceEntity;

public interface PriceService {

	public void save(PriceEntity price);
	
	public double getLatestPriceOfRoomType(int roomTypeId);
	
	public boolean checkPriceAvailable(int roomTypeId);

}
