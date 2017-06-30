package tsi.map.repo;


import org.springframework.data.jpa.repository.JpaRepository;
import tsi.map.model.Login;

public interface LoginRepo extends JpaRepository<Login, Integer> {
}
