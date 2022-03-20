package business;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class Contract {
    private int consumerId;
    private int price;
    @JsonIgnore
    private int unpaidFee;
    private int remainedContractMonths;

    public final int getConsumerId() {
        return consumerId;
    }

    public final void setConsumerId(final int consumerId) {
        this.consumerId = consumerId;
    }

    public final int getPrice() {
        return price;
    }

    public final void setPrice(final int price) {
        this.price = price;
    }

    public final int getUnpaidFee() {
        return unpaidFee;
    }

    public final void setUnpaidFee(final int unpaidFee) {
        this.unpaidFee = unpaidFee;
    }

    public final int getRemainedContractMonths() {
        return remainedContractMonths;
    }

    public final void setRemainedContractMonths(final int remainedContractMonths) {
        this.remainedContractMonths = remainedContractMonths;
    }

    public Contract(final int consumerId, final int price, final int remainedContractMonths) {
        this.consumerId = consumerId;
        this.price = price;
        this.unpaidFee = 0;
        this.remainedContractMonths = remainedContractMonths;
    }

    /**
     * Decrease the remainingContractMonths each month.
     */
    public final void monthPassed() {
        this.remainedContractMonths--;
    }
}
