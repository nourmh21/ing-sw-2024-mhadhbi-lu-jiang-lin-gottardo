package it.polimi.ingsw.controller.server.task;

import it.polimi.ingsw.network.Client;
import it.polimi.ingsw.controller.server.GameController;
import it.polimi.ingsw.message.enums.NotifyType;

import java.io.*;
import java.util.Scanner;

import static it.polimi.ingsw.message.enums.ErrorType.*;

/*
  Comment about this class implementation status:
  I almost completely ignored cyber-security issues
  I didn't try to found an efficient algorithm to find content in a file <- Should I do it?
 */
public class Access implements Runnable{
    private String nickname;
    private String pwd;
    private Boolean isRegistered;
    private Client client;

    public Access(Client client, String nickname, String pwd, Boolean isRegistered){
        this.nickname = nickname;
        this.pwd = pwd;
        this.isRegistered = isRegistered;
        this.client = client;
    }

    @Override
    public void run() {
        if (GameController.getInstance().isLoggedIn(nickname)){
            client.informError(ACCOUNT_ALREADY_LOGGED);
        }else{
            //file containing password
            String rootPath = System.getProperty("user.dir");
            String path = "/CodexNaturalis/src/main/java/it/polimi/ingsw/controller/server/task/data.txt";
            File file = new File(rootPath + path);

            //if user is already register
            if (isRegistered){
                if (tryLogin(file)){
                    loginSuccess();
                } else{
                    client.informError(CREDENTIAL_WRONG);
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
                        loginSuccess();
                    }else {
                        client.informError(NICKNAME_ALREADY_EXIST);
                    }
                } catch (FileNotFoundException e) {
                    System.out.println("Invalid file path");
                }
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

    private void loginSuccess(){
        client.setNickname(nickname);
        GameController.getInstance().addNewUserLoggedIn(nickname);
        client.informActionResult(NotifyType.LOGIN_SUCCESS);
    }


}
