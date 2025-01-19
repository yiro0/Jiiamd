package controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Controller
public class ErrorController {

    @ExceptionHandler(IllegalArgumentException.class)
    @RequestMapping("/error/illegalArgument")
    @ResponseBody
    public String handleIllegalArgumentError(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid parameter value.");
        return null;
    }

    @ExceptionHandler(NullPointerException.class)
    @RequestMapping("/error/nullPointer")
    @ResponseBody
    public String handleNullPointerError(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing required parameters.");
        return null;
    }

    @ExceptionHandler(Exception.class)
    @RequestMapping("/error/unexpected")
    @ResponseBody
    public String handleUnexpectedError(HttpServletResponse response) throws IOException {
        response.sendError(HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Unexpected error occurred.");
        return null;
    }
}
