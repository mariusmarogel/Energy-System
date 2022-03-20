package entities;

import business.Client;
import business.Contract;
import business.Seller;
import com.fasterxml.jackson.annotation.JsonIgnore;
import utils.Constants;

import java.util.ArrayList;
import java.util.HashMap;

public class Distributor implements Seller {
    private int id;
    private int contractLength;
    private int initialBudget;
    private int initialInfrastructureCost;
    private int initialProductionCost;
    private int budget;
    private int infrastructureCost;
    private int productionCost;
    private boolean bankrupt;

    @JsonIgnore
    private final HashMap<Client, Contract> contracts;  // True if client has not payed last month
    private final ArrayList<Client> clientsToRemove;

    public Distributor() {
        this.bankrupt = false;
        this.contracts = new HashMap<>();
        this.clientsToRemove = new ArrayList<>();
    }

    public final int getContractLength() {
        return contractLength;
    }

    public final void setContractLength(final int contractLength) {
        this.contractLength = contractLength;
    }

    public final int getInitialBudget() {
        return initialBudget;
    }

    /**
     * Setter for initial budget.
     * Also sets the value for budget.
     * @param initialBudget value to be set.
     */
    public final void setInitialBudget(final int initialBudget) {
        this.initialBudget = initialBudget;
        this.budget = initialBudget;
    }

    public final int getInitialInfrastructureCost() {
        return initialInfrastructureCost;
    }

    /**
     * Setter for initial infrastructure cost.
     * Also sets the value for infrastructure cost.
     * @param initialInfrastructureCost value to be set.
     */
    public final void setInitialInfrastructureCost(final int initialInfrastructureCost) {
        this.initialInfrastructureCost = initialInfrastructureCost;
        this.infrastructureCost = initialInfrastructureCost;
    }

    public final int getInitialProductionCost() {
        return initialProductionCost;
    }

    /**
     * Setter for initial production cost.
     * Also sets the value for production cost.
     * @param initialProductionCost value to be set.
     */
    public final void setInitialProductionCost(final int initialProductionCost) {
        this.initialProductionCost = initialProductionCost;
        this.productionCost = initialProductionCost;
    }

    public final int getBudget() {
        return budget;
    }

    public final void setBudget(final int budget) {
        this.budget = budget;
    }

    public final int getInfrastructureCost() {
        return infrastructureCost;
    }

    public final void setInfrastructureCost(final int infrastructureCost) {
        this.infrastructureCost = infrastructureCost;
    }

    public final int getProductionCost() {
        return productionCost;
    }

    public final void setProductionCost(final int productionCost) {
        this.productionCost = productionCost;
    }

    public final HashMap<Client, Contract> getContracts() {
        return contracts;
    }

    private void earnMoneyFromContract(final Client client) {
        int sumToPay = getPrice(client);
        if (!client.canPay(sumToPay) && contracts.get(client).getUnpaidFee() == 0) {
            contracts.get(client).setUnpaidFee(sumToPay);
        } else {
            if (client.canPay(sumToPay)) {
                this.budget += sumToPay;
            } else {
                clientsToRemove.add(client);
            }
            client.pay(sumToPay);
        }
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
     * Based on the number of clients, propose an offer to a possible new client.
     * @return value of the offer
     */
    @Override
    public final int getOffer() {
        int profit = (int) Math.round(Math.floor(Constants.PROFIT_RATIO * productionCost));
        if (contracts.isEmpty()) {
            return infrastructureCost + productionCost + profit;
        }
        return (int) Math.round(Math.floor((double) infrastructureCost
                                        / contracts.size()) + productionCost + profit);
    }

    /**
     * Request the value of the contract and verify if there is an unpaid fee. If so,
     * request additional money for it.
     * @param client is a client that has a contract with this seller.
     * @return total value to pay
     */
    @Override
    public final int getPrice(final Client client) {
        int p = contracts.get(client).getPrice();
        int o = contracts.get(client).getUnpaidFee();
        return (int) (Math.round(Math.floor(Constants.PENALTY_RATIO * o)) + p);
    }

    @Override
    public final void addContract(final Client client) {
        this.contracts.put(client, new Contract(client.getId(), getOffer(), contractLength));
    }

    @Override
    public final void renewContract(final Client client) {
        if (contracts.getOrDefault(client, null) != null) {
            contracts.get(client).setPrice(getOffer());
            contracts.get(client).setRemainedContractMonths(contractLength);
        }
    }

    @Override
    public final void removeContract(final Client client) {
        this.contracts.remove(client);
        client.noSeller();
    }

    @Override
    public final int contractDuration(final Client client) {
        return contracts.get(client).getRemainedContractMonths();
    }

    @Override
    public final void monthStart() {
        //
    }

    /**
     * Get money from clients, update the remaining time of contracts, update budget and
     * remove bankrupt clients.
     */
    @Override
    public final void monthEnd() {
        if (bankrupt) {
            return;
        }
        for (Client client : contracts.keySet()) {
            earnMoneyFromContract(client);
            contracts.get(client).monthPassed();
        }
        this.budget -= infrastructureCost + contracts.size() * productionCost;
        if (this.budget <= 0) {
            this.bankrupt = true;
            clientsToRemove.addAll(contracts.keySet());
        }
        for (Client c : clientsToRemove) {
            removeContract(c);
        }
        clientsToRemove.clear();
    }
}
