package iofiles;

import business.Contract;
import entities.Consumer;
import entities.Distributor;

import java.util.ArrayList;
import java.util.Comparator;

public class OutputDataType {
    private ArrayList<ConsumerOutput> consumers;
    private ArrayList<DistributorOutput> distributors;

    public OutputDataType(final ArrayList<Consumer> cons, final ArrayList<Distributor> dist) {
        this.consumers = new ArrayList<>();
        this.distributors = new ArrayList<>();
        for (Consumer c : cons) {
            this.consumers.add(new ConsumerOutput(c));
        }
        for (Distributor d : dist) {
            this.distributors.add(new DistributorOutput(d));
        }
    }

    public final ArrayList<ConsumerOutput> getConsumers() {
        return consumers;
    }

    public final void setConsumers(final ArrayList<ConsumerOutput> consumers) {
        this.consumers = consumers;
    }

    public final  ArrayList<DistributorOutput> getDistributors() {
        return distributors;
    }

    public final void setDistributors(final ArrayList<DistributorOutput> distributors) {
        this.distributors = distributors;
    }

    public static class ConsumerOutput {
        private int id;
        private boolean isBankrupt;
        private int budget;

        ConsumerOutput(final Consumer consumer) {
            this.id = consumer.getId();
            this.budget = consumer.getBudget();
            this.isBankrupt = consumer.getBankrupt();
        }

        public final int getId() {
            return id;
        }

        public final void setId(final int id) {
            this.id = id;
        }

        public final int getBudget() {
            return budget;
        }

        public final void setBudget(final int budget) {
            this.budget = budget;
        }

        public final boolean getIsBankrupt() {
            return isBankrupt;
        }

        public final void setIsBankrupt(final boolean bankrupt) {
            isBankrupt = bankrupt;
        }
    }

    public static class DistributorOutput {
        private int id;
        private int budget;
        private boolean isBankrupt;
        private ArrayList<Contract> contracts;

        DistributorOutput(final Distributor distributor) {
            this.id = distributor.getId();
            this.budget = distributor.getBudget();
            this.isBankrupt = distributor.getBankrupt();
            this.contracts = new ArrayList<>();
            this.contracts.addAll(distributor.getContracts().values());
            this.contracts.sort(Comparator.comparingInt(Contract::getRemainedContractMonths));
            this.contracts.sort(new Comparator<Contract>() {
                @Override
                public int compare(final Contract o1, final Contract o2) {
                    if (o1.getRemainedContractMonths() == o2.getRemainedContractMonths()) {
                        return o1.getConsumerId() - o2.getConsumerId();
                    }
                    return o1.getRemainedContractMonths() - o2.getRemainedContractMonths();
                }
            });
        }

        public final int getId() {
            return id;
        }

        public final void setId(final int id) {
            this.id = id;
        }

        public final int getBudget() {
            return budget;
        }

        public final void setBudget(final int budget) {
            this.budget = budget;
        }

        public final boolean getIsBankrupt() {
            return isBankrupt;
        }

        public final void setIsBankrupt(final boolean bankrupt) {
            isBankrupt = bankrupt;
        }

        public final ArrayList<Contract> getContracts() {
            return contracts;
        }

        public final void setContracts(final ArrayList<Contract> contracts) {
            this.contracts = contracts;
        }
    }
}
