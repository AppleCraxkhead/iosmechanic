package com.iosmechanic;
import java.util.Scanner;
public class command_line {
    public static void main(String [] args){
        Scanner stdin = new Scanner(System.in);
        System.out.println("Welcome to iOS Mechanic!\n ---------- \nPlease ensure your iDevice is unlocked and connected via USB \nPress ENTER to proceed");
        stdin.nextLine();
        DeviceManager deviceManager = new DeviceManager();
        System.out.println("initiating devicemanager");
        deviceManager.getDeviceInfo();
        System.out.println("Press ENTER to Close");
        stdin.nextLine();
        stdin.close();
}
}
