package cmis.service;

import cmis.dto.GeneralMessage;

public interface DepositService {
    GeneralMessage createDeposit(Integer steelRollId,Integer mortgageDays);
    GeneralMessage effectDeposit(Integer receiptId);
}
