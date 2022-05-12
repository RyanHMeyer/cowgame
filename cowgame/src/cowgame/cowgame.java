package cowgame;

import java.util.Scanner;
import java.util.Random;
public class cowgame {
	static Scanner key = new Scanner(System.in);
	static int week = 1;
	static double money = 500;
	static int dairy = 0; //how much dairy was made
	static double dairyprice = 2.00;
	static int dairym = 50; //how much dairy each cow makes
	static int dairys = 500; //how much dairy people are willing to buy
	static int cows = 0;
	static int cowprice = 450;
	static int food = 0;
	static int foodprice = 50;
	static int seed = 0;
	static double seedprice = 10;
	static int planted = 0; //how many seeds you have in the ground
	static int weekp = 0; //what week the seeds will be done growing on
	static int companylevel = 0; //what phase of company you are on
	static boolean foodprch = true;//determines if the price of food should vary or not depending on company level
	static boolean seedprch = true;//determines if the price of seeds should vary or not depending on company level
	static String compname = "0";//whats the new company name
	public static void main(String[] args) {
		//beginning of game calculations
		Random rand = new Random();
		System.out.print("Welcome to Cow Game! This is a game about buying cows, planting food, and managing a dairy farm from 1 cow to thousands!"
				+ "\nIt was created by Ryan Meyer for the AP Computer Science Principles Final Project."
				+ "\n\n***WARNING: THIS GAME IS NOT A REALISTIC SIMULATION OF A DAIRY FARM AND SHOULD NOT BE USED AS SUCH***"
				+ "\n\nHOW TO PLAY"
				+ "\n\nIn order to have a Dairy farm, you need cows! Each week, you will be prompted asking if you wish to buy a cow. If you do not have any cows, you will lose the game."
				+ "\nEach cow you own will produce dairy, which will automatically be sold. If you have too much dairy and not enough buyers, dairy will go bad and be lost sales."
				+ "\nOnce you produce enough dairy, companies will ask you to sell them your dairy. They might give you some restrictions, but it's the only way to increase consumers."
				+ "\nYou also need to feed your cows! Each cow requires 1 food each week. If they don't have enough food, you'll loose a cow."
				+ "\nFood is expensive, but seeds are cheap! It takes 8 weeks for seeds to be done growing, and once you plant some seeds, you can't plant more until they're done."
				+ "\nThings might not be so easy though, as every 4 weeks the economy will change. This might make things cheaper or more expensive."
				+ "\nAlong with the economy changing, random events will occur. They range from amazing to devastating, and can really leave an impact."
				+ "\nRemeber to have fun, and try to get past 1000 cows!\n");
		while (cows > 0 || week == 1) {
			//beginning of wave calculations
			if (week == weekp && planted > 0) { //grows any seeds if its ready to be grown
				food += planted;
				planted = 0;
				weekp = 0;
			}
			cowgame.randEvent();//executes a random event
			System.out.println("Week " + week);
			System.out.println("You have " + String.format("%.2f", money) + " dollars.");
			System.out.println("You have " + cows + " cows, meaning that you'll produce " + (dairym * cows) + " gallons of milk, which will sell for " + dairyprice + " dollars each.");
			if (companylevel > 6) {
				System.out.println("You are the owner and founder of your company, " + compname + ".");
			}
			System.out.println("You have " + food + " food.");
			System.out.println("You have " + seed + " seeds.");
			if (planted > 0) {
				System.out.println(planted + " seeds will be done growing week " + weekp + ".");
			}
			cows += cowgame.purchase("cows", cowprice);
			food += cowgame.purchase("food", foodprice);
			seed += cowgame.purchase("seeds", seedprice);
			//end of wave calculations
			food = food - cows;
			if (food < 0) {
				cows += food;
				food = 0;
			}
			dairy += dairym * cows;
			if (dairy < dairys + 1) {
				money += dairy * dairyprice;
				dairy = 0;
			}
			if (dairy > dairys) {//when you have too many cows/ determining new company level
				String selec = "0";
				System.out.println("You produced too much dairy and lost " + (dairy - dairys) + " gallons, meaning a loss of " + ((dairy - dairys) * dairyprice) + " dollars.");
				money += dairys * dairyprice;
				dairy = 0;
				if (companylevel == 0) {
					while (!"W".equals(selec) && !"B".equals(selec) && !"G".equals(selec)) {
						System.out.println("3 producers of dairy have contacted you and are attempting to contract you to produce dairy for them."
								+ "\nAs you are now producing too much dairy to sell to your current market, your only option is to accept one of their offers"
								+ "\nso you can sell all the dairy your producing. These 3 companies are "
								+ "\n1. Windy's, in an attempt to 'get more local', is trying to contract you. If you accept their offer, they will provide 10 free food"
								+ "\neach week. However, they are only willing to buy your dairy for 0.8 dollars per gallon."
								+ "\n2. BedEx is offering to drive your milk from place to place. They will not offer anything to you, but will not change anything either."
								+ "\n3. GoalFoods wants to buy your milk for 3.5 dollars a gallon, but they require you to only buy their all organic cow chow, which costs"
								+ "\n100 dollars a unit, or to plant their all organic seeds, which cost 20 a seed. You will also have to throw out all of your current seeds"
								+ "\nand food, but will recieve slight financial compensation for these items."
								+ "\nWhich one do you choose? W for Windy's, B for BedEx, and G for GoalFoods.");
						selec = key.next();
					}
					if ("W".equals(selec)) {
						companylevel = 1;
					}
					if ("B".equals(selec)) {
						companylevel = 2;
					}
					if ("G".equals(selec)) {
						companylevel = 3;
						money += (food * 25) + (seed * 5) + (planted * 5);
						planted = 0;
						food = 0;
						seed = 0;
					}
					dairys = 5000;
				}
				else if (companylevel > 0 && companylevel < 4) {
					while (!"M".equals(selec) && !"A".equals(selec) && !"J".equals(selec)) {
						System.out.println("3 producers of dairy have contacted you and are attempting to contract you to produce dairy for them."
								+ "\nAs you are now producing too much dairy to sell to your current market, your only option is to accept one of their offers"
								+ "\nso you can sell all the dairy you're producing. These 3 companies are "
								+ "\n1. MartWal is trying to contract you to produce dairy for them. They will allow you to buy their mass shipments of food and seeds,"
								+ "\nmeaning that foodprice will go down to 25 and seedprice will go down to 4. They will buy milk for .6 a gallon, though."
								+ "\n2. Anazom wants to drive your milk from place to place. They offer no changes, and will buy milk for 2.0 a gallon."
								+ "\n3. Jrader Toes wants to buy your milk from you. They will send over a free cow every 2 weeks, but you have to buy their"
								+ "\nfood and seeds for 80 and 15 each, with these prices not affected by the current economy. They will buy gallons for 2.5 each."
								+ "\nWhich one do you choose? M for MartWal, A for Anazom, and J for Jrader Toes.");
						selec = key.next();
					}
					if ("M".equals(selec)) {
						companylevel = 4;
					}
					if ("A".equals(selec)) {
						companylevel = 5;
					}
					if ("J".equals(selec)) {
						companylevel = 6;
					}
					dairys = 50000;
				} else { 
					System.out.println("Congratulations! You're now producing sooooo much dairy that no company can manage it all for you, and you"
							+"\nhave to create your own company to ship it all. From this point forward, gallon price is 2.0 a gallon, and you"
							+ "\nhave to buy all the food, seeds and cows from the marketplace, which is affected by the economy."
							+ "\nNOTE: THIS IS THE END OF THE GAME. YOU CAN KEEP PLAYING, BUT AFTER THIS POINT THERE IS NO NEW CONTENT."
							+ "\nWhat would you like to name your new company? (no spaces)");
							compname = key.next();
							companylevel += 100;
							dairys = 2147483647;
				}
				
			}
			if (companylevel == 1) {
				food += 10;
				dairyprice = 0.8;
			}
			if (companylevel == 3) {
				dairyprice = 3.5;
				seedprice = 20;
				foodprice = 100;
			}
			if (companylevel == 4) {
				dairyprice = .6;
				seedprice = 4;
				foodprice = 25;
			}
			if (companylevel == 5) {
				dairyprice = 2.0;
				seedprice = 10;
				foodprice = 50;
			}
			if (companylevel == 6) {
				dairyprice = 2.5;
				seedprice = 15;
				foodprice = 80;
				if (week % 2 == 0) {
					cows += 1;
				}
			}
			if (companylevel > 6) {
				dairyprice = 2.0;
				seedprice = 10;
				foodprice = 50;
			}
			if (planted == 0 && seed > 0) { //handling inputting how many seeds you want to plant
				int choice; //how many seeds you buy
				System.out.println("How many seeds would you like to plant? You have " + seed + " seeds: ");
				choice = key.nextInt();
				if (choice < 0) {
					choice = 0;
				}
				if (choice > seed) {
					choice = seed;
				}
				if (choice != 0) {
					planted = choice;
					seed -= choice;
					weekp = (week + 7);
				}
			}
			if (companylevel == 3 || companylevel == 4 || companylevel == 6) {//determines if food and seed price should vary
				foodprch = false;
				seedprch = false;
			} else {
				foodprch = true;
				seedprch = true;
			}
			if ((week % 4) == 0) {//determines prices
				double pricerando = (rand.nextInt(50));//randomizes the price
				boolean nrando = (rand.nextBoolean()); //determines if its negative or positive
				if (nrando == false) {
					pricerando = pricerando * -1;
				}
				if (seedprice + (pricerando/10) > 20) {
					pricerando = pricerando * -1;
				} else if (seedprice + (pricerando/10) < 4) {
					pricerando = pricerando * -1;
				}
				if (foodprice + (pricerando/3) > 100) {
					pricerando = pricerando * -1;
				} else if (foodprice + (pricerando/3) < 25) {
					pricerando = pricerando * -1;
				}
				if (cowprice + (pricerando*2) > 800) {
					pricerando = pricerando * -1;
				} else if (cowprice + (pricerando*2) < 200) {
					pricerando = pricerando * -1;
				}
				if (foodprch == true) {
					seedprice += (pricerando/10);
				}
				if (seedprch == true) {
					foodprice += (pricerando/3);
				}
				cowprice += (pricerando*2);
				cowgame.priceReset();
				System.out.println("Prices have changed!\nThe price to buy a cow is now " + cowprice + ".\nThe price to buy food is now " + foodprice + ".\nThe price to buy seeds is now " + String.format("%.2f", seedprice) + ".");
			}
			week += 1;
		}
		System.out.print("You ran out of cows!");
	}
	public static int purchase(String object, double price) {
		cowgame.priceReset();
		int purp = 0; //the input from the player for how much is bought
		int max = cowgame.max(money, price);
		if (max > 0) {
			System.out.print("How many " + object + " do you want to buy? You can buy a total of " + max + " " + object + ", and the current price is " + String.format("%.2f", price) + ": ");
			purp = key.nextInt();
			if (purp > max) {
				purp = max;
			}
			if (purp < 0) {
				purp = 0;
			}
			money = money - (purp * price);
			System.out.println("You now have " + String.format("%.2f", money) + " dollars left.");
			return purp;
		} else {
			return purp;
		}
	}
	public static int max(double money, double price){
		double returnv = Math.floor(money/price);
		return (int)returnv;
	}
	public static void priceReset() {
		if (cowprice > 800 || foodprice > 100 || seedprice > 20 || cowprice < 200 || foodprice < 25 || seedprice < 4) {
			cowprice = 450;
			foodprice = 50;
			seedprice = 10;
		}
	}
	public static void randEvent() {//determines if a random event happens or not
		Random randu = new Random();
		int randoNumber = randu.nextInt(150);
		String randoEvent = "0";
		if (randoNumber == 0) {
			randoEvent = "A cow wandered onto your farm. You got 1 free cow!";
			cows++;
		}
		if (randoNumber == 1) {
			randoEvent = "Aliens abducted a cow! You lost a cow...";
			cows--;
		}
		if (randoNumber == 2 && cows > 20) {
			randoEvent = "AP School board decided that you should loose 20 cows, and 20 cows vanished.";
			cows -= 20;
		}
		if (randoNumber == 3 && cows > 100) {
			randoEvent = "Propellers popped out of 100 of your cows and they just flew off, you lost 100 cows.";
			cows -= 100;
		}
		if (randoNumber == 4 && cows > 20) {
			randoEvent = "Text on a screen explained that a series of events occured and you gained 20 virtual cows!";
			cows += 20;
		}
		if (randoNumber == 5 && cows > 100) {
			randoEvent = "UFOs flew by and decided to apologize for all the cows taken, and gave you 100 synthetic cows!";
			cows += 100;
		}
		if (randoNumber == 6 && food > 50) {
			randoEvent = "50 pieces of food grew legs and walked away, becoming productive members of society. Good for them! But you lost 50 food.";
			food -= 50;
		}
		if (randoNumber == 7 && food > 420) {
			randoEvent = "Skaters broke onto your farm and 420 blazed it! (420 of your food, that is. You lost 420 food.";
			food -= 420;
		}
		if (randoNumber == 8 && food > 1500) {
			randoEvent = "Peter Pan and his lost boys stole 1500 of your food!";
			food -= 1500;
		}
		if (randoNumber == 9) {
			randoEvent = "Coca Cola gave you 50 food! No idea why, though...";
			food += 50;
		}
		if (randoNumber == 10 && food > 50) {
			randoEvent = "Brother Earth thought it would be really funny if he gave you 420 food, so he did!";
			food += 420;
		}
		if (randoNumber == 11 && food > 300) {
			randoEvent = "Mother Earth took food away from the rich and gave it to you! You got 1500 food!";
			food += 1500;
		}
		if (randoNumber == 12 && companylevel > 0) {
			randoEvent = "Seeds literally just fased into existence if front of you. You got 500 seeds!";
			seed += 500;
		}
		if (randoNumber == 13 && companylevel > 0) {
			randoEvent = "Dirt decided to be generous. You got 5000 seeds!";
			seed += 5000;
		}
		if (randoNumber == 14 && planted != 0) {
			randoEvent = "Fire spread throughout the forest, but somehow the only thing that was hurt were all your seeds. You lost all your seeds in the ground...";
			planted = 0;
		}
		if (randoNumber == 15 && seed >= 100) {
			randoEvent = "Zombies took 100 of your seeds!";
			seed -= 100;
		}
		if (randoNumber == 16) {
			randoEvent = "Dairy got more expensive by 1 dollar!";
			dairyprice += 1.0;
		}
		if (randoNumber == 17) {
			randoEvent = "Thieves stole all your money!";
			money = 0;
		}
		if (randoNumber == 18) {
			randoEvent = "Thieves gave you 1000 dollars!";
			money += 1000;
		}
		if (!randoEvent.equals("0")) {
			System.out.println("////////////////////////////////////////////////////////////////////////\nRANDOM EVENT!\n" + randoEvent + "\n////////////////////////////////////////////////////////////////////////");
		}
	}
}