package controller;

import model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CipherService;

import java.util.List;

@RestController
@RequestMapping("/cipher")
public class EncodeDecodeController {

    @Autowired
    private CipherService cipherService;

    @PostMapping("/encode")
    public String encode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text) {
        return "Encoded Text: " + cipherService.encode(keyword1, keyword2, text);
    }

    @PostMapping("/decode")
    public String decode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text) {
        return "Decoded Text: " + cipherService.decode(keyword1, keyword2, text);
    }

    @GetMapping("/history")
    public List<History> getHistory() {
        return cipherService.getHistory();
    }
}