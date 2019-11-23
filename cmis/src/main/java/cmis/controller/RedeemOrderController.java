package cmis.controller;

import cmis.dto.GeneralMessage;
import cmis.dto.RedeemOrderComplement;
import cmis.dto.RedeemOrderCreateRequest;
import cmis.service.RedeemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin
public class RedeemOrderController {

    @Autowired
    private RedeemOrderService redeemOrderService;

    @GetMapping(value = "/order")
    public GeneralMessage getRedeemOrderById(@RequestParam("orderId") Integer orderId) {
        return redeemOrderService.getRedeemOrderById(orderId);
    }

    @GetMapping(value = "/order/all")
    public GeneralMessage getAllRedeemOrders() {
        return redeemOrderService.getAllRedeemOrders();
    }

    @GetMapping(value = "/order/check")
    public GeneralMessage checkRedeemOrder(@RequestParam("orderId") Integer orderId) {
        return redeemOrderService.checkRedeemOrder(orderId);
    }

    @PostMapping(value = "/order/create", produces = "application/json;charset=UTF-8")
    public GeneralMessage createRedeemOrder(@RequestBody RedeemOrderCreateRequest orderCreateRequest) {
        System.out.println(orderCreateRequest.getDepositReceiptId());
        return redeemOrderService.createRedeemOrder(orderCreateRequest);
    }

    @PostMapping(value = "/order/complete", produces = "application/json;charset=UTF-8")
    public GeneralMessage completeRedeemOrder(@RequestBody RedeemOrderComplement orderComplement) {
        return redeemOrderService.completeRedeemOrder(orderComplement);
    }

}
