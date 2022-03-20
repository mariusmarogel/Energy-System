package factories;

import entities.Consumer;
import entities.Distributor;
import business.Business;

public final class BusinessFactory {
    private static BusinessFactory instance = null;

    private BusinessFactory() {

    }

    /**
     * Singleton pattern.
     * @return instance of the class.
     */
    public static BusinessFactory getInstance() {
        if (instance == null) {
            instance = new BusinessFactory();
        }
        return instance;
    }

    /**
     * Factory pattern.
     * @param type specify the required business type.
     * @return new instance of entity which implements Business.
     */
    public Business getBusiness(final String type) {
        return switch (type) {
            case "consumer" -> new Consumer();
            case "distributor" -> new Distributor();
            default -> throw new IllegalStateException("Unexpected value: " + type);
        };
    }
}
