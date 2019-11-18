package se348.cmis.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import se348.cmis.dto.GeneralMessage;
import se348.cmis.dto.RegisterRequest;
import se348.cmis.service.AccountService;

@RestController
@RequestMapping(value = "/api")
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
