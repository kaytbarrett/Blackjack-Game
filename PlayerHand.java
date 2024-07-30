/**
 * This class extends the `Hand` class to provide functionality specific to the player's hand. 
 * It includes methods to display the player's hand and print the total score.
 * 
 * @author Katherine (Katie) Barrett
 * @version 1.0
 */
public class PlayerHand extends Hand{

    /**
     * Constructs a new player's hand by calling the base Hand class constructor.
     */
    public PlayerHand()
    {
        super(); // Initialize the hand using the base class constructor
    }

    /**
     * Displays the player's hand and the total score.
     */
    public void showPlayerHand()
    {
        // Iterate over each card in the hand to display them
        for (String card : hand)
        {
            System.out.println("\t" + card); // Print each card in the player's hand
        }

        // Display the total score of the player's hand
        System.out.println("Score: " + cardsTotal());
    }
    
}
