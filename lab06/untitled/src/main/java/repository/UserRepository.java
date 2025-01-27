package repository;

import model.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing User entities.
 * @author Bartosz Pałucki
 * @version 6.1
 */
public interface UserRepository extends JpaRepository<User, Long> {
    User findByUsername(String username);
}