package cmis.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Date;

@Entity
public class TemperatureHumidityInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer TemperatureHumidityInfoId;

    private Double temperature;

    private Double humidity;

    private Date time;

    public Integer getTemperatureHumidityInfoId() {
        return TemperatureHumidityInfoId;
    }

    public void setTemperatureHumidityInfoId(Integer temperatureHumidityInfoId) {
        TemperatureHumidityInfoId = temperatureHumidityInfoId;
    }

    public Double getTemperature() {
        return temperature;
    }

    public void setTemperature(Double temperature) {
        this.temperature = temperature;
    }

    public Double getHumidity() {
        return humidity;
    }

    public void setHumidity(Double humidity) {
        this.humidity = humidity;
    }

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
