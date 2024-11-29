package fi.backendkurssi.fishapp.domain;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;


public interface FishRepository extends JpaRepository<Fish, Long>{
    List<Fish> findByUser(User user);
}
