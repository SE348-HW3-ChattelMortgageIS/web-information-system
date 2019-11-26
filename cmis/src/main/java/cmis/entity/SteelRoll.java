package cmis.entity;

import java.math.BigDecimal;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

@Entity
public class SteelRoll {
  @Id
  @GeneratedValue
  private Integer steelRollId;

  @Column(nullable = false)
  private Boolean movable;

  @ManyToOne
  @JoinColumn(nullable = false)
  private UserEntity belongTo;

  @Column(nullable = false)
  private BigDecimal price;

  @Column(nullable = false)
  private SteelRollState steelRollState;

  @Column()
  private String remark;

  @OneToOne
  @JoinColumn(nullable = false)
  private RepositoryPosition position;

  public Integer getSteelRollId() {
    return steelRollId;
  }

  public void setSteelRollId(Integer steelRollId) {
    this.steelRollId = steelRollId;
  }

  public Boolean getMovable() {
    return movable;
  }

  public void setMovable(Boolean movable) {
    this.movable = movable;
  }

  public UserEntity getBelongTo() {
    return belongTo;
  }

  public void setBelongTo(UserEntity belongTo) {
    this.belongTo = belongTo;
  }

  public BigDecimal getPrice() {
    return price;
  }

  public void setPrice(BigDecimal price) {
    this.price = price;
  }

  public SteelRollState getSteelRollState() {
    return steelRollState;
  }

  public void setSteelRollState(SteelRollState steelRollState) {
    this.steelRollState = steelRollState;
  }

  public String getRemark() {
    return remark;
  }

  public void setRemark(String remark) {
    this.remark = remark;
  }

  public RepositoryPosition getPosition() {
    return position;
  }

  public void setPosition(RepositoryPosition position) {
    this.position = position;
  }

  public enum SteelRollState {
    TO_BE_VERIFIED,
    NOT_MORTGAGED,
    TO_BE_MORTGAGED,
    MORTGAGED,
    TO_BE_REDEEMED,
    ABNORMAL,
    REDEEMED
  }

}
