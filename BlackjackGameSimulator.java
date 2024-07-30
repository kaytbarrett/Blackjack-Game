import java.util.Scanner;

/**
 * A simple Blackjack game simulator.
 * 
 * This class contains the main method to run a Blackjack game. The user can choose to play the game and
 * place bets. The game continues until the user decides to stop or runs out of money.
 * 
 * @author Katherine (Katie) Barrett
 * @version 1.0
 * 
 * References used for this assignment: 
 * Module 7 Office Hours Presentation (slides talk about ArrayList and Collections)
 * JavadocDemo.pdf
 */
public class BlackjackGameSimulator {

    /** Scanner object for user input */
    private static Scanner input = new Scanner(System.in);

     /**
     * The entry point of the Blackjack game simulator.
     * 
     * Prompts the user to play the game, handles betting, and updates the total amount of money
     * based on the outcome of the game. The game continues in a loop until the user chooses to
     * stop or runs out of money.
     * 
     * @param args command line arguments (not used)
     */
    public static void main (String[] args) {

        String response; // To store user response for whether they want to play
        int totalAmount = 100; // Player's starting money
        int betAmount = 0; // Amount the player bets in each round
        boolean wantsToPlay = true; // Boolean to control the main game loop

        // Loop to handle betting and playing the game
        while (wantsToPlay)
        {
            System.out.println("\nDo you want to play a game of Blackjack? (y/n)");
            response = input.nextLine(); // Get user's response

            if (response.equals("y"))
            {
                while (true) {
                    if (totalAmount <= 0) {
                        System.out.println("You are out of money. Game over.");
                        System.exit(0); // Exit program if the player is out of money
                    }

                    System.out.println("You have $" + totalAmount + ". How much do you want to bet? (integers only)");
                    betAmount = input.nextInt(); // Get player's bet
                    input.nextLine(); // Consume the newline character
    
                    // Validate bet amount
                    if (betAmount > 0 && betAmount <= totalAmount) {
                        break; // Exit loop if bet is valid
                    } else {
                        System.out.println("Invalid bet amount. Please enter a value between 1 and " + totalAmount + ".");
                    }
                }
    
                // Play the game and get the result
                String game = playGame();
    
                // Update totalAmount based on the result of the game
                if (game.equals("Lose"))
                {
                    totalAmount -= betAmount; // Deduct bet amount if the player loses
                } else if (game.equals("Win"))
                {
                    totalAmount += betAmount; // Add bet amount if the player wins
                }
    
            } else if (response.equals("n"))
            {
                wantsToPlay = false; // Exit the game loop if the player chooses not to play
            } else
            {
                System.out.println("Please enter 'y' or 'n'."); // Prompt for valid input
            }
        }
    }

    /**
     * This method contains the logic for playing the game, including dealing cards, managing
     * the player's and dealer's hands, and determining the winner. 
     * 
     * @return a String indicating the outcome of the game: "Win", "Lose", or "Draw".
     */
    private static String playGame()
    {
        // Create a new deck of cards
        Deck deck = new Deck();
        
        // Create hands for the player and dealer
        PlayerHand playerHand = new PlayerHand();
        DealerHand dealerHand = new DealerHand();
    
        // Deal two cards to both the player and dealer
        playerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
        playerHand.addCard(deck.dealCard());
        dealerHand.addCard(deck.dealCard());
    
        // Show the dealer's hand (one card face-up, one card face-down)
        System.out.println("\nDealer hand:");
        dealerHand.showDealerHand();
        System.out.println();
    
        // Show the player's hand
        System.out.println("\nYour hand:");
        playerHand.showPlayerHand();
        System.out.println();
    
        // Check if the player has drawn 21 and wins immediately
        if (playerHand.cardsTotal() == 21){
            System.out.println("You drew 21 - You win!");
            return "Win";
        } else 
        {
            // Player's turn to draw more cards or stand
            String playerTurn = playerTurn(deck, playerHand);
            
            // Dealer's turn to draw more cards or stand
            String dealerTurn = dealerTurn(deck, dealerHand, playerHand);
    
            // Determine the result based on the final hands of the player and dealer
            if (playerTurn == null && dealerTurn == null)
            {
                // Both player and dealer have finished their turns without determining the winner; find the winner
                return findWinner(dealerHand.cardsTotal(), playerHand.cardsTotal());
            } else if (playerTurn != null)
            {
                // Player's turn determined the winner, return that result
                return playerTurn;
            } else
            {
                // Dealer's turn determined the winner, return that result
                return dealerTurn;
            }
        }
    }

    /**
     * This method allows the player to choose whether to hit (draw a card) or hold (stop drawing cards).
     * The player's hand is updated based on their choice, and the game outcome is determined if the player
     * reaches 21 or exceeds it. If the player chooses to hold, the method exits the loop and returns null.
     * 
     * @param deck the Deck object used to deal cards to the player
     * @param playerHand the PlayerHand object representing the player's hand
     * @return a String indicating the outcome of the player's turn: "Win" if the player draws 21, 
     *         "Lose" if the player overdrew, or null if the player holds
     */
    private static String playerTurn(Deck deck, PlayerHand playerHand)
    {
        Boolean requestedHit = true; // Boolean to control the loop for player's turn
    
        while (requestedHit) // Loop to allow player to hit or hold
        {
            int hitResponse; // Variable to store player's choice (hit or hold)
            System.out.println("Hit (1) or hold (0)?");
            hitResponse = input.nextInt(); // Get player's choice
            input.nextLine(); // Consume newline character
    
            if (hitResponse == 1)
            {
                // Player chooses to hit; deal a new card
                playerHand.addCard(deck.dealCard());
                System.out.println("\nYour hand:");
                playerHand.showPlayerHand(); // Show updated hand
                System.out.println();
    
                // Check if the player has hit 21 or overdrew
                if (playerHand.cardsTotal() == 21){
                    System.out.println("You drew 21 - You win!");
                    requestedHit = false; // End the player's turn
                    return "Win"; // Return result
                } else if (playerHand.cardsTotal() > 21)
                {
                    System.out.println("You overdrew - Dealer wins!");
                    requestedHit = false; // End the player's turn
                    return "Lose"; // Return result
                }
            } else if (hitResponse == 0)
            {
                // Player chooses to hold; end their turn
                requestedHit = false;
            } else
            {
                // Invalid input; prompt the player to enter a valid option
                System.out.println("Please enter '1' or '0'");
            }    
        }
        // Return null if the player's card total is less than 21
        return null;
    }

    /**
     * This method manages the dealer's actions based on the dealer's current hand and the player's hand.
     * If the dealer has 21 at the start, the dealer wins immediately. Otherwise, the dealer continues
     * to draw cards until the dealer's hand reaches at least 17. 
     * 
     * @param deck the Deck object used to deal cards to the dealer
     * @param dealerHand the DealerHand object representing the dealer's hand
     * @param playerHand the PlayerHand object representing the player's hand (used to check if player already hit 21 cards or more)
     * @return a String indicating the outcome of the dealer's turn: "Lose" if the dealer wins, "Win" if the player wins, or null otherwise
     */
    private static String dealerTurn(Deck deck, DealerHand dealerHand, PlayerHand playerHand)
    {
        // Check if the dealer has already drawn 21
        if (dealerHand.cardsTotal() == 21)
        {
            System.out.println("Dealer drew 21 - Dealer wins!");
            return "Lose"; // Player loses if dealer hits 21
        } 
        else if (playerHand.cardsTotal() < 21) // Continue if player has less than 21
        {
            // Dealer must hit until their hand totals at least 17
            while(dealerHand.cardsTotal() < 17)
            {
                dealerHand.addCard(deck.dealCard()); // Deal a card to the dealer
                System.out.println("\nDealer hits.");
                System.out.println("Dealer hand:");
                dealerHand.showDealerHand(); // Show dealer's updated hand
                System.out.println();
    
                // Check if the dealer has drawn 21
                if (dealerHand.cardsTotal() == 21 )
                {
                    System.out.println("Dealer got 21 - Dealer wins!");
                    return "Lose"; // Dealer wins if they reach 21
                } 
                else if (dealerHand.cardsTotal() > 21)
                {
                    System.out.println("Dealer went over 21 - You win!");
                    return "Win"; // Player wins if dealer exceeds 21
                }
            }
        }
        // Return null if dealer's hand is between 17 and 20 
        return null;
    }
    

    /**
     * This method determines the winner of the Blackjack game based on the final totals of the dealer and the player.
     * It considers only the cases where neither the dealer nor the player has exceeded 21. 
     * If both have the same total, the game is a tie.
     * 
     * @param dealerTotal the final total of the dealer's hand
     * @param playerTotal the final total of the player's hand
     * @return a String indicating the outcome of the game: "Lose" if the dealer wins, "Win" if the player wins,
     *         or "Draw" if the totals are equal. Otherwise, it returns null. 
     */
    private static String findWinner(int dealerTotal, int playerTotal )
    {
        // Determine the outcome of the game if neither dealer nor player has 21 or greater
        if(dealerTotal < 21 && playerTotal < 21) 
        {
            // Compare dealer's and player's totals to decide the winner
            if (dealerTotal > playerTotal) {
                System.out.println("Dealer wins with " + dealerTotal + " against " + playerTotal);
                return "Lose"; // Dealer wins if their total is higher
            } else if (playerTotal > dealerTotal) {
                System.out.println("You win with " + playerTotal + " against " + dealerTotal);
                return "Win"; // Player wins if their total is higher
            } else {
                System.out.println("It's a tie with " + playerTotal);
                return "Draw"; // It's a tie if both totals are equal
            }
        } 
        // Return null otherwise
        return null;
    }    
}

