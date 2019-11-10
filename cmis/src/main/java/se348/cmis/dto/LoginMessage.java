package se348.cmis.dto;

public class LoginMessage {
  private int state;
  private String message;
  private Boolean succeeded;

  public int getState() {
    return state;
  }

  public void setState(int state) {
    this.state = state;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public Boolean getSucceeded() {
    return succeeded;
  }

  public void setSucceeded(Boolean succeeded) {
    this.succeeded = succeeded;
  }

  @Override
  public String toString() {
    return "{" +
        "\"state\":" + state +
        ", \"message\": \"" + message +
        "\", \"succeeded\":" + succeeded +
        '}';
  }
}
