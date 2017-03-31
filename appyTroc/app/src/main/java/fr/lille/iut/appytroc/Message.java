package fr.lille.iut.appytroc;

/**
 * Created by thibault on 30/03/2017.
 */

public class Message {
    private String message;


    public Message(){};
    public Message(String message){
        this.message= message;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
