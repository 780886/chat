package com.wgq.chat.execption;

public class BusinessException extends Exception {



    private String message;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String toString() {
        return message;
    }

    public static void main(String[] args) {
        try {
            throw new Exception();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}
