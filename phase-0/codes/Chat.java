// package codes;

public class Chat {
    public long id;// unique identifier
    public String type;// can be pv/group or channel
    public String name; // name displayed on top of chat
    public Image chatPhoto;// picture displayed for chat

    public void addToGroup(User user){// adds user to the chat
    }

    public void removeFromGroup(User user){// removes user from chat
    }
    
    public void changePermissions(User user){// handles permissions, might take extra input
    }
}
