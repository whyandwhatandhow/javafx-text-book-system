package com.example.textbooksystem.Domain;

import java.util.List;

/**
 * @author 叶鸣镝
 * <p>
 * description:
 */

public class Book {
    private int book_id;
    private String book_name;
    private String publish;
    private int user_id;

    public Book() {
    }

    public Book(int book_id, String book_name, String publish, int user_id) {
        this.book_id = book_id;
        this.book_name = book_name;
        this.publish = publish;
        this.user_id = user_id;
    }

    public int getBook_id() {
        return book_id;
    }

    public void setBook_id(int book_id) {
        this.book_id = book_id;
    }

    public String getBook_name() {
        return book_name;
    }

    public void setBook_name(String book_name) {
        this.book_name = book_name;
    }

    public String getPublish() {
        return publish;
    }

    public void setPublish(String publish) {
        this.publish = publish;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }


}
