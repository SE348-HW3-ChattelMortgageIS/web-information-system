package cmis.service.serviceImplement;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import cmis.dto.GeneralMessage;
import cmis.repository.UserEntityRepository;
import cmis.dto.RegisterRequest;
import cmis.entity.UserEntity;
import cmis.service.AccountService;

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
