package cat.itacademy.s04.t02.n03.services;

import java.util.List;

public interface FruitService {
	FruitDTO addFruit(FruitDTO newFruitDTO);
	List<FruitDTO> getAllFruits();
	List<FruitDTO> getFruitsByProviderId(Long providerId);
	FruitDTO getFruitById(long id);
	FruitDTO updateFruitById(long id, FruitDTO newFruitDTO);
	void deleteFruitById(long id);
}
