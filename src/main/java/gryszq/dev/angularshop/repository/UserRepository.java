package gryszq.dev.angularshop.repository;

import gryszq.dev.angularshop.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Long> {

    User findByUsername(String username);

    User findUserById(Long id);

//    @Query(value = "select id_user, name, username from user and user_roles.roles_id where id_user = user_roles.user_id_user",nativeQuery = true)

    @Query(value ="select user.id_user, user.name, user.username, user_roles.roles_id from user, user_roles where user.id_user = user_roles.user_id_user", nativeQuery = true)
    List<Object[]> findAllQuery();

}
