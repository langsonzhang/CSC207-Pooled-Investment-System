package entities.containers;

import entities.assets.Asset;
import entities.identification.Identifiable;
import entities.users.User;
import java.io.Serializable;
import java.util.Date;

// An immutable decision for a transaction.
// A transaction has an INITIATOR, buying and selling assets, and a timestamp.
// The buying and selling value should be equal.
public final class Transaction extends Identifiable implements Serializable{

    public final User initiator;
    public final Asset sell;
    public final Asset buy;
    public final Date date;

    public Transaction(User initiator, Asset sell, Asset buy){
        super();
        this.initiator = initiator;
        this.sell = sell;
        this.buy = buy;
        this.date = new Date();
    }

    // Check the validity of the transaction
    public boolean checkIsValid(){
        return this.sell.getValue() == this.buy.getValue();
    }

    @Override
    public String toString(){
        return String.format("%s: Initiator: %s, Long: %s, Short: %s, Total Price: %f \n", this.date.toString(),
                this.initiator.getName(), this.buy.getSymbol(), this.sell.getSymbol(),  this.buy.getValue());
    }
}
