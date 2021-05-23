package com.amit.rest.webservices.restfulwebservices.helloworld;

public class HelloWorldBean {
    public String message;
    public int statusCode;

    public HelloWorldBean(String message, int statusCode) {
        this.message = message;
        this.statusCode = statusCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(int statusCode) {
        this.statusCode = statusCode;
    }

    @Override
    public String toString() {
        return "HelloWorldBean{" +
                "message='" + message + '\'' +
                ", statusCode=" + statusCode +
                '}';
    }
}
