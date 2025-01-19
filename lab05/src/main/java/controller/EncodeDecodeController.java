package controller;

import logic.FourSquareCipher;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestController
@RequestMapping("/cipher")
public class EncodeDecodeController {

    @PostMapping("/encode")
    public void encode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text,
            HttpServletResponse response) throws IOException {
        try {
            FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
            String encodedText = cipher.encode(text);

            response.setContentType("text/plain");
            response.getWriter().write("Encoded Text: " + encodedText);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error occurred.");
        }
    }

    @PostMapping("/decode")
    public void decode(
            @RequestParam String keyword1,
            @RequestParam String keyword2,
            @RequestParam String text,
            HttpServletResponse response) throws IOException {
        try {
            FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
            String decodedText = cipher.decode(text);

            response.setContentType("text/plain");
            response.getWriter().write("Decoded Text: " + decodedText);
        } catch (IllegalArgumentException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Error: " + e.getMessage());
        } catch (Exception e) {
            response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error occurred.");
        }
    }
}