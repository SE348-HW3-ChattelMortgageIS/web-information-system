package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.dto.RedeemOrderComplement;
import cmis.dto.RedeemOrderCreateRequest;
import cmis.entity.DepositReceipt;
import cmis.entity.RedeemOrder;
import cmis.entity.SteelRoll;
import cmis.repository.DepositReceiptRepostitory;
import cmis.repository.RedeemOrderRepository;
import cmis.repository.SteelRollRepository;
import cmis.service.RedeemOrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Date;

@Service
public class RedeemOrderServiceImplement implements RedeemOrderService {

    @Autowired
    private RedeemOrderRepository redeemOrderRepository;

    @Autowired
    private DepositReceiptRepostitory depositReceiptRepostitory;

    @Autowired
    private SteelRollRepository steelRollRepository;

    @Override
    public GeneralMessage createRedeemOrder(RedeemOrderCreateRequest orderCreateRequest) {
        RedeemOrder newRedeemOrder = new RedeemOrder();
        // set payed to false when creating a new order
        newRedeemOrder.setPayed(false);

        newRedeemOrder.setDailyInterest(0.0);
        newRedeemOrder.setRedeemPrice(new BigDecimal(0));
        newRedeemOrder.setPaymentDeadline(new Date());


        // check deposit not null
        if (!depositReceiptRepostitory.findById(orderCreateRequest.getDepositReceiptId()).isPresent()) {
            return new GeneralMessage(500, "deposit receipt not found", false, null);
        }

        DepositReceipt depositReceipt = depositReceiptRepostitory.findById(orderCreateRequest.getDepositReceiptId()).get();

        if (depositReceipt.getReceiptState() != DepositReceipt.DepositReceiptState.IN_EFFECT) {
            return new GeneralMessage(500, "deposit receipt not in effect", false, null);
        }

        // set receipt state to REDEEMING
        depositReceipt.setReceiptState(DepositReceipt.DepositReceiptState.REDEEMING);

        SteelRoll steelRoll = depositReceipt.getSteelRoll();

        if (steelRoll == null) {
            return new GeneralMessage(500, "steel roll not found", false, null);
        }

        // TODO: Precondition check, check steel roll state
        steelRoll.setSteelRollState(SteelRoll.SteelRollState.TO_BE_REDEEMED);

        try {
            newRedeemOrder.setDepositReceipt(depositReceipt);
            redeemOrderRepository.save(newRedeemOrder);
            depositReceiptRepostitory.save(depositReceipt);
            steelRollRepository.save(steelRoll);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
        return new GeneralMessage(200, "OK", true, newRedeemOrder);
    }

    @Override
    public GeneralMessage getRedeemOrderById(Integer orderId) {
        RedeemOrder redeemOrder;

        try {
            redeemOrder = redeemOrderRepository.findById(orderId).get();
        } catch (Exception e) {
            e.printStackTrace();
            return new GeneralMessage(500, "get redeem order failed", false, null);
        }
        return new GeneralMessage(200, "OK", true, redeemOrder);
    }

    @Override
    public GeneralMessage getAllRedeemOrders() {
        try {
            return new GeneralMessage(200, "OK", true, redeemOrderRepository.findAll());
        }
        catch (Exception e) {
            e.printStackTrace();
            return new GeneralMessage(500, "get all orders failed", false, null);
        }
    }

    @Override
    public GeneralMessage checkRedeemOrder(Integer orderId) {
        // check order not null
        if (!redeemOrderRepository.findById(orderId).isPresent()) {
            return new GeneralMessage(500, "order not found", false, null);
        }
        RedeemOrder redeemOrder = redeemOrderRepository.findById(orderId).get();

        // set order state to payed
        redeemOrder.setPayed(true);
        DepositReceipt depositReceipt = redeemOrder.getDepositReceipt();

        if (depositReceipt == null) {
           return new GeneralMessage(500, "Receipt corresponding to the order can't found", false ,null);
        }
        // set receipt state to REDEEMED
        depositReceipt.setReceiptState(DepositReceipt.DepositReceiptState.REDEEMED);

        SteelRoll steelRoll = depositReceipt.getSteelRoll();

        if (steelRoll == null) {
            return new GeneralMessage(500, "steel roll not found", false, null);
        }
        steelRoll.setSteelRollState(SteelRoll.SteelRollState.REDEEMED);

        try {
            steelRollRepository.save(steelRoll);
            depositReceiptRepostitory.save(depositReceipt);
            redeemOrderRepository.save(redeemOrder);
        }
        catch (Exception e) {
            return new GeneralMessage(500, "save change failed", false, null);
        }
        return new GeneralMessage(200, "OK", true, null);
    }

    @Override
    public GeneralMessage completeRedeemOrder(RedeemOrderComplement redeemOrderComplement) {
        // check order not null
        if (!redeemOrderRepository.findById(redeemOrderComplement.getOrderId()).isPresent()) {
            return new GeneralMessage(500, "order not found", false, null);

        }
        RedeemOrder redeemOrder = redeemOrderRepository.findById(redeemOrderComplement.getOrderId()).get();

        redeemOrder.setPaymentDeadline(redeemOrderComplement.getPaymentDeadline());
        redeemOrder.setDailyInterest(redeemOrderComplement.getDailyInterest());
        redeemOrder.setRedeemPrice(redeemOrderComplement.getRedeemPrice());

        try {
            redeemOrderRepository.save(redeemOrder);
        }
        catch (Exception e) {
            return new GeneralMessage(500, "save change failed", false, null);
        }
        return new GeneralMessage(200, "OK", true, null);
    }

}
