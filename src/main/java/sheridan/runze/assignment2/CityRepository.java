package sheridan.runze.assignment2;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import sheridan.runze.assignment2.City;

import java.util.List;

@RepositoryRestResource(path = "cities")
public interface CityRepository extends JpaRepository<City, Long> {

    List<City> findByNameContainingIgnoreCase(@Param("name") String name);

    List<City> findByCountryIgnoreCase(@Param("country") String country);

    List<City> findByPopulationGreaterThan(@Param("min") long min);

    List<City> findByPopulationLessThan(@Param("max") long max);

    List<City> findByPopulationBetween(@Param("min") long min, @Param("max") long max);
}
