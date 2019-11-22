package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class DepositCreateController {
    @Autowired
    private DepositService depositService;

    @RequestMapping(value = "customer/depositcreate", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GeneralMessage DepositCreate(HttpServletRequest httpServletRequest) {
        Integer steelRollId = new Integer(httpServletRequest.getParameter("steelrollid"));
        Integer mortgageDays = new Integer(httpServletRequest.getParameter("mortgagedays"));
        return this.depositService.createDeposit(steelRollId,mortgageDays);
    }
}
