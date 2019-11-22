package cmis.service.serviceImplement;

import cmis.dto.GeneralMessage;
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
import sun.rmi.runtime.Log;

import java.util.Arrays;
import java.util.Iterator;
import java.util.Map;

@Service
public class getMessageServiceimpl implements getMessageService {

    private MessageClient messageClient;

    private MessageCallback messageCallback;

    private String status;

    @Autowired
    public void initmessageClient()
    {
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
                    status = Data.getString("value");
                    System.out.print(status);
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
        return new GeneralMessage(1, status, true,null);
    }
}
