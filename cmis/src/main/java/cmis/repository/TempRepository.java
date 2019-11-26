package cmis.repository;

import cmis.entity.TemperatureHumidityInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TempRepository extends JpaRepository<TemperatureHumidityInfo, Integer> {
}
