package cmis.dto;

import java.math.BigDecimal;
import java.util.Date;

public class RedeemOrderComplement {

    private Integer orderId;

    private Date paymentDeadline;

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

    public Date getPaymentDeadline() {
        return paymentDeadline;
    }

    public void setPaymentDeadline(Date paymentDeadline) {
        this.paymentDeadline = paymentDeadline;
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
