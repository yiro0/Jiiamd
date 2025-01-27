package service;

import model.User;
import model.History;
import repository.UserRepository;
import repository.HistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import jakarta.annotation.PostConstruct;

/**
 * Service to initialize the database with sample data.
 * @author Bartosz Pałucki
 * @version 6.1
 */
@Service
public class DatabaseInitializationService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @PostConstruct
    public void initializeDatabase() {
        // Create a sample user
        // For testing eetc
        User user = new User("john_doe");
        userRepository.save(user);

        // Create some history records for the user
        // Same as above
        History history1 = new History("Operation1", "Keyword1", "Keyword2", "Sample text 1", "Result 1", user);
        History history2 = new History("Operation2", "Keyword3", "Keyword4", "Sample text 2", "Result 2", user);

        historyRepository.save(history1);
        historyRepository.save(history2);
    }
}
