/*
The exceptions are handled by the ErrorController, which sends appropriate error responses to the client.
This approach is used instead of HttpServletResponse.sendError to centralize error handling and separate concerns.

Since there was an issue with the dependency injection of the HttpServletResponse in the ErrorController, the ResponseEntity class was used instead.
This controller is responsible for handling encoding and decoding requests using the FourSquareCipher.

It also ensures that the client is informed about incorrect application operations by using exceptions.
It is implemented the same way as the model functionality error handling.
*/
package controller;

import logic.FourSquareCipher;
import service.HistoryService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Controller to handle encoding and decoding requests using the FourSquareCipher.
 * Also handles history retrieval.
 *
 * Exceptions are handled by the ErrorController, which sends appropriate error responses to the client.
 * This approach is used instead of HttpServletResponse.sendError to centralize error handling and separate concerns.
 *
 * Since there was an issue with the dependency injection of the HttpServletResponse in the ErrorController, the ResponseEntity class was used instead.
 *
 * This controller ensures that the client is informed about incorrect application operations by using exceptions.
 * It is implemented the same way as the model functionality error handling.
 *
 * @author Bartosz Pałucki
 * @version 5.1
 */
@RestController
@RequestMapping("/cipher")
public class EncodeDecodeController {

    private final HistoryService historyService;

    /**
     * Constructs an EncodeDecodeController with the specified HistoryService.
     *
     * @param historyService the HistoryService to use for history operations
     */
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
        validateInput(keyword1, keyword2, text);
        FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
        String encodedText = "Encoded Text: " + cipher.encode(text);
        historyService.addHistory(encodedText);
        return encodedText;
    }

    private String decode(String keyword1, String keyword2, String text) {
        validateInput(keyword1, keyword2, text);
        FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
        String decodedText = "Decoded Text: " + cipher.decode(text);
        historyService.addHistory(decodedText);
        return decodedText;
    }

    private void validateInput(String keyword1, String keyword2, String text) {
        if (keyword1 == null || keyword2 == null || text == null) {
            throw new NullPointerException("One or more input parameters are missing.");
        }
        if (!keyword1.matches("[A-Za-z]+") || !keyword2.matches("[A-Za-z]+") || !text.matches("[A-Za-z]+")) {
            throw new IllegalArgumentException("Input values must contain only letters.");
        }
    }
}