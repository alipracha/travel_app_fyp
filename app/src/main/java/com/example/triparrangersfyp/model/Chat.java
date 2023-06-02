package com.example.triparrangersfyp.model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Chat {

    @SerializedName("Chat_ID")
    @Expose
    private int Chat_ID;

    @SerializedName("Chat_Message")
    @Expose
    private String Chat_Message;

    @SerializedName("Chat_SenderID")
    @Expose
    private int Chat_SenderID;

    @SerializedName("Chat_ReceiverID")
    @Expose
    private int Chat_ReceiverID;

    @SerializedName("sender_type")
    @Expose
    private String sender_type;

    @SerializedName("Trip_ID")
    @Expose
    private int Trip_ID;

    @SerializedName("Imagee")
    @Expose
    private String Imagee;

    @SerializedName("code")
    @Expose
    private int code;

    @SerializedName("error")
    @Expose
    private boolean error;

    @SerializedName("message")
    @Expose
    private String message;

    public Chat() {
    }

    public Chat(int chat_ID, String chat_Message, int chat_SenderID, int chat_ReceiverID, String sender_type,
                String imagee, int trip_ID) {
        Chat_ID = chat_ID;
        Chat_Message = chat_Message;
        Chat_SenderID = chat_SenderID;
        Chat_ReceiverID = chat_ReceiverID;
        this.sender_type = sender_type;
        Trip_ID = trip_ID;
        Imagee = imagee;
    }

    public int getChat_ID() {
        return Chat_ID;
    }

    public void setChat_ID(int chat_ID) {
        Chat_ID = chat_ID;
    }

    public String getChat_Message() {
        return Chat_Message;
    }

    public void setChat_Message(String chat_Message) {
        Chat_Message = chat_Message;
    }

    public int getChat_SenderID() {
        return Chat_SenderID;
    }

    public void setChat_SenderID(int chat_SenderID) {
        Chat_SenderID = chat_SenderID;
    }

    public int getChat_ReceiverID() {
        return Chat_ReceiverID;
    }

    public void setChat_ReceiverID(int chat_ReceiverID) {
        Chat_ReceiverID = chat_ReceiverID;
    }

    public String getSender_type() {
        return sender_type;
    }

    public void setSender_type(String sender_type) {
        this.sender_type = sender_type;
    }

    public int getTrip_ID() {
        return Trip_ID;
    }

    public void setTrip_ID(int trip_ID) {
        Trip_ID = trip_ID;
    }


    public boolean isError() {
        return error;
    }

    public void setError(boolean error) {
        this.error = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getImagee() {
        return Imagee;
    }

    public void setImagee(String imagee) {
        Imagee = imagee;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }
}


