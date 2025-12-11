package cat.itacademy.s04.t02.n03.services;

import java.util.List;

public interface ProviderService {
	ProviderDTO addProvider(ProviderDTO provider);
	List<ProviderDTO> getAllProviders();
	ProviderDTO updateProviderById(long id, ProviderDTO newProviderDTO);
	void deleteProviderById(long id);
}
