// package com.electro.phase1.controllers;

// import java.util.Collection;
// import java.util.HashMap;
// import java.util.Map;
// import com.electro.phase1.models.node.Chat;
// import com.electro.phase1.models.node.Message;
// import com.electro.phase1.models.node.user.User;
// import static com.electro.phase1.util.StdOut.*;

// public class ForwardController extends ChatListController {

//     public Map<Chat, Boolean> selections;
//     private Collection<Message> messages;

//     // @Override
//     // public void parse(String input) throws changeViewException {
//     // input = input.toLowerCase().trim();
//     // switch (input) {
//     // case "n":
//     // case "next":
//     // case "scroll down":
//     // case "down":
//     // case "d":
//     // currentChat = chatItems.get((++current < chatItems.size()) ? current :
//     // (current = 0));
//     // break;
//     // case "l":
//     // case "last":
//     // case "scroll up":
//     // case "up":
//     // case "u":
//     // currentChat = chatItems.get((--current >= 0) ? current : (current = 0));
//     // break;
//     // case "t":
//     // case "top":
//     // currentChat = chatItems.get(0);
//     // break;
//     // case "all":
//     // case "show -all":
//     // showAll();
//     // break;
//     // case "select":
//     // select(currentChat.getChat());
//     // break;
//     // case "deselect":
//     // deselect(currentChat.getChat());
//     // break;
//     // case "confirm":
//     // confirm();
//     // App.setView(ChatListView.getInstance());
//     // break;
//     // case "help":
//     // help();
//     // break;
//     // default:
//     // printError("no such command");
//     // break;
//     // }
//     // }

//     private void confirm() {// indeed a mouth-full
//         for (Chat chat : selections.keySet())
//             if (isSelected(chat))
//                 for (Message msg : messages)
//                     User.getCurrentUser().sendMessage(msg.forwardFrom(User.getCurrentUser()), chat);
//     }

//     private void deselect(Chat currentChat) {
//         if (!isSelected(currentChat))
//             printError("chat not selected!");
//         else
//             selections.put(currentChat, false);
//     }

//     private void select(Chat currentChat) {
//         if (isSelected(currentChat))
//             printError("already selected!");
//         else
//             selections.put(currentChat, true);
//     }

//     @Override
//     public void addAll(Collection<Chat> chats) {
//         super.addAll(chats);
//         selections = new HashMap<>();
//         for (Chat chat : chats)
//             selections.put(chat, false);
//     }

//     @Override
//     public void help() {
//         // TODO implement help method
//     }

//     public void setMessages(Collection<Message> messages) {
//         this.messages = messages;
//     }

//     public boolean isSelected(Chat currentChat) {
//         return selections.get(currentChat);
//     }

// }
