package business;

import com.fasterxml.jackson.annotation.JsonGetter;

public interface Business {
    /**
     * Getter for id.
     * @return id of the business entity.
     */
    int getId();

    /**
     * Setter for id.
     * @param id new id.
     */
    void setId(int id);

    /**
     * Getter for bankruptcy.
     * @return whether the entity is bankrupt or not.
     */
    @JsonGetter("isBankrupt")
    boolean getBankrupt();

    /**
     * Setter for bankrupt.
     * @param bankrupt new bankrupt state.
     */
    void setBankrupt(boolean bankrupt);

    /**
     * Makes a business perform the actions for the start of the month.
     */
    void monthStart();

    /**
     * Makes a business perform the actions for the end of the month.
     */
    void monthEnd();
}
