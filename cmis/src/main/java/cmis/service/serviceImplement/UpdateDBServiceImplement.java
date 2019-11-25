package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
import cmis.entity.DepositReceipt;
import cmis.entity.ExceptionAlarm;
import cmis.entity.SteelRoll;
import cmis.repository.DepositReceiptRepostitory;
import cmis.repository.ExceptionAlarmRepository;
import cmis.repository.SteelRollRepository;
import cmis.service.UpdateService;
import cmis.service.getMessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
@Service
public class UpdateDBServiceImplement implements UpdateService {
    @Autowired
    private SteelRollRepository steelRollRepository;
    @Autowired
    private DepositReceiptRepostitory depositReceiptRepostitory;
    @Autowired
    private getMessageService getMessageService1;
    @Autowired
    private ExceptionAlarmRepository exceptionAlarmRepository;
    @Override
    public  boolean update(){
        List<SteelRoll> steelRolls = steelRollRepository.findByMovableFalse();
        for(int i=0;i<steelRolls.size();i++){
            GeneralMessage message =  getMessageService1.getStatus(Integer.toString(steelRolls.get(i).getSteelRollId()));
            String flag = message.getMessage();
            if(!flag.equals("no")){
                DepositReceipt depositReceipt = depositReceiptRepostitory.findBySteelRoll(steelRolls.get(i));
                int a = steelRollRepository.abnormal(steelRolls.get(i).getSteelRollId());
                int b= depositReceiptRepostitory.abnormal(depositReceipt.getReceiptId());
                if(a==0)
                    System.out.println("steelStatus");
                if(b==0)
                    System.out.println("d");
                ExceptionAlarm exceptionAlarm = exceptionAlarmRepository.findByDepositReceipt(depositReceipt);
                if(exceptionAlarm == null){
                    ExceptionAlarm new_alarm = new ExceptionAlarm();
                    new_alarm.setStartAt(new Date());
                    new_alarm.setAlarmType(ExceptionAlarm.AlarmType.STEEL_ALARM);
                    new_alarm.setAlarmState(ExceptionAlarm.AlarmState.UNREAD);
                    new_alarm.setDepositReceipt(depositReceipt);
                    exceptionAlarmRepository.save(new_alarm);
                }
                else{
                    exceptionAlarmRepository.updateTime(exceptionAlarm.getAlarmId(),new Date());
                }
            }
        }
        return true;
    }
    @Override
    public GeneralMessage generateAlarm(String id){
        SteelRoll steelRoll = steelRollRepository.findBySteelRollId(Integer.parseInt(id));
        if(steelRoll == null)
            return new GeneralMessage(500,"not find the steelroll",false,null);
        if(steelRoll.getMovable())
            return new GeneralMessage(200,"the steelroll is movable",true,null);
        GeneralMessage message =  getMessageService1.testStatus(Integer.toString(steelRoll.getSteelRollId()));
        String flag = message.getMessage();
        if(flag != "no"){
            DepositReceipt depositReceipt = depositReceiptRepostitory.findBySteelRoll(steelRoll);
            if(depositReceipt == null){
                return new GeneralMessage(500,"not find the matched depositReceipt",true,null);
            }
            int a = steelRollRepository.abnormal(steelRoll.getSteelRollId());
            int b= depositReceiptRepostitory.abnormal(depositReceipt.getReceiptId());
            ExceptionAlarm exceptionAlarm = exceptionAlarmRepository.findByDepositReceipt(depositReceipt);
            if(exceptionAlarm == null){
                ExceptionAlarm new_alarm = new ExceptionAlarm();
                new_alarm.setStartAt(new Date());
                new_alarm.setAlarmType(ExceptionAlarm.AlarmType.STEEL_ALARM);
                new_alarm.setAlarmState(ExceptionAlarm.AlarmState.UNREAD);
                new_alarm.setDepositReceipt(depositReceipt);
                exceptionAlarmRepository.save(new_alarm);
            }
            else{
                exceptionAlarmRepository.updateTime(exceptionAlarm.getAlarmId(),new Date());
            }
        }
        return new GeneralMessage(200,"generate alarm succeed!",true,null);
    }

}
