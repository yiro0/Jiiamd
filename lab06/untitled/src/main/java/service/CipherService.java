package service;

import model.History;
import model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import repository.HistoryRepository;
import repository.UserRepository;

import java.util.List;

@Service
public class CipherService {

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private UserRepository userRepository;

    public String encode(String keyword1, String keyword2, String text) {
        String result = "encodedText";
        User user = userRepository.findByUsername("john_doe");
        historyRepository.save(new History("encode", keyword1, keyword2, text, result, user));
        return result;
    }

    public String decode(String keyword1, String keyword2, String text) {
        // Decoding logic
        String result = "decodedText";
        User user = userRepository.findByUsername("john_doe");
        historyRepository.save(new History("decode", keyword1, keyword2, text, result, user));
        return result;
    }

    public List<History> getHistory() {
        return historyRepository.findAll();
    }

    public User saveUser(User user) {
        return userRepository.save(user);
    }

    public List<User> getUsers() {
        return userRepository.findAll();
    }
}