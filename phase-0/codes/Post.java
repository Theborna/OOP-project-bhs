// package codes;

public class Post {
    public long id;// unique identifier for each post
    public long inReplyToPostID; // (Optional) the id of the post that it was a reply to, null for original posts
    public long inReplyToUserID; // (Optional) the id of the user that it was a reply to, null for original posts
    public String text;
    public Image picture; // (Optional) is null for text only posts
    public User sender; // user that posted it
    public int likes;// number of likes on the post
    public int views;// and other stats that show traction and are'nt publicly visible

    public Post() {// used when Users make a Post
    }
    public void validateLikes(){
        //counts likes from the data base and validates number of likes
    }
}
