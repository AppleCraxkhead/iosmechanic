package com.iosmechanic;
import java.util.Scanner;
public class command_line {
    public static void main(String [] args){
        DeviceManager deviceManager = new DeviceManager();
        Scanner stdin = new Scanner(System.in);
        System.out.println("Welcome to iOS Mechanic!\n---------------------\nPlease ensure your iDevice is unlocked and connected via USB \nPress ENTER to proceed");
        stdin.nextLine();
        while(true){
            System.out.println("Connected Devices\n----------------");
            String connectedDevice = deviceManager.getFromDeviceInfo("DeviceName");
            if(connectedDevice == null){
                System.out.println("No iDevices Detected. Please ensure iDevice is unlocked and connected via USB. If prompted, select <Trust> on your iDevice upon connection. ");
                System.out.println("Once you have connected your iDevice, press ENTER to continue\n\n");
                stdin.nextLine();
                continue;
            }
            System.out.println(connectedDevice + "\n");
            System.out.println("Select an option:\n1: Display UDID \n2:Device Actions\n3: Display Activation Status\n0: Quit");
            int choice = stdin.nextInt();
            stdin.nextLine();
            System.out.println("----------------");
            if(choice ==1){
                String message = "UDID for " + connectedDevice + ": " + deviceManager.getFromDeviceInfo("UniqueDeviceID");
                System.out.println(message +"\n Press ENTER to continue");
                stdin.nextLine();
                }
            if(choice == 2){
                System.out.println("DEVICE ACTIONS FOR: " + connectedDevice + "\n----------------");
                System.out.println("Select a Device Action\n1:Restart");
                int actionChoice = stdin.nextInt();
                stdin.nextLine();
                if(actionChoice == 1){
                    System.out.println("Restarting " + connectedDevice + "...");
                    deviceManager.performDeviceActions("idevicediagnostics", "restart");
                    System.out.println("Press ENTER to continue");
                    stdin.nextLine();
                }


            }

            if(choice == 3){
                String message = "This device is " + deviceManager.getFromDeviceInfo("ActivationState");
                System.out.println(message + "\n\nPress ENTER to continue");
                stdin.nextLine();
                }
            if(choice == 0){
                stdin.close();
                break;
            }
    }
    
}}
