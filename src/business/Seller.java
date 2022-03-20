package business;

public interface Seller extends Business {
    /**
     * @return price per month for a new contract.
     */
    int getOffer();

    /**
     * @param client is a client that has a contract with this seller.
     * @return the value that the client has to pay.
     */
    int getPrice(Client client);

    /**
     * @param client has accepted the offer, a new contract will be created.
     */
    void addContract(Client client);

    /**
     * @param client has accepted the offer, the terms of the contract will be updated.
     */
    void renewContract(Client client);

    /**
     * @param client is either bankrupt or the contract duration is over.
     */
    void removeContract(Client client);

    /**
     * @param client client that has contract with this seller.
     * @return remaining contract duration.
     */
    int contractDuration(Client client);
}
