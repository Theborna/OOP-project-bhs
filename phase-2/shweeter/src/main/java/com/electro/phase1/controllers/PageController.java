// package com.electro.phase1.controllers;

// import com.electro.phase1.models.node.user.User;

// import static com.electro.phase1.util.StdOut.*;

// public class PageController extends FeedController {

//     private boolean needInfo;

//     // @Override
//     // public void parse(String input) throws changeViewException {
//     // input = input.toLowerCase().trim();
//     // if (needInfo)
//     // needInfo = false;
//     // switch (input) {
//     // case "info":
//     // needInfo = true;
//     // break;
//     // case "follow":
//     // if (PageView.getInstance().isFollows())
//     // printError("already following!");
//     // else
//     // User.getCurrentUser().follow(PageView.getInstance().getUser());
//     // break;
//     // case "un follow":
//     // case "un":
//     // if (!PageView.getInstance().isFollows())
//     // printError("not following!");
//     // else
//     // User.getCurrentUser().unfollow(PageView.getInstance().getUser());
//     // break;
//     // default:
//     // super.parse(input);
//     // break;
//     // }
//     // }

//     // public int isContinue(String input) throws changeViewException {
//     // input = input.toLowerCase().trim();
//     // switch (input) {
//     // case "yes":
//     // case "y":
//     // return 1;
//     // case "no":
//     // case "n":
//     // App.setView(App.lastView());
//     // return 2;
//     // default:
//     // return 3;
//     // }
//     // }

//     public boolean isNeedInfo() {
//         return needInfo;
//     }
// }
