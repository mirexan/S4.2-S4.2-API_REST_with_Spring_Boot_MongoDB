package cat.itacademy.s04.t02.n03.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface FruitRepository extends JpaRepository<Fruit, Long> {
	boolean existsByProviderId(Long providerId);
}
