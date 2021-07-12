package com;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Controller {

	public static List<Product> products = new ArrayList<>();

	public static void main(String[] args) {
		try {
			loadProducts();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		runner();
	}

	public static void runner() {
		String Food = runMenu();
		System.out.println("You enetered :: " + Food);
		if (!Food.equals("0")) {
			Product product = retrieveProduct(Food.trim());
			if (product == null) {
				System.out.println("The product code entered does not exists. Please provide a valid product code");
				runner();
			} else {
				boolean isAvailable = checkAvailability(product);
				if (isAvailable) {

					int change = CoinCollector.moneyInserted(product.getPrice());
					CoinDispenser.dispense(change);
					ProductDispencer.dispense(product);
					reduceQuantity(product);
				} else {
					System.out.println("The product is currently unavailable. You can purchase another product");
					runner();
				}
			}
		} else {
			System.out.println("The product code entered does not exists. Please provide a valid product code");
			runner();
		}
	}

	private static void reduceQuantity(Product product) {
		products.stream().filter(p -> p.getProductCode() == product.getProductCode())
				.forEach(p -> p.setQuantity(p.getQuantity() - 1));

	}

	private static void loadProducts() throws IOException {
		ClassLoader classloader = Thread.currentThread().getContextClassLoader();
		InputStream is = classloader.getResourceAsStream("resources/data.csv");
		String line = "";
		String splitBy = ",";
		// parsing a CSV file into BufferedReader class constructor
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		while ((line = br.readLine()) != null) // returns a Boolean value
		{
			String[] data = line.split(splitBy); // use comma as separator

			Product p = new Product();
			p.setProductCode(data[0].trim());
			p.setProductName(data[1]);
			p.setPrice(Integer.parseInt(data[2]));
			p.setQuantity(Integer.parseInt(data[3]));
			products.add(p);
		}
	}

	public static String runMenu() {
		Scanner keyboard = new Scanner(System.in);
		String choice = "0";
		try {
			System.out.println("\nHey! we have below items available ");
			for (Product p : products) {
				System.out.print("\nProduct Code: " + p.getProductCode() + " Name: " + p.getProductName() + " Price: "
						+ p.getPrice());
			}

			System.out.println("\nPlease enter the product code");
			choice = keyboard.next();
		} catch (Exception e) {
			System.out.println("Please provide a valid product code");
		}
		return choice;
	}

	public static Product retrieveProduct(String menuChoice) {
		Product product = products.stream().filter(p -> p.getProductCode().equals(menuChoice)).findAny().orElse(null);
		return product;
	}

	public static boolean checkAvailability(Product product) {
		if (product.getQuantity() > 0)
			return true;
		return false;
	}

}
