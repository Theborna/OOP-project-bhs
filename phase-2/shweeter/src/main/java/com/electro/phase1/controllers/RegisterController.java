package com.electro.phase1.controllers;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import com.electro.phase1.AppRegex;
import com.electro.phase1.enums.Security;
import com.electro.phase1.models.node.ImageNode;
import com.electro.phase1.models.node.Media;
import com.electro.phase1.models.node.user.BusinessUser;
import com.electro.phase1.models.node.user.NormalUser;
import com.electro.phase1.models.node.user.User;
import com.electro.phase1.util.crypt;
import com.electro.views.component.ErrorNotification;

public class RegisterController extends LoginController {

    // @Override
    // public void parse(String input) {
    // }
    private Security securityQ;
    private String securityAns;
    private String email, fullName;
    private Media pfp;

    public RegisterController() {
        super();
        email = null;
        fullName = null;
    }

    public String getRepeatPassword(String nextLine, String password) {
        if (nextLine.equals(password))
            return password;
        return null;
    }

    public String getFullName(String nextLine) {
        if (nextLine.matches("\\S+") && nextLine.length() <= 30)
            return nextLine;
        return null;
    }

    public java.util.Date getBirthDate(String input) {
        if (!AppRegex.DATE.matches(input))
            return null;
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            return format.parse(input);
        } catch (ParseException e) {
            return null;
        }
    }

    public boolean setEmail(String email) {
        if (getEmail(email) == null) {
            if (email.length() != 0)
                new ErrorNotification("invalid email format: " + email);
            return false;
        }
        this.email = email;
        return true;
    }

    public String getType(String string) {
        if (string.equals("normal") || string.equals("business"))
            return string;
        return null;
    }

    public String getVisibility(String string) {
        if (string.equals("public") || string.equals("private"))
            return string;
        return null;
    }

    public String getEmail(String string) {
        if (AppRegex.EMAIL.matches(string))
            return string;
        return null;
    }

    public void makeAccount(boolean isPublic, boolean isNormal, Date birthDate) {
        User user;
        String salt = crypt.salt();
        password = crypt.encryptedString(password + salt);
        if (isNormal)
            user = new NormalUser(username, password);
        else
            user = new BusinessUser(username, password);
        user.setPublic(isPublic).setBirthDate(birthDate).setSecAns(crypt.encryptedString(securityAns + salt));
        user.setSecType(securityQ);
        user.setEmail(email);
        user.setName(fullName);
        user.setSalt(salt);
        user.setProfilePhoto(pfp);
        user.sendToDB(); // TODO: update controller
    }

    public boolean getSecurityQuestion(String nextLine) {
        nextLine = nextLine.trim().toLowerCase();
        if (nextLine.length() < 2) {
            new ErrorNotification("security answer cannot be empty");
            return false;
        }
        securityAns = nextLine;
        return true;
    }

    public void setSecurityQ(Security securityQ) {
        this.securityQ = securityQ;
    }

    public String getSecurityAns() {
        return securityAns;
    }

    public boolean setName(String text) {
        if (!AppRegex.FULL_NAME.matches(text))
            return false;
        fullName = text;
        return true;
    }

    public void setPfp(File pfp) {
        if (pfp == null)
            return;
        this.pfp = new ImageNode(pfp.getPath());
    }
}
