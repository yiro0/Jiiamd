package service;

import logic.FourSquareCipher;
import model.History;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CipherService {
    private final List<History> historyList = new ArrayList<>();

    public String encode(String keyword1, String keyword2, String text) {
        FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
        String result = cipher.encode(text);
        historyList.add(new History("encode", keyword1, keyword2, text, result));
        return result;
    }

    public String decode(String keyword1, String keyword2, String text) {
        FourSquareCipher cipher = new FourSquareCipher(keyword1, keyword2, true);
        String result = cipher.decode(text);
        historyList.add(new History("decode", keyword1, keyword2, text, result));
        return result;
    }

    public List<History> getHistory() {
        return historyList;
    }
}