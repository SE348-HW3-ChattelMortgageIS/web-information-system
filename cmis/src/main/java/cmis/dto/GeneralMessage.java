package cmis.dto;

public class GeneralMessage {
  private int state;
  private String message;
  private Boolean succeeded;
  private Object entity;

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

  public Object getEntity() {
    return entity;
  }

  public void setEntity(Object entity) {
    this.entity = entity;
  }

  @Override
  public String toString() {
    return "{" +
        "\"state\":" + state +
        ", \"message\": \"" + message +
        "\", \"succeeded\":" + succeeded +
        '}';
  }

  public GeneralMessage(int state, String message, Boolean succeeded, Object entity) {
    this.state = state;
    this.message = message;
    this.succeeded = succeeded;
    this.entity = entity;
  }

  public GeneralMessage() {
    this.entity = null;
  }
}
