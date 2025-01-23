package controller;

import logic.FourSquareCipher;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/cipher")
public class EncodeDecodeController {

    private final List<String> history = new ArrayList<>();

    @PostMapping("/encode")
    public String encode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text) {
        try {
            FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
            String encodedText = "Encoded Text: " + cipher.encode(text);
            history.add(encodedText);
            return encodedText;
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error occurred.";
        }
    }

    @PostMapping("/decode")
    public String decode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text) {
        try {
            FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
            String decodedText = "Decoded Text: " + cipher.decode(text);
            history.add(decodedText);
            return decodedText;
        } catch (IllegalArgumentException e) {
            return "Error: " + e.getMessage();
        } catch (Exception e) {
            return "Unexpected error occurred.";
        }
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return history;
    }
}