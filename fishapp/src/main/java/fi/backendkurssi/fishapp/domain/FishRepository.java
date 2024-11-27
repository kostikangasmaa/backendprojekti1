package fi.backendkurssi.fishapp.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface FishRepository extends CrudRepository<Fish, Long>{
    List<Fish> findBySpecies(String species);

}
