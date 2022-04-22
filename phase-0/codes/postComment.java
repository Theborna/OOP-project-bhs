// package codes;

public class postComment {
    public long id, date; // unique identifier and the date the message was sent
    public String message;
    public Image commentImage;// (Optional) null for text based comments
    public User sender; // user that sent the message
    public Post post; // the chat that the message has been sent into

    public postComment() {
        // used when user makes a comment
    }
}
