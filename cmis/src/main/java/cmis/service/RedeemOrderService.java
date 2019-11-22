package cmis.service;

import cmis.dto.GeneralMessage;
import cmis.dto.RedeemOrderComplement;
import cmis.dto.RedeemOrderCreateRequest;


public interface RedeemOrderService {
    GeneralMessage createRedeemOrder(RedeemOrderCreateRequest orderCreateRequest);

    GeneralMessage getRedeemOrderById(Integer orderId);

    GeneralMessage getAllRedeemOrders();

    /**
     * 确认赎回钢卷，标记钢卷有效
     *
     * @param orderId
     * @return
     */
    GeneralMessage checkRedeemOrder(Integer orderId);

    GeneralMessage completeRedeemOrder(RedeemOrderComplement redeemOrderComplement);

}
