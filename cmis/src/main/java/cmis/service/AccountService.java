package cmis.service;

import cmis.dto.GeneralMessage;
import cmis.dto.RegisterRequest;

public interface AccountService {

  GeneralMessage register(RegisterRequest registerRequest);
}
