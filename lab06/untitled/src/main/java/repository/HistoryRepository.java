package repository;

import model.History;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repository interface for managing History entities.
 * @author Bartosz Pałucki
 * @version 6.1
 */
public interface HistoryRepository extends JpaRepository<History, Long> {
    // This line gets edited numerous times during development
    // TODO: delete lines belkow
}