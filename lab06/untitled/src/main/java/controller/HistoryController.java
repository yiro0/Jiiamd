package controller;

import model.History;
import model.User;
import repository.HistoryRepository;
import repository.UserRepository;
import service.CipherService;
import exception.InvalidInputException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.GetMapping;
import utils.InputValidator;

/**
 * Controller for handling history-related requests.
 * Provides endpoints for adding history entries and retrieving history records.
 * @author Bartosz Pałucki
 * @version 6.1
 */

@Controller
public class HistoryController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HistoryRepository historyRepository;

    @Autowired
    private CipherService cipherService;

    /**
     * Handles the form submission to add a new history entry.
     *
     * @param username  The username to associate with the history.
     * @param operation The operation performed.
     * @param keyword1  The first keyword.
     * @param keyword2  The second keyword.
     * @param text      The text input by the user.
     * @param model     The model to send data to the view.
     * @return The view name.
     * @throws InvalidInputException If the input contains non-Latin characters.
     */
    @PostMapping("/addHistory")
    public String addHistory(@RequestParam String username, @RequestParam String operation,
                             @RequestParam String keyword1, @RequestParam String keyword2,
                             @RequestParam String text, Model model) throws InvalidInputException {

        if (!InputValidator.isValidLatin(operation) || !InputValidator.isValidLatin(keyword1) ||
                !InputValidator.isValidLatin(keyword2) || !InputValidator.isValidLatin(text)) {
            throw new InvalidInputException("Input contains invalid characters. Only Latin characters are allowed.");
        }

        User user = userRepository.findByUsername(username);
        if (user == null) {
            model.addAttribute("error", "User not found.");
            return "error";
        }

        History history = new History(operation, keyword1, keyword2, text, "Success", user);
        historyRepository.save(history);

        model.addAttribute("message", "History record added successfully.");
        return "success";
    }

    /**
     * Displays the history records.
     *
     * @param model The model to send data to the view.
     * @return The view name.
     */
    @GetMapping("/history")
    public String getHistory(Model model) {
        Iterable<History> histories = historyRepository.findAll();
        model.addAttribute("histories", histories);
        return "history";
    }
}