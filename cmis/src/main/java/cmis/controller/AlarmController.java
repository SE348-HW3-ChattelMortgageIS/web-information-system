package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.service.AlarmService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/alarm")
@CrossOrigin("*")
public class AlarmController {
  @Autowired
  private AlarmService alarmService;

  @RequestMapping(value = "/get", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public GeneralMessage getAlarms() {
    return this.alarmService.getAlarm();
  }

  @RequestMapping(value = "/read", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public GeneralMessage readAlarm(@RequestParam(name = "id", required = true) Integer id) {
    return this.alarmService.readAlarm(id);
  }

  @RequestMapping(value = "/fix", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public GeneralMessage fixAlarm(@RequestParam(name = "id", required = true) Integer id) {
    return this.alarmService.fixAlarm(id);
  }
}
