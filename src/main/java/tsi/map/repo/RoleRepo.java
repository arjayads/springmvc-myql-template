package tsi.map.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import tsi.map.model.Role;

import java.util.List;

public interface RoleRepo extends JpaRepository<Role, Integer> {

    List<Role> findByName(String name);

}
