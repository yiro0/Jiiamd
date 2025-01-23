package service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service to handle history operations.
 * Provides methods to add and retrieve history entries.
 *
 * Ensures that history operations are centralized and reusable.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@Service
public class HistoryService {

    private final List<String> history = new ArrayList<>();

    /**
     * Adds a history entry.
     *
     * @param entry the history entry to add
     */
    public void addHistory(String entry) {
        history.add(entry);
    }

    /**
     * Retrieves the history of entries.
     *
     * @return a list of history entries
     */
    public List<String> getHistory() {
        return new ArrayList<>(history);
    }
}