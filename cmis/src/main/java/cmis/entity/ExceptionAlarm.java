package cmis.entity;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;


@Entity
public class
ExceptionAlarm {
  @Id
  @GeneratedValue
  private Integer alarmId;

  @OneToOne
  @JoinColumn(nullable = false, unique = true)
  private DepositReceipt depositReceipt;

  @Column(nullable = false)
  private Date startAt;

  @Column()
  private Date endAt;

  @Column(nullable = false)
  private AlarmState alarmState;

  @Column(nullable = false)
  private AlarmType alarmType;

  public enum AlarmState {
    UNREAD,
    READ,
    SOLVED
  }

  public enum AlarmType {
    STEEL_ALARM,
    REPOSITORY_ALARM
  }

  public Integer getAlarmId() {
    return alarmId;
  }

  public void setAlarmId(Integer alarmId) {
    this.alarmId = alarmId;
  }

  public DepositReceipt getDepositReceipt() {
    return depositReceipt;
  }

  public void setDepositReceipt(DepositReceipt depositReceipt) {
    this.depositReceipt = depositReceipt;
  }

  public Date getStartAt() {
    return startAt;
  }

  public void setStartAt(Date startAt) {
    this.startAt = startAt;
  }

  public Date getEndAt() {
    return endAt;
  }

  public void setEndAt(Date endAt) {
    this.endAt = endAt;
  }

  public AlarmState getAlarmState() {
    return alarmState;
  }

  public void setAlarmState(AlarmState alarmState) {
    this.alarmState = alarmState;
  }

  public AlarmType getAlarmType() {
    return alarmType;
  }

  public void setAlarmType(AlarmType alarmType) {
    this.alarmType = alarmType;
  }
}
