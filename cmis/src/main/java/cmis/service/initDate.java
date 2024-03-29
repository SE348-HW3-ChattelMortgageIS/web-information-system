package cmis.service;

import cmis.dto.RegisterRequest;
import cmis.entity.DepositReceipt;
import cmis.entity.DepositReceipt.DepositReceiptState;
import cmis.entity.ExceptionAlarm;
import cmis.entity.ExceptionAlarm.AlarmState;
import cmis.entity.ExceptionAlarm.AlarmType;
import cmis.entity.RedeemOrder;
import cmis.entity.RepositoryPosition;
import cmis.entity.SteelRoll;
import cmis.entity.SteelRoll.SteelRollState;
import cmis.entity.UserEntity;
import cmis.entity.UserEntity.UserType;
import cmis.repository.DepositReceiptRepostitory;
import cmis.repository.ExceptionAlarmRepository;
import cmis.repository.RedeemOrderRepository;
import cmis.repository.RepositoryPositionRepository;
import cmis.repository.SteelRollRepository;
import java.math.BigDecimal;
import java.util.Date;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class initDate {
  @Autowired
  getMessageService getMessageService;
  @Autowired
  AccountService accountService;
  @Autowired
  DepositReceiptRepostitory depositReceiptRepostitory;
  @Autowired
  ExceptionAlarmRepository exceptionAlarmRepository;
  @Autowired
  RedeemOrderRepository redeemOrderRepository;
  @Autowired
  RepositoryPositionRepository repositoryPositionRepository;
  @Autowired
  SteelRollRepository steelRollRepository;

  public String fillTestData() {
    UserEntity bbg = this.initCus();
    UserEntity hhq = this.initBank();
    for (int j = 0; j < 100; j++) {
      RepositoryPosition position = new RepositoryPosition();
      position.setCode("RP" + j);
      position.setInMortgage(true);
      position.setInUse(true);
      position = this.repositoryPositionRepository.save(position);
      SteelRoll roll = new SteelRoll();
      roll.setBelongTo(bbg);
      roll.setPrice(BigDecimal.valueOf(81234.23));
      roll.setPosition(position);
      roll.setRemark("nothing");
      if (j < 50 && j % 6 == 1) {
        roll.setSteelRollState(SteelRollState.TO_BE_MORTGAGED);
      } else if (j < 50 && j % 6 == 2) {
        roll.setSteelRollState(SteelRollState.MORTGAGED);
      } else if (j < 50 && j % 6 == 3) {
        roll.setSteelRollState(SteelRollState.TO_BE_REDEEMED);
      } else if (j < 50 && j % 6 == 4) {
        roll.setSteelRollState(SteelRollState.REDEEMED);
      } else if (j < 50 && j % 6 == 5) {
        roll.setSteelRollState(SteelRollState.ABNORMAL);
      } else if (j < 70) {
        roll.setSteelRollState(SteelRollState.MORTGAGED);
      } else {
        roll.setSteelRollState(SteelRollState.NOT_MORTGAGED);
      }
      if (roll.getSteelRollState() == SteelRollState.NOT_MORTGAGED
          || roll.getSteelRollState() == SteelRollState.REDEEMED) {
        roll.setMovable(true);
      } else {
        roll.setMovable(true);
      }
      roll = this.steelRollRepository.save(roll);
      if (roll.getSteelRollState() == SteelRollState.NOT_MORTGAGED) {
      } else {
        DepositReceipt receipt = new DepositReceipt();
        receipt.setCreatedAt(new Date());
        receipt.setInEffect(true);
        receipt.setMorgageDays(1001);
        receipt.setRemark("Nothing");
        receipt.setStartAt(new Date());
        receipt.setSteelRoll(roll);
        if (roll.getSteelRollState() == SteelRollState.TO_BE_MORTGAGED) {
          receipt.setReceiptState(DepositReceiptState.UNFINISHED);
        } else if (roll.getSteelRollState() == SteelRollState.MORTGAGED) {
          receipt.setReceiptState(DepositReceiptState.IN_EFFECT);
        } else if (roll.getSteelRollState() == SteelRollState.TO_BE_REDEEMED) {
          receipt.setReceiptState(DepositReceiptState.REDEEMING);
        } else if (roll.getSteelRollState() == SteelRollState.REDEEMED) {
          receipt.setReceiptState(DepositReceiptState.REDEEMED);
        } else if (roll.getSteelRollState() == SteelRollState.ABNORMAL) {
          receipt.setReceiptState(DepositReceiptState.NONEFFECTIVE);
        }
        receipt = depositReceiptRepostitory.save(receipt);

        if (receipt.getReceiptState() == DepositReceiptState.REDEEMING || receipt.getReceiptState() == DepositReceiptState.REDEEMED) {
          RedeemOrder redeemOrder = new RedeemOrder();
          redeemOrder.setDailyInterest(0.012);
          redeemOrder.setDepositReceipt(receipt);
          if (receipt.getReceiptState() == DepositReceiptState.REDEEMING) {
            redeemOrder.setPayed(false);
            redeemOrder.setRedeemOrderState(RedeemOrder.RedeemOrderState.COMPLETED);
          } else {
            redeemOrder.setPayed(true);
            redeemOrder.setRedeemOrderState(RedeemOrder.RedeemOrderState.PAID);
          }

          redeemOrder.setRedeemPrice(BigDecimal.valueOf(10092.312));
          this.redeemOrderRepository.save(redeemOrder);
        }

        if (receipt.getReceiptState() == DepositReceiptState.NONEFFECTIVE) {
          ExceptionAlarm alarm = new ExceptionAlarm();
          alarm.setAlarmState(AlarmState.UNREAD);
          alarm.setAlarmType(AlarmType.STEEL_ALARM);
          alarm.setDepositReceipt(receipt);
          alarm.setStartAt(new Date());
          alarm.setEndAt(new Date());
          this.exceptionAlarmRepository.save(alarm);
        }
      }

    }
    return "ok";
  }

  private UserEntity initCus() {
    RegisterRequest request = new RegisterRequest();
    request.setType(UserType.CUSTOMER);
    request.setPhone("13288230012");
    request.setUsername("customer");
    request.setPassword("bbg123123");
    //System.out.println("OK Here!");
    return (UserEntity) this.accountService.register(request).getEntity();
  }

  private UserEntity initBank() {
    RegisterRequest request = new RegisterRequest();
    request.setType(UserType.BANK);
    request.setPhone("13288230011");
    request.setUsername("bank");
    request.setPassword("hhq123123");
    return (UserEntity) this.accountService.register(request).getEntity();
  }

}
