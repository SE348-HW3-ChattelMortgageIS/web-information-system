package se348.cmis.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import se348.cmis.entity.UserEntity;

@Repository
public interface UserEntityRepository extends JpaRepository<UserEntity, Integer> {
  UserEntity findByUsername(String username);
  UserEntity findByPhone(String phone);
}
