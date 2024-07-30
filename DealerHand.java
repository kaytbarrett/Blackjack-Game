/**
 * This class extends the `Hand` class to provide functionality specific to the dealer's hand. 
 * It includes methods to show the dealer's hand with one card hidden, as required in Blackjack.
 * 
 * @author Katherine (Katie) Barrett
 * @version 1.0
 */
public class DealerHand extends Hand {
    
    /**
     * Constructs a new dealer's hand by calling the base Hand class constructor.
     */
    public DealerHand() {
        super(); // Initialize the hand using the base class constructor
    }

    /**
     * Displays the dealer's hand, hiding the first card and showing the rest.
     */
    public void showDealerHand()
    {
        // Iterate over each card in the hand to display them
        for (int i = 0; i < hand.size(); i++)
        {
            if (i == 0)
            {
                // Hide the first card
                System.out.println("\tHidden");
            } 
            else
            {
                // Show the remaining cards
                System.out.println("\t" + hand.get(i));
            }
        }
    }
}
