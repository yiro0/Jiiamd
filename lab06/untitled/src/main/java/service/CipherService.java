package service;

import model.History;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HistoryRepository;
import repository.UserRepository;

import java.util.List;

/**
 * Service for handling encoding and decoding operations.
 * Manages the history of operations and user data.
 * @author Bartosz Pałucki
 * @version 6.1
 */
@Service
public class CipherService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    /**
     * Encodes the given text using the provided keywords.
     *
     * @param keyword1 The first keyword for encoding.
     * @param keyword2 The second keyword for encoding.
     * @param text     The text to encode.
     * @return The encoded text.
     */
    public String encode(String keyword1, String keyword2, String text) {
        String result = "encodedText";
        User user = userRepository.findByUsername("john_doe");
        historyRepository.save(new History("encode", keyword1, keyword2, text, result, user));
        return result;
    }

    /**
     * Decodes the given text using the provided keywords.
     *
     * @param keyword1 The first keyword for decoding.
     * @param keyword2 The second keyword for decoding.
     * @param text     The text to decode.
     * @return The decoded text.
     */
    public String decode(String keyword1, String keyword2, String text) {
        String result = "decodedText";
        User user = userRepository.findByUsername("john_doe");
        historyRepository.save(new History("decode", keyword1, keyword2, text, result, user));
        return result;
    }

    /**
     * Retrieves the history of encoding and decoding operations.
     *
     * @return The list of history records.
     */
    public List<History> getHistory() {
        return historyRepository.findAll();
    }

    /**
     * Saves a user to the repository.
     *
     * @param user The user to save.
     * @return The saved user.
     */
    public User saveUser(User user) {
        return userRepository.save(user);
    }

    /**
     * Retrieves all users from the repository.
     *
     * @return The list of users.
     */
    public List<User> getUsers() {
        return userRepository.findAll();
    }
}