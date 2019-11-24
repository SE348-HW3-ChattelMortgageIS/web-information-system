package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.TemperatureHumidityInfo;
import cmis.repository.TempRepository;
import cmis.service.TemperatureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class LocalDBTemperatureServiceImpl implements TemperatureService {

    @Autowired
    private TempRepository tempRepository;

    @Override
    public GeneralMessage getTemperature() {
        List<TemperatureHumidityInfo> infos = new ArrayList<>();
        infos = tempRepository.findAll();
        infos.sort((TemperatureHumidityInfo a, TemperatureHumidityInfo b) -> a.getTime().compareTo(b.getTime()) );
        return new GeneralMessage(200, "OK", true, infos);
    }
}
