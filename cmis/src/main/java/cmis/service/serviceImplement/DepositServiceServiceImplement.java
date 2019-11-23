package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.DepositReceipt;
import cmis.entity.SteelRoll;
import cmis.repository.DepositReceiptRepostitory;
import cmis.repository.SteelRollRepository;
import cmis.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

import static cmis.entity.DepositReceipt.DepositReceiptState.IN_EFFECT;
import static cmis.entity.DepositReceipt.DepositReceiptState.UNFINISHED;

@Service
public class DepositServiceServiceImplement implements DepositService {
    @Autowired
    private DepositReceiptRepostitory depositReceiptRepostitory;

    @Autowired
    private SteelRollRepository steelRollRepository;

    public GeneralMessage createDeposit(Integer steelRollId,Integer mortgageDays){
        DepositReceipt depositReceipt = new DepositReceipt();
        SteelRoll steelRoll = steelRollRepository.findBySteelRollId(steelRollId);
        Date now = new Date(0);
        Date date = new Date();
        depositReceipt.setMorgageDays(mortgageDays);
        depositReceipt.setCreatedAt(date);
        depositReceipt.setInEffect(false);
        depositReceipt.setSteelRoll(steelRoll);
        depositReceipt.setReceiptState(UNFINISHED);
        depositReceipt.setStartAt(now);
        try {
           depositReceiptRepostitory.save(depositReceipt);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
        return new GeneralMessage(200, "Deposit receipt is created", true, depositReceipt);
    }

    public GeneralMessage effectDeposit(Integer receiptId){
        DepositReceipt depositReceipt = depositReceiptRepostitory.findByReceiptId(receiptId);
        depositReceipt.setReceiptState(IN_EFFECT);
        Date now = new Date();
        depositReceipt.setStartAt(now);
        depositReceipt.setInEffect(true);
        try {
            depositReceiptRepostitory.save(depositReceipt);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
        return new GeneralMessage(200, "Deposit receipt is effect", true, depositReceipt);
    }
}
