package com.project.controllers;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import com.project.models.connection.UserFollowingConnection;
import com.project.models.node.post.Post;
import com.project.models.node.user.User;
import com.project.util.PostScore;
import com.project.util.Suggestion;
import com.project.util.exception.changeViewException;

public class ExploreController extends FeedController {

    @Override
    protected void dislike() {
        super.dislike();
    }

    @Override
    protected void like() {
        super.like();
    }

}
