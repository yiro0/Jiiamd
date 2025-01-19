package controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    private final EncodeDecodeController encodeDecodeController;

    public HistoryController(EncodeDecodeController encodeDecodeController) {
        this.encodeDecodeController = encodeDecodeController;
    }

    @GetMapping
    public List<String> getHistory() {
        return encodeDecodeController.getHistory();
    }
}