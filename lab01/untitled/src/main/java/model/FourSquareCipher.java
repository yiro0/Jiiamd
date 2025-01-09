package model;

public class FourSquareCipher {
    private String keyword1;
    private String keyword2;

    public FourSquareCipher(String keyword1, String keyword2) {
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
    }

    public String encode(String text) {
        // Simplified encoding logic for demonstration
        return text.toUpperCase().replaceAll("[^A-Z]", "");
    }
}