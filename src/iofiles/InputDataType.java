package iofiles;

import entities.Consumer;
import entities.Distributor;

import java.util.ArrayList;

public class InputDataType {
    private int numberOfTurns;
    private InitialData initialData;
    private ArrayList<MonthlyUpdate> monthlyUpdates;

    public final int getNumberOfTurns() {
        return numberOfTurns;
    }

    public final void setNumberOfTurns(final int numberOfTurns) {
        this.numberOfTurns = numberOfTurns;
    }

    public final InitialData getInitialData() {
        return initialData;
    }

    public final void setInitialData(final InitialData initialData) {
        this.initialData = initialData;
    }

    public final ArrayList<MonthlyUpdate> getMonthlyUpdates() {
        return monthlyUpdates;
    }

    public final void setMonthlyUpdates(final ArrayList<MonthlyUpdate> monthlyUpdates) {
        this.monthlyUpdates = monthlyUpdates;
    }

    public static class InitialData {
        private ArrayList<Consumer> consumers;
        private ArrayList<Distributor> distributors;

        public final ArrayList<Consumer> getConsumers() {
            return consumers;
        }

        public final void setConsumers(final ArrayList<Consumer> consumers) {
            this.consumers = consumers;
        }

        public final ArrayList<Distributor> getDistributors() {
            return distributors;
        }

        public final void setDistributors(final ArrayList<Distributor> distributors) {
            this.distributors = distributors;
        }
    }

    public static class MonthlyUpdate {
        private ArrayList<Consumer> newConsumers;
        private ArrayList<CostChange> costsChanges;

        public final ArrayList<Consumer> getNewConsumers() {
            return newConsumers;
        }

        public final void setNewConsumers(final ArrayList<Consumer> newConsumers) {
            this.newConsumers = newConsumers;
        }

        public final ArrayList<CostChange> getCostsChanges() {
            return costsChanges;
        }

        public final void setCostsChanges(final ArrayList<CostChange> costsChanges) {
            this.costsChanges = costsChanges;
        }
    }

    public static class CostChange {
        private int id;
        private int infrastructureCost;
        private int productionCost;

        public final int getId() {
            return id;
        }

        public final void setId(final int id) {
            this.id = id;
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
    }
}
