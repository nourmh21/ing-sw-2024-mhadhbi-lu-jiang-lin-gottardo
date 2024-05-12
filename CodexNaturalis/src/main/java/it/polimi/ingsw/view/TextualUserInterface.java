package it.polimi.ingsw.view;

import java.io.IOException;
import java.io.PrintStream;
import java.util.List;
import java.util.Scanner;

public class TextualUserInterface {
    private final Scanner in;
    private final PrintStream out;
    private final String red = "\u001B[31m";
    private final String reset = "\u001B[0m";

    private String nickName;
    private String password;
    private String confirmPassword;
    private int numberOfPlayers;
    private List<String> playersInLobby;

    public TextualUserInterface(){
        in = new Scanner(System.in);
        out = new PrintStream(System.out, true);
        playersInLobby = null;
    }

    public void show_title() {
        System.out.println("\u001B[38;5;231m░██████╗░█████╗░██████╗░███████╗██╗░░░██╗");
        System.out.println("\u001B[38;5;231m██╔════╝██╔══██╗██╔══██╗██╔════╝╚██╗░██╔╝");
        System.out.println("\u001B[38;5;231m██║░░░░░██║░░██║██║░░██║█████╗░░░╚████╔╝░");
        System.out.println("\u001B[38;5;231m██║░░░░░██║░░██║██║░░██║██╔══╝░░░██╔░██╗░");
        System.out.println("\u001B[38;5;231m╚██████╗░█████╔╝██████╔╝███████╗██╔╝░░██╗");
        System.out.println("\u001B[38;5;231m░╚═════╝░╚════╝░╚═════╝░╚══════╝╚═╝░░░╚═╝");
        System.out.println("\u001B[38;5;231m███╗░░██╗░█████╗░████████╗██╗░░██╗██████╗░░█████╗░██╗░░░░░██╗░██████╗");
        System.out.println("\u001B[38;5;231m████╗░██║██╔══██╗░░░██╔══╝██║░░██║██╔══██╗██╔══██╗██║░░░░░██║██╔════╝");
        System.out.println("\u001B[38;5;231m██╔██╗██║███████║░░░██║░░░██║░░██║██████╔╝███████║██║░░░░░██║╚█████╗░");
        System.out.println("\u001B[38;5;231m██║╚████║██╔══██║░░░██║░░░██║░░██║██╔══██╗██╔══██║██║░░░░░██║░╚═══██╗");
        System.out.println("\u001B[38;5;231m██║░╚███║██║░░██║░░░██║░░░░█████╔╝██║░░██║██║░░██║███████╗██║██████╔╝");
        System.out.println("\u001B[38;5;231m╚═╝░░╚══╝╚═╝░░╚═╝░░░╚═╝░░░░╚════╝░╚═╝░░╚═╝╚═╝░░╚═╝╚══════╝╚═╝╚═════╝░");
    }
    public void askForConnection(){
        int c;
        String address;
        out.println("Please provide the IP address or the URL of the server: ");
        address = in.nextLine();
        //send address to server
        //if it is valid continue with connection else
        //invalidAddress();
        out.println("Choose the connection type: ");
        out.println("1 - RMI");
        out.println("2 - SOCKET");
        c = choice();
        out.println("Connecting...");
        //send user choices about connection type to server
        out.println("Connection successful!");
    }
    public void askForSignInOrSignUp(){
        int c;
        out.println("Enter: ");
        out.println("1 - LOGIN if you already have an existing account");
        out.println("2 - REGISTRATION otherwise");
        c = choice();
        if(c==1){
            signIn();
        }
        else{
            signUp();
        }
    }
    public void signIn(){
        out.println("Please enter nickName: ");
        nickName = in.nextLine();
        out.println("Please enter password: ");
        password = in.nextLine();
        //send credentials to server
        //the server validates credentials
        //if they are correct
        out.println("LOGIN SUCCESSFUL!");
        //else
        //signInFailure();
    }
    public void signUp(){
        int c;
        setNickName();
        //send username to server
        //the server checks if it already exists
        //if it is correct then continue with sign up
        //else
        //invalidNickName();
        setPassword();
        setConfirmPassword();
        out.println("Choose: ");
        out.println("1 - Go next");
        out.println("2 - Go back to selection");
        c = choice();
        if(c==1){
            //send credentials to server
            //the server registers credentials
            out.println("REGISTRATION SUCCESSFUL!");
        }
        else{
            askForSignInOrSignUp();
        }
    }

    public void checkIfExistsGame(){
        if(playersInLobby!=null){
            //join in exist game
            playersInLobby.add(nickName);
        }
        else {
            //create a new game
            setNumberOfPlayers();
            playersInLobby.add(nickName);
        }
    }
    public void show_players(){
        for(String player : playersInLobby){
            out.println(player);
        }
    }
    public void setNickName() {
        out.println(red + "WARNING: Special characters and sweeps are not allowed!" + reset);
        out.println("Please enter nickName: ");
        nickName = in.nextLine();
        if(!nickName.matches("[a-zA-Z0-9]+")){
            out.println(red + "ERROR: Invalid nickname!" + reset);
            setNickName();
        }
    }
    public void setPassword() {
        out.println("Please enter password: ");
        password = in.nextLine();
        if(!password.matches("[a-zA-Z0-9]+")){
            out.println(red + "ERROR:Invalid password!" + reset);
            out.println(red + "WARNING: Special characters and sweeps are not allowed!" + reset);
            setPassword();
        }
    }
    public void setConfirmPassword(){
        out.println("Confirm password: ");
        confirmPassword = in.nextLine();
        if(!confirmPassword.equals(password)){
            out.println(red + "ERROR: Incompatible password!" + reset);
            setConfirmPassword();
        }
    }
    public void setNumberOfPlayers() {
        String inputRead;
        out.println("Select number of players: [2/3/4]");
        while(true){
            inputRead = in.nextLine();
            try{
                numberOfPlayers=Integer.parseInt(inputRead);
            }catch (NumberFormatException e){
                out.println(red + "ERROR: Invalid input!" + reset);
                continue;
            }
            if(numberOfPlayers!=2 && numberOfPlayers!=3 && numberOfPlayers!=4){
                out.println(red + "ERROR: Invalid input!" + reset);
            }
            else break;
        }
    }
    public void signInFailure(){
        int c;
        out.println(red + "ERROR: Invalid credentials!" + reset);
        out.println("Choose: ");
        out.println("1 - Continue with login");
        out.println("2 - Go back to selection");
        c = choice();
        if(c==1){
            signIn();
        }
        else{
            askForSignInOrSignUp();
        }
    }

    public void invalidAddress(){
        out.println(red + "ERROR: Invalid address!" + reset);
        askForConnection();
    }

    public void invalidNickName(){
        out.println(red + "ERROR: Nickname already exists!" + reset);
        signUp();
    }
    public int choice(){
        String inputRead;
        int c = -1;
        while(true){
            inputRead = in.nextLine();
            try{
                c = Integer.parseInt(inputRead);
            }catch(NumberFormatException e){
                out.println(red + "ERROR: Invalid input!" + reset);
                continue;
            }
            if(c != 1 && c != 2)
                System.out.println(red + "ERROR: Invalid input!" + reset);
            else break;
        }
        return c;
    }

    public void cleanConsole() {
        try {
            // Check the operating system to determine the appropriate command
            String os = System.getProperty("os.name");

            // Clear the console based on the operating system
            if (os.contains("Windows")) {
                new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
            } else {
                new ProcessBuilder("bash", "-c", "clear").inheritIO().start().waitFor();
            }
        } catch (InterruptedException | IOException e) {
            System.out.println("Error while clearing console: " + e.getMessage());
        }
    }
}
