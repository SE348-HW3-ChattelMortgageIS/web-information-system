package se348.cmis.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class RepositoryPosition {
  @Id
  @GeneratedValue
  private Integer positionId;

  @Column(nullable = false)
  private String code;

  @Column(nullable = false)
  private Boolean inUse;

  @Column(nullable = false)
  private Boolean inMortgage;

  public Integer getPositionId() {
    return positionId;
  }

  public void setPositionId(Integer positionId) {
    this.positionId = positionId;
  }

  public String getCode() {
    return code;
  }

  public void setCode(String code) {
    this.code = code;
  }

  public Boolean getInUse() {
    return inUse;
  }

  public void setInUse(Boolean inUse) {
    this.inUse = inUse;
  }

  public Boolean getInMortgage() {
    return inMortgage;
  }

  public void setInMortgage(Boolean inMortgage) {
    this.inMortgage = inMortgage;
  }
}
