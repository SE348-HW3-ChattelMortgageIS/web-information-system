package cmis.entity;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;

@Entity
public class DepositReceipt {
  @Id
  @GeneratedValue
  private Integer receiptId;

  @OneToOne
  @JoinColumn(nullable = false, unique = true)
  private SteelRoll steelRoll;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @Column()
  private Date startAt;

  @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
  @Column(nullable = false)
  private Date createdAt;

  @Column(nullable = false)
  private Integer morgageDays;

  @Column(nullable = false)
  private DepositReceiptState receiptState;

  @Column(nullable = false)
  private Boolean inEffect;

  @Column()
  private String remark;

  public Integer getReceiptId() {
    return receiptId;
  }

  public void setReceiptId(Integer receiptId) {
    this.receiptId = receiptId;
  }

  public SteelRoll getSteelRoll() {
    return steelRoll;
  }

  public void setSteelRoll(SteelRoll steelRoll) {
    this.steelRoll = steelRoll;
  }

  public Date getStartAt() {
    return startAt;
  }

  public void setStartAt(Date startAt) {
    this.startAt = startAt;
  }

  public Date getCreatedAt() {
    return createdAt;
  }

  public void setCreatedAt(Date createdAt) {
    this.createdAt = createdAt;
  }

  public Integer getMorgageDays() {
    return morgageDays;
  }

  public void setMorgageDays(Integer morgageDays) {
    this.morgageDays = morgageDays;
  }

  public DepositReceiptState getReceiptState() {
    return receiptState;
  }

  public void setReceiptState(DepositReceiptState receiptState) {
    this.receiptState = receiptState;
  }

  public Boolean getInEffect() {
    return inEffect;
  }

  public void setInEffect(Boolean inEffect) {
    this.inEffect = inEffect;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public enum DepositReceiptState {
    UNFINISHED,
    IN_EFFECT,
    REDEEMING,
    REDEEMED,
    NONEFFECTIVE
  }


}
