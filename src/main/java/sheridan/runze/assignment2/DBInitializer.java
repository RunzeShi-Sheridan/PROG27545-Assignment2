package sheridan.runze.assignment2;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Component;

import java.io.InputStream;
import java.util.List;

@Component
public class DBInitializer implements CommandLineRunner {

    private final CityRepository cityRepository;

    public DBInitializer(CityRepository cityRepository) {
        this.cityRepository = cityRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        cityRepository.deleteAll();

        ClassPathResource resource = new ClassPathResource("cities.json");

        try (InputStream is = resource.getInputStream()) {

            ObjectMapper mapper = new ObjectMapper();

            CitiesWrapper wrapper = mapper.readValue(is, CitiesWrapper.class);

            if (wrapper != null && wrapper.getCities() != null) {
                cityRepository.saveAll(wrapper.getCities());
            }
        }
    }

    static class CitiesWrapper {

        private List<City> cities;

        public List<City> getCities() {
            return cities;
        }

        public void setCities(List<City> cities) {
            this.cities = cities;
        }
    }
}
