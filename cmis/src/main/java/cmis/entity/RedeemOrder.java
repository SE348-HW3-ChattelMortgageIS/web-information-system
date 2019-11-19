package cmis.entity;

import java.math.BigDecimal;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class RedeemOrder
{
  @Id
  @GeneratedValue
  private Integer orderId;

  @OneToOne
  @JoinColumn(nullable = false, unique = true)
  private DepositReceipt depositReceipt;

  @Column(nullable = false)
  private Date paymentDeadline;

  @Column(nullable = false)
  private Double dailyInterest;

  @Column(nullable = false)
  private BigDecimal redeemPrice;

  @Column(nullable = false)
  private Boolean payed;

  public Integer getOrderId() {
    return orderId;
  }

  public void setOrderId(Integer orderId) {
    this.orderId = orderId;
  }

  public DepositReceipt getDepositReceipt() {
    return depositReceipt;
  }

  public void setDepositReceipt(DepositReceipt depositReceipt) {
    this.depositReceipt = depositReceipt;
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

  public Boolean getPayed() {
    return payed;
  }

  public void setPayed(Boolean payed) {
    this.payed = payed;
  }
}
