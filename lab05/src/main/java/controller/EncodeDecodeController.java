package controller;

import logic.FourSquareCipher;
import service.HistoryService;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/cipher")
public class EncodeDecodeController {

    private final HistoryService historyService;

    // Constructor injection of HistoryService
    public EncodeDecodeController(HistoryService historyService) {
        this.historyService = historyService;
    }

    @PostMapping("/encode")
    public String encodePost(
            @RequestParam(required = false) String keyword1,
            @RequestParam(required = false) String keyword2,
            @RequestParam(required = false) String text) {
        return encode(keyword1, keyword2, text);
    }

    @GetMapping("/encode")
    public String encodeGet(
            @RequestParam(required = false) String keyword1,
            @RequestParam(required = false) String keyword2,
            @RequestParam(required = false) String text) {
        return encode(keyword1, keyword2, text);
    }

    @PostMapping("/decode")
    public String decodePost(
            @RequestParam(required = false) String keyword1,
            @RequestParam(required = false) String keyword2,
            @RequestParam(required = false) String text) {
        return decode(keyword1, keyword2, text);
    }

    @GetMapping("/decode")
    public String decodeGet(
            @RequestParam(required = false) String keyword1,
            @RequestParam(required = false) String keyword2,
            @RequestParam(required = false) String text) {
        return decode(keyword1, keyword2, text);
    }

    @GetMapping("/history")
    public List<String> getHistory() {
        return historyService.getHistory();
    }

    private String encode(String keyword1, String keyword2, String text) {
        if (keyword1 == null || keyword2 == null || text == null) {
            throw new NullPointerException("Missing required parameters.");
        }

        try {
            FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
            String encodedText = "Encoded Text: " + cipher.encode(text);
            historyService.addHistory(encodedText);  // Add encoded text to history
            return encodedText;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid parameter value.");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred.");
        }
    }

    private String decode(String keyword1, String keyword2, String text) {
        if (keyword1 == null || keyword2 == null || text == null) {
            throw new NullPointerException("Missing required parameters.");
        }

        try {
            FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
            String decodedText = "Decoded Text: " + cipher.decode(text);
            historyService.addHistory(decodedText);  // Add decoded text to history
            return decodedText;
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException("Invalid parameter value.");
        } catch (Exception e) {
            throw new RuntimeException("Unexpected error occurred.");
        }
    }
}