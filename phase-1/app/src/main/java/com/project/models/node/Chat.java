package com.project.models.node;

import java.util.ArrayList;

import com.project.models.node.user.User;

public class Chat extends node {
    private int participantNum;
    private ArrayList<User> participants;
    private ArrayList<Message> messages;
    private ArrayList<User> administrators;

    public Chat(ArrayList<User> participants) {

    }
}
