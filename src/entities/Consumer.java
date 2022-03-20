package entities;

import business.Client;
import business.Seller;

import java.util.ArrayList;
import java.util.Comparator;

public class Consumer implements Client {
    private int id;
    private int initialBudget;
    private int monthlyIncome;
    private int budget;
    private boolean bankrupt;
    private Seller seller;

    public Consumer() {
        this.bankrupt = false;
    }

    public final int getInitialBudget() {
        return initialBudget;
    }

    /**
     * Setter for the initial budget.
     * Budget is also initialised.
     * @param initialBudget represents the value to be given.
     */
    public final void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
        this.budget = initialBudget;
    }

    public final int getMonthlyIncome() {
        return monthlyIncome;
    }

    public final void setMonthlyIncome(final int monthlyIncome) {
        this.monthlyIncome = monthlyIncome;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }

    @Override
    public final int getId() {
        return id;
    }

    @Override
    public final void setId(final int id) {
        this.id = id;
    }

    @Override
    public final boolean getBankrupt() {
        return bankrupt;
    }

    @Override
    public final void setBankrupt(final boolean bankrupt) {
        this.bankrupt = bankrupt;
    }

    /**
     * Will pay the money if its possible, otherwise will go bankrupt.
     * @param amount of money to be sent
     */
    @Override
    public final void pay(final int amount) {
        if (canPay(amount)) {
            this.budget -= amount;
        } else {
            this.bankrupt = true;
        }
    }

    @Override
    public final boolean canPay(final int amount) {
        return budget > amount;
    }

    @Override
    public final boolean canChangeSeller() {
        if (bankrupt) {
            return false;
        }
        if (seller == null) {
            return true;
        }
        if (seller.getBankrupt()) {
            return true;
        }
        return seller.contractDuration(this) <= 0;
    }

    /**
     * From the list of sellers choose the best based on offer.
     * If it is the old seller, only renew the contract.
     * @param sellers list of all sellers
     */
    @Override
    public final void changeSeller(final ArrayList<Seller> sellers) {
        if (!canChangeSeller()) {
            return;
        }
        sellers.sort(Comparator.comparingInt(Seller::getOffer));
        if (this.seller == sellers.get(0)) {
            this.seller.renewContract(this);
        }
        if (this.seller != null && this.seller != sellers.get(0)) {
            this.seller.removeContract(this);
            this.seller = sellers.get(0);
            this.seller.addContract(this);
        }
        if (this.seller == null) {
            this.seller = sellers.get(0);
            this.seller.addContract(this);
        }
    }

    @Override
    public final void noSeller() {
        this.seller = null;
    }

    /**
     * Earn monthly income.
     */
    @Override
    public final void monthStart() {
        this.budget += monthlyIncome;
    }

    @Override
    public final void monthEnd() {
        //
    }
}
