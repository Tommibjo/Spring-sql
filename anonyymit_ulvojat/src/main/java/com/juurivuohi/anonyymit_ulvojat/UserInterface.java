package com.juurivuohi.anonyymit_ulvojat;

import java.util.Scanner;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component("ui")
public class UserInterface {

	@Autowired
	private MessagesDAO messagedao;
	
	private Scanner scanner;
	private boolean running;
	private int menuoption;

	public UserInterface() {
		this.scanner = new Scanner(System.in);
		this.running = true;
	}

	public void start() {
		menu();
	}

	public void menu() {
		while (this.running == true) {
			System.out.println("\nCommands: ");
			System.out.println("1) List comments from db");
			System.out.println("2) Add comment to db");
			System.out.println("3) Quit");
			 this.menuoption = Integer.parseInt(this.scanner.nextLine());
			 System.out.println(this.menuoption);
			if (this.menuoption == 1) {
				for(int i = 0; i < messagedao.getMessages().size(); i++) {
					System.out.println("Comment ID: " + messagedao.getMessages().get(i).getId() + ", Name: " + messagedao.getMessages().get(i).getName() + ", Message: " + messagedao.getMessages().get(i).getMessage());
				}
			}else if(this.menuoption == 2) {
				System.out.print("name: ");
				String name = this.scanner.nextLine();
				System.out.print("Comment: ");
				String comment = this.scanner.nextLine();
				this.messagedao.writeMessage(name, comment);
			}else if(this.menuoption == 3) {
				this.running = false;	
			}else {
				System.out.println("Invalid option");
			}
		}
	}

}
