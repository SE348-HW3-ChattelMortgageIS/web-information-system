package cmis.component;

import cmis.dto.GeneralMessage;
import net.sf.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.apache.commons.codec.binary.Base64;
import org.springframework.web.client.RestTemplate;

@Component
public class IOTAdapter {

  private final String getURL = "http://182.92.106.52/get/status/";
  private final String publicKey = "70f3eb7d7acd43388f694a511bb882b0";
  private final String secretKey = "09D5BF22F88126887386441B20366403";

  public GeneralMessage getSteelRollStatus(Integer id) {
    String url = this.getURL + id;
    GeneralMessage message = new GeneralMessage();

    /* encrypt token */
    long time = System.currentTimeMillis();
    String timeInSecond = String.valueOf(time / 1000);
    String token = this.publicKey + timeInSecond + this.secretKey;
    String secreteToken;
    try {
      secreteToken = Base64.encodeBase64String(token.getBytes()).toUpperCase();
    } catch (Exception e) {
      message.setMessage("error in encryption: " + e.getMessage());
      message.setState(500);
      message.setSucceeded(false);
      message.setEntity(token);
      return message;
    }

    /* send and get http request/response */
    HttpHeaders httpHeaders = new HttpHeaders();
    httpHeaders.add("Content-Type", "application/json; charset=UTF-8");
    httpHeaders.add("token", secreteToken);
    httpHeaders.add("time", timeInSecond);
    HttpEntity httpEntity = new HttpEntity(httpHeaders);
    RestTemplate restTemplate = new RestTemplate();
    ResponseEntity responseEntity;
    try {
      responseEntity= restTemplate
          .exchange(url, HttpMethod.GET, httpEntity, JSONObject.class);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      message.setMessage("error when sending http request to " + url + ": " + e.getMessage());
      message.setState(500);
      message.setSucceeded(false);
      message.setEntity(null);
      return message;
    }
    message.setEntity(responseEntity.getBody());
    message.setSucceeded(true);
    message.setState(200);
    message.setMessage("get from: " + url);
    return message;
  }

}
