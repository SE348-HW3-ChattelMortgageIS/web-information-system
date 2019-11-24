package cmis.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RedeemOrderComplement {

    private Integer orderId;

    private Double dailyInterest;

    private BigDecimal redeemPrice;

    public RedeemOrderComplement() {
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Double getDailyInterest() {
        return dailyInterest;
    }

    public void setDailyInterest(Double dailyInterest) {
        this.dailyInterest = dailyInterest;
    }

    public BigDecimal getRedeemPrice() {
        return redeemPrice;
    }

    public void setRedeemPrice(BigDecimal redeemPrice) {
        this.redeemPrice = redeemPrice;
    }
}
