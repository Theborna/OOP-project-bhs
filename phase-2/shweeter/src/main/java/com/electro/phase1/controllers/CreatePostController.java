// package com.electro.phase1.controllers;

// import com.electro.phase1.util.StdIn;
// import com.electro.phase1.util.exception.changeViewException;

// public class CreatePostController implements Controller {

//     @Override
//     public void parse(String input) {// unnecessary
//     }

//     public boolean getPostText(StringBuilder postText) throws changeViewException {
//         if (postText == null)
//             postText = new StringBuilder();
//         String input;
//         while (!(input = StdIn.nextLine()).equals("")) {
//             if (input.equals("-done"))
//                 break;
//             postText.append(input + "\n");
//         }
//         return true;
//     }

//     @Override
//     public void help() {
//         // no help needed
//     }

// }
