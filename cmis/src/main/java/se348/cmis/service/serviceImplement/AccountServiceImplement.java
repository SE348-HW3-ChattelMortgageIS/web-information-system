package se348.cmis.service.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import se348.cmis.dto.GeneralMessage;
import se348.cmis.dto.RegisterRequest;
import se348.cmis.entity.UserEntity;
import se348.cmis.repository.UserEntityRepository;
import se348.cmis.service.AccountService;

@Service
public class AccountServiceImplement implements AccountService {

  @Autowired
  private UserEntityRepository userEntityRepository;

  public GeneralMessage register(RegisterRequest registerRequest) {
    UserEntity newUser = new UserEntity();
    BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
    String hashedPw = encoder.encode(registerRequest.getPassword());

    newUser.setPhone(registerRequest.getPhone());
    newUser.setPassword(hashedPw);
    newUser.setType(registerRequest.getType());
    newUser.setUsername(registerRequest.getUsername());

    try {
      newUser = this.userEntityRepository.save(newUser);
    } catch (Exception e) {
      return new GeneralMessage(500, e.getMessage(), false, null);
    }
    return new GeneralMessage(200, "user.saved", true, newUser);
  }
}
