package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/history")
public class HistoryController {

    // Placeholder history storage (can be replaced with a proper database or in-memory solution)
    private final List<String> history = new ArrayList<>();

    @GetMapping
    public void getHistory(HttpServletResponse response) throws IOException {
        // Mock history data
        history.add("Encoded: HELLO -> WXYZA");
        history.add("Decoded: WXYZA -> HELLO");

        response.setContentType("text/plain");

        if (history.isEmpty()) {
            response.getWriter().write("No history available.");
        } else {
            for (String entry : history) {
                response.getWriter().write(entry + "\n");
            }
        }
    }
}