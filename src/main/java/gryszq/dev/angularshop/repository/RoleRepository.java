package gryszq.dev.angularshop.repository;

import gryszq.dev.angularshop.model.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role,Long> {
    Role findByName(String role);
}
