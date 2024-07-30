import java.util.ArrayList;

/**
 * This class manages a collection of cards held in a hand, allowing cards to be added and the total
 * value of the hand to be calculated. The value of each card is determined according to Blackjack rules,
 * where face cards are worth 10, Aces can be worth 1 or 11, and numeric cards are worth their face value.
 * 
 * @author Katherine (Katie) Barrett
 * @version 1.0 
 */
public class Hand {

    /**
     * List to store the cards in hand.
     */
    protected ArrayList<String> hand; // List to store the cards in the hand

    /**
     * Constructs a new hand with an empty list of cards.
     */
    public Hand()
    {
        hand = new ArrayList<>(); // Initialize the hand list
    }

    /**
     * This method adds a card to the hand.
     *
     * @param card The card to be added to the hand.
     */
    public void addCard(String card)
    {
        if (card != null) // Check if the card is not null
        {
            hand.add(card); // Add the card to the hand
        }
    }

    /**
     * This method calculates the total value of the hand based on Blackjack rules.
     *
     * @return The total value of the hand.
     */
    public int cardsTotal()
    {
        int total = 0; // Initialize the total value

        // Iterate over each card in the hand to calculate the total value
        for (String card : hand)
        {
            // Get the rank of the card (first character)
            String first = card.substring(0, 1);

            // Add value based on the rank of the card
            if (first.equals("J") || first.equals("Q") || first.equals("K"))
            {
                total += 10; // Face cards are worth 10 points
            } 
            else if (first.equals("A"))
            {
                // Ace can be worth 11 or 1, depending on the current total
                if (total <= 10)
                {
                    total += 11; // Ace is worth 11 if it doesn't bust the hand
                } 
                else 
                {
                    total += 1; // Ace is worth 1 if it would bust the hand
                }
            } 
            else
            {
                // Handle number cards (2-10)
                String firstTwo = card.substring(0,2);
                if (firstTwo.equals("10"))
                {
                    total += 10; // '10' card is worth 10 points
                } 
                else
                {
                    int firstAsInt = Integer.parseInt(first); // Convert rank to integer
                    total += firstAsInt; // Add value based on rank
                }
            }
        }
        return total; // Return the total value of the hand
    }
    
}
