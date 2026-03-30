package fr.utc.miage.shares;

public class Transaction {
    private Action action;
    private Jour jour;
    private float price;

    public Transaction(Action action, Jour jour, float price) {
        this.action = action;
        this.jour = jour;
        this.price = price;
    }

    public Action getAction() {
        return action;
    }

    public void setAction(Action action) {
        this.action = action;
    }

    public Jour getJour() {
        return jour;
    }

    public void setJour(Jour jour) {
        this.jour = jour;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }
}
