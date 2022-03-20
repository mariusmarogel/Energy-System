package business;

import java.util.ArrayList;

public interface Client extends Business {
    /**
     * Used to send money to seller.
     * @param amount of money to be sent
     */
    void pay(int amount);

    /**
     * @param amount of money required
     * @return true if the client can pay the required sum.
     */
    boolean canPay(int amount);

    /**
     * Checks if client satisfies the conditions to get a new contract with a seller.
     * @return true if conditions are met.
     */
    boolean canChangeSeller();

    /**
     * Select the best seller, based on their offer.
     * @param sellers list of all sellers
     */
    void changeSeller(ArrayList<Seller> sellers);

    /**
     * Sets the current seller to null.
     */
    void noSeller();
}
