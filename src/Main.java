import iofiles.IOController;

public class Main {
    protected Main() {
    }

    /**
     * Entrypoint to my implementation.
     * @param args input and output files.
     * @throws Exception from writeFile()
     */
    public static void main(final String[] args) throws Exception {
        EnergyNetwork energyNetwork = new EnergyNetwork(args[0]);
        energyNetwork.execute();
        IOController.getInstance().writeFile(args[1], energyNetwork.toOutput());
    }
}
