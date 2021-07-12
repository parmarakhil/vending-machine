package com;

public class CoinDispenser {

	public static void dispense(int change) {
		int fifties = 0;
		int twenties = 0;
		int tens = 0;
		while (change >= 50) {
			fifties = fifties + 1;
			change = change - 50;
		}
		while (change >= 20) {
			twenties = twenties + 1;
			change = change - 20;
		}
		while (change >= 10) {
			tens = tens + 1;
			change = change - 10;
		}

		// to see the change, print it to the console perhaps
		System.out.printf("\nHere's your change:\n%d fifties, %d twenties, %d tens and %d pennies!", fifties, twenties,
				tens, change);
	}
}
