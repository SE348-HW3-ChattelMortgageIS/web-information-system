package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.service.TemperatureService;
//import cmis.service.serviceImplement.LocalDBTemperatureServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class IoTController {

    /**
     * implement by fetching data from local database
     * TODO: replaced by IoT interface
     */
    @Autowired
    private TemperatureService temperatureService;

    @GetMapping("/temp")
    public GeneralMessage getTemperature() {
        return temperatureService.getTemperature();
    }
}
