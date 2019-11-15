package se348.cmis.service;

import se348.cmis.dto.GeneralMessage;
import se348.cmis.dto.RegisterRequest;

public interface AccountService {

  GeneralMessage register(RegisterRequest registerRequest);
}
