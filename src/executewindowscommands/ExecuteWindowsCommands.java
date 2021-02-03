/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package executewindowscommands;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 *
 * @author Andrea
 */
public class ExecuteWindowsCommands {
    
    public static void main(String[] args) {
        try {
            //If you want to know priviledge level
            System.out.println(AdministratorChecker.IS_RUNNING_AS_ADMINISTRATOR);
            
            //Write commands like these: command1 & command2
            cmdCommand("");
            
            //Write commands like these: command1; command2
            powerShellCommand("");
            
        } catch (Exception ex) {
            System.out.println(ex.toString());
        }
    }
    
    public static void cmdCommand(String userCommand) throws IOException {
        userCommand = "cmd /c " + userCommand;
        executeCommand(userCommand);
    }
    
    public static void powerShellCommand(String userCommand) throws IOException {
        userCommand = "powershell.exe " + userCommand;
        executeCommand(userCommand);
    }
    
    public static void executeCommand(String userCommand) throws IOException {
        String command = userCommand;
        //String command = "powershell.exe " + userCommand;
        Process process = Runtime.getRuntime().exec(command);
        process.getOutputStream().close();
        
        String line;
        System.out.println("Standard Output:");
        BufferedReader stdout = new BufferedReader(new InputStreamReader(process.getInputStream()));
        while ((line = stdout.readLine()) != null) {
            System.out.println(line);
        }
        stdout.close();
        System.out.println("Standard Error:");
        BufferedReader stderr = new BufferedReader(new InputStreamReader(process.getErrorStream()));
        while ((line = stderr.readLine()) != null) {
            System.out.println(line);
        }
        stderr.close();
        System.out.println("Done");
    }
}
