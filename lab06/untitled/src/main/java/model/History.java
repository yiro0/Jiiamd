package model;

import jakarta.persistence.*;

/**
 * Entity class representing a history entry of operations.
 * @author Bartosz Pałucki
 * @version 6.1
 */
@Entity
public class History {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String operation;
    private String keyword1;
    private String keyword2;
    private String text;
    private String result;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User user;

    public History() {}

    public History(String operation, String keyword1, String keyword2, String text, String result, User user) {
        this.operation = operation;
        this.keyword1 = keyword1;
        this.keyword2 = keyword2;
        this.text = text;
        this.result = result;
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public String getOperation() {
        return operation;
    }

    public String getKeyword1() {
        return keyword1;
    }

    public String getKeyword2() {
        return keyword2;
    }

    public String getText() {
        return text;
    }

    public String getResult() {
        return result;
    }

    public User getUser() {
        return user;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setOperation(String operation) {
        this.operation = operation;
    }

    public void setKeyword1(String keyword1) {
        this.keyword1 = keyword1;
    }

    public void setKeyword2(String keyword2) {
        this.keyword2 = keyword2;
    }

    public void setText(String text) {
        this.text = text;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "History{" +
                "id=" + id +
                ", operation='" + operation + '\'' +
                ", keyword1='" + keyword1 + '\'' +
                ", keyword2='" + keyword2 + '\'' +
                ", text='" + text + '\'' +
                ", result='" + result + '\'' +
                ", user=" + user +
                '}';
    }
}
