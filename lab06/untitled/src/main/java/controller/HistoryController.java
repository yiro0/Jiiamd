package controller;

import model.History;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final EncodeDecodeController encodeDecodeController;

    public HistoryController(EncodeDecodeController encodeDecodeController) {
        this.encodeDecodeController = encodeDecodeController;
    }

    @GetMapping
    public List<String> getHistory() {
        List<History> historyList = encodeDecodeController.getHistory();
        return historyList.stream()
                .map(history -> history.getOperation() + " - " + history.getKeyword1() + ", " + history.getKeyword2() + ": " + history.getText() + " -> " + history.getResult())
                .collect(Collectors.toList());
    }
}