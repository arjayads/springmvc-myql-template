package tsi.map.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import tsi.map.model.User;

import java.util.List;


public interface UserRepo extends JpaRepository<User, Integer> {

    User findOneByUsername(String username);
    User findOneByAccountNo(Integer accountNo);
    List<User> findByUsername(String username);
    List<User> findByEmail(String email);

    @Modifying
    @Transactional
    @Query(value = "UPDATE User set " +
            "fullName = :fullName, " +
            "username = :username, " +
            "email = :email, " +
            "enabled = :enabled " +
            "WHERE id = :userId", nativeQuery = true)
    int saveWoPassword(@Param("userId") Integer userId,
                              @Param("fullName") String fullName,
                              @Param("username") String username,
                              @Param("email") String email,
                              @Param("enabled") Boolean enabled
    );

    @Modifying
    @Transactional
    @Query(value = "UPDATE User set " +
            "fullName = :fullName, " +
            "username = :username, " +
            "email = :email, " +
            "password = :password, " +
            "enabled = :enabled " +
            "WHERE id = :userId", nativeQuery = true)
    int saveWithPassword(@Param("userId") Integer userId,
                                @Param("fullName") String fullName,
                                @Param("username") String username,
                                @Param("email") String email,
                                @Param("enabled") Boolean enabled,
                                @Param("password") String password
    );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO User set " +
            "fullName = :fullName, " +
            "username = :username, " +
            "password = :password, " +
            "email = :email, " +
            "FK_createdByUserId = :createdByUserId, " +
            "enabled = :enabled", nativeQuery = true)
    int save(@Param("fullName") String fullName,
                    @Param("username") String username,
                    @Param("password") String password,
                    @Param("email") String email,
                    @Param("enabled") Boolean enabled,
                    @Param("createdByUserId") Integer createdByUserId
    );

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO UserRole SET " +
            "FK_userId = :userId, " +
            "FK_roleId = :roleId", nativeQuery = true)
    int saveRoles(@Param("userId") Integer userId,
                         @Param("roleId") Integer roleId
    );

    @Modifying
    @Transactional
    @Query(value = "DELETE FROM UserRole WHERE FK_userId = :userId", nativeQuery = true)
    int removeRoles(@Param("userId") Integer userId);

    @Transactional(readOnly = true)
    @Query(value = "SELECT " +
                "Role.id, Role.name " +
                "FROM UserRole " +
                "JOIN Role ON UserRole.FK_roleId = Role.id " +
                "WHERE UserRole.FK_userId = :userId", nativeQuery = true)
    List<Object[]> findRolesByUserId(@Param("userId") Integer userId);

    User findByEmailLike(String email);
}
