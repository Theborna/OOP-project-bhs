// package codes;

public class Message {
    public long id, date; // unique identifier and the date the message was sent
    public String message;
    public Image messageImage;// (Optional) null for text based messages
    public long inReplyToUserID; // (Optional) the id of the user that it was a reply to, null for original posts
    public User sender; // user that sent the message
    public Chat chat; // the chat that the message has been sent into
    private int decryptionKey; // used to support end-to-end encryption, not saved in the dataBase

    public Message() {// used when user sends a message
    }
}
