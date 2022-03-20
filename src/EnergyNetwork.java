import business.Seller;
import entities.Consumer;
import entities.Distributor;
import iofiles.IOController;
import iofiles.InputDataType;
import iofiles.OutputDataType;

import java.io.IOException;
import java.util.ArrayList;

public class EnergyNetwork {
    private final ArrayList<Consumer> consumers;
    private final ArrayList<Distributor> distributors;
    private final ArrayList<InputDataType.MonthlyUpdate> monthlyUpdates;
    private final int numTurns;

    EnergyNetwork(final String in) throws IOException {
        InputDataType inputDataType = IOController.getInstance().parseInput(in);
        this.numTurns = inputDataType.getNumberOfTurns();
        this.consumers = inputDataType.getInitialData().getConsumers();
        this.distributors = inputDataType.getInitialData().getDistributors();
        this.monthlyUpdates = inputDataType.getMonthlyUpdates();
    }

    private void readUpdate(final int month) {
        consumers.addAll(monthlyUpdates.get(month).getNewConsumers());
        for (InputDataType.CostChange cc : monthlyUpdates.get(month).getCostsChanges()) {
            for (Distributor d : distributors) {
                if (d.getId() == cc.getId()) {
                    d.setInfrastructureCost(cc.getInfrastructureCost());
                    d.setProductionCost(cc.getProductionCost());
                }
            }
        }
    }

    private void monthStart() {
        ArrayList<Seller> sellers = new ArrayList<>(distributors);
        for (Consumer c : consumers) {
            if (!c.getBankrupt()) {
                c.monthStart();
                c.changeSeller(sellers);
            }
        }
        for (Distributor d : distributors) {
            d.monthStart();
        }
    }

    private void monthEnd() {
        for (Distributor d : distributors) {
            d.monthEnd();
        }
        for (Consumer c : consumers) {
            c.monthEnd();
        }
    }

    /**
     * Based on the input data, will simulate the outcome.
     */
    public final void execute() {
        for (int i = 0; i <= numTurns; ++i) {
            if (i > 0) {
                readUpdate(i - 1);
            }
            monthStart();
            monthEnd();
        }
    }

    /**
     * Converts the result to a json-printable class.
     * @return instance of OutputDataType in order to be printed to file.
     */
    public final OutputDataType toOutput() {
        return  new OutputDataType(consumers, distributors);
    }
}
