package controller;

import model.User;

import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;

public class JsonHandler {

    static JsonHandler instance = null;

    public static JsonHandler getInstance() {
        if (instance == null) instance = new JsonHandler();
        return instance;
    }

    public void addUserToJson(User user){
        try {
            FileWriter fileWriter = new FileWriter("users.txt",true);
            PrintWriter printWriter = new PrintWriter(fileWriter);
            printWriter.println(user.toString());  //New line
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void deleteUserFromJson(String userString){

    }

}
