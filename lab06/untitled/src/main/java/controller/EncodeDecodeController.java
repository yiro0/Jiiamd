package controller;

import model.History;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import service.CipherService;

import java.util.List;

/**
 * Controller for handling encoding and decoding requests.
 * Provides endpoints for encoding, decoding, and retrieving history.
 * @author Bartosz Pałucki
 * @version 6.1
 */
@RestController
@RequestMapping("/cipher")
public class EncodeDecodeController {

    @Autowired
    private CipherService cipherService;

    /**
     * Endpoint for encoding text.
     *
     * @param keyword1 The first keyword for encoding.
     * @param keyword2 The second keyword for encoding.
     * @param text     The text to encode.
     * @return The encoded text.
     */
    @PostMapping("/encode")
    public String encode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text) {
        return "Encoded Text: " + cipherService.encode(keyword1, keyword2, text);
    }

    /**
     * Endpoint for decoding text.
     *
     * @param keyword1 The first keyword for decoding.
     * @param keyword2 The second keyword for decoding.
     * @param text     The text to decode.
     * @return The decoded text.
     */
    @PostMapping("/decode")
    public String decode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text) {
        return "Decoded Text: " + cipherService.decode(keyword1, keyword2, text);
    }

    /**
     * Endpoint for retrieving the history of encoding and decoding operations.
     *
     * @return The list of history records.
     */
    @GetMapping("/history")
    public List<History> getHistory() {
        return cipherService.getHistory();
    }
}