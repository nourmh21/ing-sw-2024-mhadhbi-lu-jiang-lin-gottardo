package it.polimi.ingsw.model;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Account {
    private String nickName;

    /**
     * @return player's Nickname
     */
    public String getNickName() {
        return nickName;
    }

    /**
     * Check if a specific player's nickName exists within the file "Credenziali.txt"
     * @param nickName player's nickName
     * @return true if found, otherwise false
     */
    public boolean checkNickName(String nickName){
        BufferedReader reader = null;
        try{
            reader = new BufferedReader(new FileReader("Credenziali.txt"));
            String line;
            while((line = reader.readLine()) != null){
                String[] arrayCredenziali = line.split(":");
                if(nickName.equals(arrayCredenziali[0])){
                    return true;
                }
            }
            return false;
        } catch (FileNotFoundException e){
            System.out.println(e.getMessage());
            return false;
        } catch (IOException e){
            System.out.println(e.getMessage());
            return false;
        } finally { //close BufferedReader
            if(reader != null)
                try{
                    reader.close();
                } catch (IOException e){
                    System.out.println(e.getMessage());
                }
        }
    }
}
