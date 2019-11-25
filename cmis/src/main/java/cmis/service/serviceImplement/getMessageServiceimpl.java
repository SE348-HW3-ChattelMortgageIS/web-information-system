package cmis.service.serviceImplement;


import cmis.dto.GeneralMessage;
import cmis.dto.TemperatureInfo;
import cmis.service.TemperatureService;
import cmis.service.getMessageService;
import com.alibaba.fastjson.JSONException;
import com.aliyun.openservices.iot.api.Profile;
import com.aliyun.openservices.iot.api.message.MessageClientFactory;
import com.aliyun.openservices.iot.api.message.api.MessageClient;
import com.aliyun.openservices.iot.api.message.callback.ConnectionCallback;
import com.aliyun.openservices.iot.api.message.callback.MessageCallback;
import com.aliyun.openservices.iot.api.message.entity.Message;
import com.aliyun.openservices.iot.api.message.entity.MessageToken;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;

@Service
public class getMessageServiceimpl implements getMessageService, TemperatureService {

    private MessageClient messageClient;

    private MessageCallback messageCallback;

    private String[] status=new String[100];

    private Double Temperature;

    private Double humidity;

    private Date timestamp;

    private static final int TEMP_COUNT = 50;

    private static ArrayList<TemperatureInfo> tempList = new ArrayList<>();

    @Autowired
    public void initmessageClient()
    {
        Arrays.fill(status,"yes");
        String accessKey = "LTAI4FiEEZF93DNiT6dfcwwL";
        String accessSecret = "RiPCaGOfIzxRGPE2ql4eqDl1LpQSnK";
        String regionId = "cn-shanghai";
        String uid = "1591662154888233";
        String endPoint = "https://" + uid + ".iot-as-http2." + regionId + ".aliyuncs.com";
        Profile profile = Profile.getAccessKeyProfile(endPoint, regionId, accessKey, accessSecret);
        messageClient = MessageClientFactory.messageClient(profile);
        messageClient.connect(messageToken -> {
            Message m = messageToken.getMessage();
            return MessageCallback.Action.CommitSuccess;
        });
        messageClient.setConnectionCallback(new ConnectionCallback() {
            @Override
            public void onConnectionLost() {
                messageClient.connect(messageToken -> {
                    Message m = messageToken.getMessage();
                    return MessageCallback.Action.CommitSuccess;
                });
            }

            @Override
            public void onConnected(boolean isReconnected) {
                System.out.println("连接成功，是否为重连: " + isReconnected);

            }
        });
        messageCallback = new MessageCallback() {
            @Override
            public Action consume(MessageToken messageToken) {
                Message m = messageToken.getMessage();
                String receive= new String(messageToken.getMessage().getPayload());
                try {
                    JSONObject jsonObject = new JSONObject(receive);
                    JSONObject items =(JSONObject)jsonObject.get("items");
                    JSONObject Data =(JSONObject)items.get("Data");
                    timestamp = new Date(Data.getLong("time"));
                    JSONObject value =(JSONObject)Data.get("value");
                    Temperature = value.getDouble("Temperature");
                    humidity = value.getDouble("humidity");
                    if (value != null) {
                        addTempToList(Temperature, humidity, timestamp);
                    }
                    String temp = value.getString("status");
                    status[Integer.parseInt(temp)] = "no";
                }catch (JSONException err){
                    System.out.print("error");
                }
                return Action.CommitSuccess;
            }
        };
        messageClient.setMessageListener("/a1UJLmxdlhK/#",messageCallback);
    }
    



    @Override
    public GeneralMessage getStatus(String id) {
        return new GeneralMessage(1, status[Integer.parseInt(id)], true,null);
    }
  
    @Override
    public  GeneralMessage testStatus(String id){
        if(id == "4")
            return new GeneralMessage(1,"yes",true,null);
        return new GeneralMessage(1,"yes",true,null);
    }

    @Override
    public GeneralMessage getTemperature() {
        return new GeneralMessage(1, "yes", true, tempList);
    }

    private void addTempToList(Double temperature, double humidity, Date time) {
        if (tempList.size() < TEMP_COUNT) {
            tempList.add(new TemperatureInfo(temperature, humidity, time));
        }
        else {
            tempList.remove(0);
            tempList.add(new TemperatureInfo(temperature, humidity, time));
        }

    }
}
