package com.juurivuohi.anonyymit_ulvojat;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext("/com/juurivuohi/beans/beans.xml");
		UserInterface ui = (UserInterface)context.getBean("ui");
		ui.start();
		((ClassPathXmlApplicationContext) context).close();

	}

}
