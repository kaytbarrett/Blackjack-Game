import java.util.ArrayList;
import java.util.Collections;

/**
 * This class manages a deck of cards, including initializing the deck with all standard cards,
 * shuffling the deck, and dealing cards from it. The deck is represented as a list of card strings,
 * each formatted as "value of suit".
 * 
 * @author Katherine (Katie) Barrett
 * @version 1.0
 */
public class Deck {
    
    /**
     * List to store the deck of cards.
     */
    private ArrayList<String> cards; // List to store the deck of cards

    /**
     * Constructs a new deck of cards, initializes it, and shuffles it.
     */
    public Deck()
    {
        cards = new ArrayList<>(); // Initialize the cards list
        initializeCards(); // Populate the deck with cards
        shuffleDeck(); // Shuffle the deck to randomize card order
    }

    /**
     * This method initializes the deck with 52 cards, including all combinations of suits and values.
     */
    private void initializeCards()
    {
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"}; // Define the four suits
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"}; // Define the card values

        // Loop through each suit and value to create all possible cards
        for (String suit : suits)
        {
            for (String value: values)
            {
                cards.add(value + " of " + suit); // Add card to the deck
            }
        }
    }

    /**
     * This method shuffles the deck to ensure the cards are in random order.
     */
    public void shuffleDeck()
    {
        Collections.shuffle(cards); // Shuffle the deck
    }

    /**
     * This method deals the top card from the deck.
     *
     * @return The card dealt from the top of the deck.
     */
    public String dealCard()
    {
        return cards.remove(cards.size() - 1); // Remove and return the last card in the deck
    }
    
}

