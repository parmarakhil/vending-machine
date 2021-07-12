package com;

import java.util.Scanner;

public class CoinCollector {

	public static int money = 0;

	public static int moneyInserted(int Price) {
		Scanner keyboard = new Scanner(System.in);
		int money1 = 0;
		System.out.println("Your item costs: " + Price + " Please enter the amount of money:");
		money1 = calculator(Price, keyboard);
		money = 0;
		return money1 - Price;
	}

	private static int calculator(int Price, Scanner keyboard) {
		System.out.println("You have added " + Price + " amount of money");
		int value = keyboard.nextInt();
		if (value == 10 || value == 20 || value == 50) {
			money += value;
			while (Price > money) {
				calculator(money, keyboard);
			}
		} else {
			System.out.println("We only except changes of 10,20 & 50");
		}
		return money;
	}
}
