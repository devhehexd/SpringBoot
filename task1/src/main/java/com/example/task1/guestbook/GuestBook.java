package com.example.task1.guestbook;

import java.util.Date;

//VO
public class GuestBook {

    private Integer num;
    private String writer;
    private String password;
    private Date wdate;
    private String content;

    public GuestBook() {

    }

    public GuestBook(Integer num, String writer, String password, Date wdate, String content) {
        this.num = num;
        this.writer = writer;
        this.password = password;
        this.wdate = wdate;
        this.content = content;
    }

    public String getWriter() {
        return writer;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    public Date getWdate() {
        return wdate;
    }

    public void setWdate(Date wdate) {
        this.wdate = wdate;
    }
}
