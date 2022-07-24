package com.project.util;

import java.util.Comparator;

import com.project.models.node.Message;
import com.project.models.node.TextBased;
import com.project.models.node.node;
import com.project.models.node.post.Post;

public class TextSort<E extends node & TextBased> implements Comparator<E> {

    public static final TextSort<Post> INSTANCE_POST = new TextSort<>();
    public static final TextSort<Message> INSTANCE_MESSAGE = new TextSort<>();

    private boolean contains(String s, String t) {
        String[] parts = s.split(" ");
        for (String string : parts)
            if (t.contains(string))
                return true;
        return false;
    }

    @Override
    public int compare(E arg0, E arg1) { // only care about positive values
        if (arg0.getText() == null)
            return -1;
        if (arg1.getText() == null)
            return -1;
        if (!contains(arg0.getText(), arg1.getText()))
            return -1;
        return arg0.getLastModifiedDate().compareTo(arg1.getLastModifiedDate());
    }
}
