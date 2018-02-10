import java.util.Random;
import java.util.Scanner;

public class CrapsMP7 {


	public static void main(String[] args) {
		//just checking stuff
		int roundNumber = 0, bank = 250;
		boolean passline = true;
		//Introduction. Bank amount
		System.out.println("Hello. Welcome to a game of Craps. One person will be the shooter. You will be the player. You will both begin with $250.");
		System.out.println();
		for (int shooterCraps = 0; shooterCraps < 10; ) {
			Scanner asker = new Scanner(System.in);
			//Receives input from the user
			//Wager system make sure it is less than bank but at least $25
			System.out.println("Please make a wager. The wager will be in tokens. Each token is worth $25. Your wager must be at least 1 token and no more than " + bank/25 + ". Please only type the number of tokens.");
			int wager;
			//Wager is number of tokens, but prints out the dollar amount by multiplying by 25
			wager = asker.nextInt()*25;
			//makes sure that the wager remains within the banks ability and is more than the minimum of 25
			//ensures that wager is less than how much you have and at least 25
			if (wager <= bank && wager >= 25) {
				System.out.println("You have chosen a wager of $" + wager + ".");
			}
			//Wager is less than 1 token
			else if (wager < 25) {
				System.out.println("Your wager is less than $25.");
				do {
					System.out.println("Please print out an acceptable numerical wager that is less than your bank but at least $25.");
					wager = asker.nextInt()*25;
					//wager is more than your bank
					if (wager > bank) {
						do {
							System.out.println("Please print out an acceptable numerical wager that is less than your bank.");
							wager = asker.nextInt()*25;
						} while (wager > bank);
					}
				} while (wager < 25); //repeats until wager is more than or equal to 25
				System.out.println("You have chosen a wager of $" + wager + ".");
			} else if (wager > bank) {
				//Wager more than bank
				System.out.println("Your wager is larger than what is available in your bank. Please enter another number.");
				do {
					System.out.println("Please print out an acceptable numerical wager that is less than " + bank/25 + ".");
					wager = asker.nextInt()*25;
					if (wager < 25) {
						do {
							System.out.println("Please print out an acceptable numerical wager that is at least $25.");
							wager = asker.nextInt()*25;
						} while (wager < 25);
					}
				} while (wager > bank); //Cannot be more than bank so change 150 to whatever their bank is
				System.out.println("You have chosen a wager of $" + wager + ".");
			} else {
				//is not a number and must restart
				System.out.println("You have not typed in a numerical value. Please refresh the game and start over.");
			}
			//Choose Pass or Don't Pass Line
			System.out.println("Choose to make a Pass Line or Don't Pass Line wager. Declare this by typing 1 for pass, or 2 for don't pass.");
			int passornot = asker.nextInt();
			//If pass line chosen, it will make boolean passline true
			if (passornot == 1) {
				System.out.println("You have chosen to make a Pass Line.");
				passline = true;
			} else if (passornot == 2) {
				//passline is not chosen, so boolean is false
				System.out.println("You have chosen to make a Don't Pass Line.");
				passline = false;
			}
			//did not type in 1 or 2 and must retype
			else {
				System.out.println("You have not chosen either.");
				do {
					System.out.println("Please enter in a 1 to choose Pass Line, or a 2 to choose a Don't Pass Line wager.");
					passornot = asker.nextInt();
				} while (passornot > 2 || passornot < 1); //repeats instruction until user types in 1 or 2
				if (passornot == 1) {
					System.out.println("You have chosen to make a Pass Line.");
					passline = true;
				}
				else if (passornot == 2) {
					System.out.println("You have chosen to make a Don't Pass Line.");
					passline = false;
				}
			}
			// The start of the game
			Random r = new Random() ; // This 'rolls' the 'die'
			roundNumber++;
			int dice1 = r.nextInt(6) + 1;
			int dice2 = r.nextInt(6) + 1;
			int dieSum = dice1 + dice2; //adds the two die numbers
			int point;
			System.out.println("Round " + roundNumber + ", Roll 1: Numbers are " + dice1 + " and " + dice2 + "." + " Sum is " + dieSum + ".");
			// This checks if the first roll is 7 or 11.
			// if true, the shooter wins
			if (dieSum == 7 || dieSum == 11) {
				System.out.println("Shooter wins!");
				System.out.println();
				//Since shooter wins, you win, so you add money to bank
				if (passline) {
					bank += wager;
					System.out.println("You have won back your money. You have " + bank + "dollars left.");
				} else if (!passline) {
					//Since shooter wins, you lose, so you lose money from the bank
					bank -= wager;
					System.out.println("You have lost your wager. You have " + bank + " dollars left.");
				}
				else {
					//just in case
					System.out.println("Whoops! Something went wrong :( Please restart.");
				}
			}
			// This checks if the first roll is 2, 3, or 11.
			// If true, the shooter loses
			else if (dieSum == 2 || dieSum == 3 || dieSum == 12) {
				System.out.println("Shooter Craps!");
				System.out.println();
				shooterCraps++;
				if (!passline) {
					//if boolean variable is true, then money will be added to bank
					if (dieSum == 2 || dieSum == 3) {
						bank += wager;
						System.out.println("You have won back your money. You have " + bank + "dollars left.");
					}
					else if (dieSum == 12)
					{
						//when 12 is rolled, if boolean passline is false, then no money lost or gained
						System.out.println("You have not gained or lost any money. You have " + bank + " dollars left.");
					}
				} else if (passline) {
					//if boolean variable is false, then money will be subtracted from bank
					bank -= wager;
					System.out.println("You have lost your wager. You have " + bank + " dollars left.");
				} else {
					//just in case
					System.out.println("Whoops! Something went wrong :( Please restart.");
				}
			}
			// This sets the point value to whatever was rolled.
			// Then it does the rest of the rolls using the recursion down below
			else {
				point = dieSum;
				int diesum;
				System.out.println("Your point is " + point + ".");
				//if a point is rolled, checks to see if user wants to bet odds on an event
				System.out.println("Would you like to place odds on your bet? If yes, please type in 1. If no, please type in any other number.");
				int odds;
				odds = asker.nextInt();
				if (odds == 1) {
					//only if they choose 1, they will be able to change their wager
					if (passline) {
						//if boolean is true, they can multiply wager by 3 4 or 5
						System.out.println("Please choose to multiply your initial bet by 3x, 4x, or 5x. Type in either 3, 4, or 5.");
						int m;
						do {
							m = asker.nextInt();
							if (m == 3 || m == 4|| m == 5) {
								wager *= m;
								break;
							}
							else if (m != 3 || m!= 4|| m != 5) {
								System.out.println("Please input either 3, 4, or 5.");
							}
						} while(m != 3 || m!= 4|| m != 5);
						System.out.println("Your new wager is $" + wager + ".");
					}
					else if (!passline) {
						//if boolean is false, then the wager automatically multiplies by 6
						wager *= 6;
						System.out.println("You have chosen to increase your wager by 6x. Your new wager is $" + wager + ".");
					}
				}
				else if (odds != 1) {
					//Do ont want to bet the odds, so wager remains the same
					System.out.println("You have chosen not to increase your wager.");
				}
				else {
					System.out.println("Wow, you're great at messing this up? How did this even happen?!?!");
				}
				do {
					diesum = diceSum2(); 
					//second roll to get the point sum or 7
					// The automatic lose if the roll is seven
					if (diesum == 7) {
						System.out.println("Shooter Loses!!");
						if (odds == 1) {
							if (!passline) {
								//if boolean is false then they win their wager times any multiplier depending on what point they have
								switch (point) {
								case 4:
									bank = bank + (int)((double)wager * 1.5);
									break;
								case 10:
									bank = bank + (int)((double)wager * 1.5);
									break;
								case 5:
									bank = bank + (int)((double)wager * 1.6667);
									break;
								case 9:
									bank = bank + (int)((double)wager * 1.6667);
									break;
								case 6:
									bank = bank + (int)((double)wager * 1.8333);
									break;
								case 8:
									bank = bank + (int)((double)wager * 1.8333);
									break;
								case 7:
									bank += wager;
								default:
									System.out.println("Whoops! Something went wrong! Please restart.");
								}
								System.out.println("You have won. You have " + bank + " dollars left.");
							}
							else if (passline) {
								//if boolean is true, they lose their wager
								bank =- wager;
								System.out.println("You have lost your wager. You have " + bank + "dollars left.");
							}
						}

						else if (odds != 1) {
							//for users who have not chosen to bet the odds
							if (passline) {
								//for boolean is true, they will lose their original wager
								bank -= wager;
								System.out.println("You have lost your wager. You have " + bank + "dollars left.");
							}
							else if (!passline) {
								//for boolean is false, they will add their original wager
								bank+=wager;
								System.out.println("You have won. You have " + bank + " dollars left.");
							}
							else {
								System.out.println("Whoops! Something went wrong :( Please restart.");
							}
						}
						System.out.println();
						break; // Stops the rolls when the shooter loses
					}
					// This tells the player when the shooter wins
					else if (diesum == point) {
						//the shooter gets the point before getting a seven, so they win
						System.out.println("Shooter Wins!!");
						if (odds == 1) {
							//if the user chooses to bet the odds
							if (passline) {
								//given boolean is true, they will add however much their betting odds wager is plus the possibility of cases, given the point rolled is a certain number
								switch (point) {
								//adds multiplier of original wager to bank
								case 4:
									bank = bank + (int)((double)wager * 1.5);
									break;
								case 10:
									bank = bank + (int)((double)wager * 1.5);
									break;
								case 5:
									bank = bank + (int)((double)wager * 1.6667);
									break;
								case 9:
									bank = bank + (int)((double)wager * 1.6667);
									break;
								case 6:
									bank = bank + (int)((double)wager * 1.8333);
									break;
								case 8:
									bank = bank + (int)((double)wager * 1.8333);
									break;
								case 7:
									bank += wager;
								default:
									System.out.println("Whoops! Something went wrong! Please restart.");
								}
								System.out.println("You have won. You have " + bank + " dollars left."); 
							}
							else if (!passline) {
								//lose the six times betted odds wager from your bank
								bank =- wager;
								System.out.println("You have lost your wager. You have " + bank + "dollars left.");
							}
						}
						else if (odds != 1) {
							//do not bet the odds, so keep original wager
							if (!passline) {
								//subtract original wager
								bank -= wager;
								System.out.println("You have lost your wager. You have " + bank + "dollars left.");
							} else if (passline) {
								//add original wager
								bank+=wager;
								System.out.println("You have won. You have " + bank + " dollars left.");
							} else {
								//just in case
								System.out.println("Whoops! Something went wrong :( Please restart.");
							}
						}
						else {
							//really just in case
							System.out.println("Well this is awkward. You might wanna restart to make this work again...");
						}
					}
				} while (diesum != point); // Keeps going until the shooter wins
			}
			//Tells you at the end of each round how many times you've crapped, to keep count and make sure it is less than 10
			System.out.println("Shooter has crapped " + shooterCraps + " times.");
			//Checks that you have at least $25 in the bank to make wagers
			if (bank < 25) {
				System.out.println("You have run out of money.");
				System.out.println();
				break;
			} else if (bank >= 25) {
				System.out.println();
				continue;
			} else {
				System.out.println("Something went wrong! Please restart!");
			}
		}
		System.out.println("Game Over :(");
	}

	
	static int diceSum2() {
		// for the second roll when you get a point instead winning the first roll!
		Random r = new Random();
		int dice3 = r.nextInt(6) + 1;
		int dice4 = r.nextInt(6) + 1;
		int dieSum2 = dice3 + dice4;
		System.out.println("\t" + "Numbers are " + dice3 + " and " + dice4 + ". Sum is " + dieSum2 + ".");
		return dieSum2;
	}
		
		
	
	
	
	
	
	
	
	
	
}
