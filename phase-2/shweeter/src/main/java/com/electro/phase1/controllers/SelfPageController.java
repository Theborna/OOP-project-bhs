// package com.electro.phase1.controllers;

// // import com.electro.phase1.App;
// // import com.electro.phase1.util.exception.changeViewException;
// // import com.electro.phase1.view.model.SettingsView;

// public class SelfPageController extends FeedController {
//     private boolean needInfo;

//     // @Override
//     // public void parse(String input) throws changeViewException {
//     //     input = input.toLowerCase().trim();
//     //     if (needInfo)
//     //         needInfo = false;
//     //     switch (input) {
//     //         case "info":
//     //             needInfo = true;
//     //             break;
//     //         case "settings":
//     //             App.setView(SettingsView.getInstance());
//     //             break;
//     //         default:
//     //             super.parse(input);
//     //             break;
//     //     }
//     // }

//     // public int isContinue(String input) throws changeViewException {
//     //     input = input.toLowerCase().trim();
//     //     switch (input) {
//     //         case "yes":
//     //         case "y":
//     //             return 1;
//     //         case "no":
//     //         case "n":
//     //             App.setView(App.lastView());
//     //             return 2;
//     //         default:
//     //             return 3;
//     //     }
//     // }

//     // public boolean isNeedInfo() {
//     //     return needInfo;
//     // }
// }
