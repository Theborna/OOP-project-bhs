// package codes;

public abstract class User {
    public String username, firstName;
    public boolean isPublic;

    public void sendMessage(Message message, Chat chat) {
        // will send message to the desired chat
    }

    public abstract void Post(Post post);

    public void likePost(Post post){ // adds user to the likes DB
    }

    public void unlikePost(Post post){ // removes user from likes DB
    }

}
