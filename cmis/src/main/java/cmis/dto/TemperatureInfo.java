package cmis.dto;

import java.util.Date;

public class TemperatureInfo {
    public Double Temperature;

    public Double humidity;

    public Date timestamp;

    public TemperatureInfo(Double Temperature, Double humidity, Date timestamp)
    {
        this.Temperature=Temperature;
        this.humidity=humidity;
        this.timestamp=timestamp;
    }

}
