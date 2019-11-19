package cmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import cmis.dto.GeneralMessage;
import cmis.dto.RegisterRequest;
import cmis.service.AccountService;

@RestController
@RequestMapping()
@CrossOrigin("*")
public class AccountController {

  @Autowired
  private AccountService accountService;

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public GeneralMessage register(@RequestBody RegisterRequest registerRequest) {
    return this.accountService.register(registerRequest);
  }
}
