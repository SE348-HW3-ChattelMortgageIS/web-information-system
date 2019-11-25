package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.dto.RegisterRequest;
import cmis.service.AccountService;
import cmis.service.initDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class AccountController {

  @Autowired
  private AccountService accountService;
  @Autowired
  private initDate init;

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public GeneralMessage register(@RequestBody RegisterRequest request) {
    return this.accountService.register(request);
  }

  @RequestMapping(value = "/init", method = RequestMethod.GET)
  @ResponseBody
  public String init() {
    return this.init.fillTestData();
  }
}
