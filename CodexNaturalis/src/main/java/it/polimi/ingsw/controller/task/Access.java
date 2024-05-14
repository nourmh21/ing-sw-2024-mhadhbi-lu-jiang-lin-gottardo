package it.polimi.ingsw.controller.task;

import it.polimi.ingsw.controller.GameController;
import it.polimi.ingsw.message.error.ErrorMessage;
import it.polimi.ingsw.message.general.LoginSuccessMessage;
import it.polimi.ingsw.message.Message;

import java.io.*;
import java.util.Scanner;


import static it.polimi.ingsw.message.enums.ErrorType.CREDENTIAL_WRONG;
import static it.polimi.ingsw.message.enums.ErrorType.NICKNAME_ALREADY_EXIST;

/*
  Comment about this class implementation status:
  I almost completely ignored cyber-security issues
  I didn't try to found an efficient algorithm to find content in a file <- Should I do it?
 */
public class Access implements Runnable{
    private String nickname;
    private String pwd;
    private Boolean isRegistered;
    private ObjectOutputStream oos;

    public Access(String nickname, String pwd, Boolean isRegistered, ObjectOutputStream oos){
        this.nickname = nickname;
        this.pwd = pwd;
        this.isRegistered = isRegistered;
        this.oos = oos;
    }

    @Override
    public void run() {
        //file containing password
        String path = "";
        File file = new File(path);

        //if user is already register
        if (isRegistered){
            if (tryLogin(file)){
                GameController.getInstance().addNewUserLoggedIn(oos,nickname);
                //
                sendLoginSuccess();
            } else{
                //
                Message message = new ErrorMessage(CREDENTIAL_WRONG);
                try {
                    oos.writeObject(message);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }else{
            try {
                if (!nicknameExistence(file)){
                    try {
                        FileWriter fw = new FileWriter(file, true);
                        BufferedWriter bufferedWriter = new BufferedWriter(fw);
                        bufferedWriter.write(nickname + " " + pwd.hashCode());
                        bufferedWriter.newLine();
                        bufferedWriter.close();
                        fw.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    GameController.getInstance().addNewUserLoggedIn(oos,nickname);
                    //
                    sendLoginSuccess();
                }else {
                    //
                    Message message = new ErrorMessage(NICKNAME_ALREADY_EXIST);
                    try {
                        oos.writeObject(message);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (FileNotFoundException e) {
                System.out.println("Invalid file path");
            }
        }
    }

    private boolean nicknameExistence(File file) throws FileNotFoundException {
        Scanner sc = new Scanner(file);
        boolean existence = false;
        sc.useDelimiter("\\s+");
        while (sc.hasNext() && !existence){
            if (nickname.equals(sc.next()))
                existence = true;
        }
        return existence;
    }

    private boolean tryLogin(File file){
        boolean found = false;
        try {
            Scanner sc = new Scanner(file);
            sc.useDelimiter("\\s+");

            while (!found && sc.hasNext()){
                String s = sc.next();
                if (s.equals(nickname)){
                    String s1 = sc.next();
                    int pwd_saved = Integer.parseInt(s1);
                    if (pwd_saved == pwd.hashCode()) {
                        found = true;
                    }
                }
            }
            sc.close();
        } catch (FileNotFoundException e) {
            System.out.println("Invalid file path");
        }
        return found;
    }

    private void sendLoginSuccess(){
        Message message = new LoginSuccessMessage();
        try {
            oos.writeObject(message);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
