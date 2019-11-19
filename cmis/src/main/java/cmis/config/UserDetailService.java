package cmis.config;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import cmis.entity.UserEntity;
import cmis.entity.UserEntity.UserType;
import cmis.repository.UserEntityRepository;

@Service
public class UserDetailService implements UserDetailsService {
    @Autowired
    UserEntityRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String usernameOrPhone) {
        UserEntity user = userRepository.findByUsername(usernameOrPhone);
        if(user == null){

            throw new UsernameNotFoundException("User Not Found");
        }
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority(usernameOrPhone));
        return new User(
                user.getUsername(),
                user.getPassword(),
                getGrantedAuthorities(user));
    }

    private Collection<GrantedAuthority> getGrantedAuthorities(UserEntity user) {
        Collection<GrantedAuthority> grantedAuthority = new ArrayList<>();
        if (user.getType()== UserType.BANK) {
            grantedAuthority.add(new SimpleGrantedAuthority("BANK"));
        }
        else if (user.getType()== UserType.CUSTOMER) {
            grantedAuthority.add(new SimpleGrantedAuthority("CUSTOMER"));
        }
        return grantedAuthority;
    }
}
