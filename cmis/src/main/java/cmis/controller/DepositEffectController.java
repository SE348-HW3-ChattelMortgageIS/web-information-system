package cmis.controller;


import cmis.dto.GeneralMessage;
import cmis.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
@CrossOrigin("*")
public class DepositEffectController {
    @Autowired
    private DepositService depositService;

    @RequestMapping(value = "/depositeffect", method = RequestMethod.POST, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GeneralMessage DepositEffect(HttpServletRequest httpServletRequest) {
        Integer receiptId = new Integer(httpServletRequest.getParameter("receiptid"));
        return this.depositService.effectDeposit(receiptId);
    }

    @RequestMapping(value = "/depositmessage", method = RequestMethod.GET, produces = "application/json;charset=UTF-8")
    @ResponseBody
    public GeneralMessage DepositMessage() {
        return this.depositService.depositMessage();
    }
}
