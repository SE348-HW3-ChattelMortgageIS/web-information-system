package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.dto.RegisterRequest;
import cmis.entity.UserEntity.UserType;
import cmis.service.AccountService;
import org.json.JSONObject;
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

  @RequestMapping(value = "/register", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
  @ResponseBody
  public GeneralMessage register(@RequestBody JSONObject registerRequest) {
    System.out.println(registerRequest);
    RegisterRequest request = new RegisterRequest();
    request.setPassword(registerRequest.getString("password"));
    request.setUsername(registerRequest.getString("username"));
    request.setPhone(registerRequest.getString("phone"));
    int userType = registerRequest.getInt("type");
    if (userType == 1) {
      request.setType(UserType.CUSTOMER);
    } else {
      request.setType(UserType.BANK);
    }
    return this.accountService.register(request);
  }
}
