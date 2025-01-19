package service;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class HistoryService {

    private final List<String> history = new ArrayList<>();

    public List<String> getHistory() {
        return new ArrayList<>(history);
    }

    public void addHistory(String entry) {
        history.add(entry);
    }
}