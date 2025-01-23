package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import service.HistoryService;

import java.util.List;

/**
 * Controller to handle history-related requests.
 * Retrieves the history of encoded and decoded texts.
 *
 * Ensures that the client is informed about the history of operations.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@RestController
public class HistoryController {

    private final HistoryService historyService;

    /**
     * Constructs a HistoryController with the specified HistoryService.
     *
     * @param historyService the HistoryService to use for history operations
     */
    @Autowired
    public HistoryController(HistoryService historyService) {
        this.historyService = historyService;
    }

    /**
     * Retrieves the history of encoded and decoded texts.
     *
     * @return a list of history entries
     */
    @GetMapping("/history")
    public List<String> getHistory() {
        return historyService.getHistory();
    }
}