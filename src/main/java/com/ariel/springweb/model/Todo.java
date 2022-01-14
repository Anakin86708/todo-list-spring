package com.ariel.springweb.model;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.util.Date;
import java.util.Objects;

public class Todo {
    public static final int MAX_DESC = 128;

    private int id;
    private String user;
    @NotEmpty(message = "Cannot be empty")
    @NotBlank(message = "Cannot be blank")
    @Size(max = MAX_DESC, message = "Maximum of " + MAX_DESC + " characters!")
    private String description;
    private Date targetDate;
    private boolean done;

    public Todo() {

    }

    public Todo(int id, String user, String description, Date targetDate, boolean done) {
        this.id = id;
        this.user = user;
        this.description = description;
        this.targetDate = targetDate;
        this.done = done;
    }

    @Override
    public String toString() {
        return "Todo{" +
                "id=" + id +
                ", user='" + user + '\'' +
                ", description='" + description + '\'' +
                ", targetDate=" + targetDate +
                ", isDone=" + done +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getTargetDate() {
        return targetDate;
    }

    public void setTargetDate(Date targetDate) {
        this.targetDate = targetDate;
    }

    public boolean isDone() {
        return done;
    }

    public void setDone(boolean done) {
        this.done = done;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Todo todo = (Todo) o;
        return id == todo.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
