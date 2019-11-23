package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.DepositReceipt;
import cmis.entity.RepositoryPosition;
import cmis.entity.SteelRoll;
import cmis.entity.UserEntity;
import cmis.repository.DepositReceiptRepostitory;
import cmis.repository.RepositoryPositionRepository;
import cmis.repository.SteelRollRepository;
import cmis.repository.UserEntityRepository;
import cmis.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

import static cmis.entity.DepositReceipt.DepositReceiptState.IN_EFFECT;
import static cmis.entity.DepositReceipt.DepositReceiptState.UNFINISHED;
import static cmis.entity.SteelRoll.SteelRollState.*;

@Service
public class DepositServiceServiceImplement implements DepositService {
    @Autowired
    private DepositReceiptRepostitory depositReceiptRepostitory;

    @Autowired
    private SteelRollRepository steelRollRepository;

    @Autowired
    private UserEntityRepository userEntityRepository;

    public GeneralMessage createDeposit(Integer steelRollId, Integer mortgageDays) {
        DepositReceipt depositReceipt = new DepositReceipt();
        SteelRoll steelRoll = steelRollRepository.findBySteelRollId(steelRollId);
        if (!(steelRoll.getSteelRollState().equals(REDEEMED) || steelRoll.getSteelRollState().equals(NOT_MORTGAGED))) {
            return new GeneralMessage(500, "wrong state", false, null);
        }
        Date now = new Date(0);
        Date date = new Date();
        depositReceipt.setMorgageDays(mortgageDays);
        depositReceipt.setCreatedAt(date);
        depositReceipt.setInEffect(false);
        depositReceipt.setSteelRoll(steelRoll);
        depositReceipt.setReceiptState(UNFINISHED);
        depositReceipt.setStartAt(now);
        steelRoll.setSteelRollState(TO_BE_MORTGAGED);
        steelRoll.setMovable(false);
        try {
            steelRollRepository.save(steelRoll);
            depositReceiptRepostitory.save(depositReceipt);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
        return new GeneralMessage(200, "Deposit receipt is created", true, depositReceipt);
    }

    public GeneralMessage effectDeposit(Integer receiptId) {
        DepositReceipt depositReceipt = depositReceiptRepostitory.findByReceiptId(receiptId);
        depositReceipt.setReceiptState(IN_EFFECT);
        SteelRoll steelRoll = depositReceipt.getSteelRoll();
        steelRoll.setSteelRollState(MORTGAGED);
        Date now = new Date();
        depositReceipt.setStartAt(now);
        depositReceipt.setInEffect(true);
        try {
            steelRollRepository.save(steelRoll);
            depositReceiptRepostitory.save(depositReceipt);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
        return new GeneralMessage(200, "Deposit receipt is effect", true, depositReceipt);
    }

    public GeneralMessage depositMessage() {
        try {
            List<DepositReceipt> listDeposit = depositReceiptRepostitory.findAll();
            return new GeneralMessage(200, "get deposit receipt message", true, listDeposit);
        } catch (Exception e) {
            return new GeneralMessage(500, e.getMessage(), false, null);
        }
    }
}
