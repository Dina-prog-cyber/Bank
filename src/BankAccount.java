public class BankAccount {
    private Balance bal;
    private final long limit;
    private long limitTodayKop = 0L;
    private final String id;
    private final String name;

    public BankAccount(Balance b, long limit, String id, String name) {
        if (b == null)
            throw new IllegalArgumentException("Не указан баланс");
        if (id == null)
            throw new IllegalArgumentException("Пустой идентификатор");
        if (name == null)
            throw new IllegalArgumentException("Пустое имя");
        if (limit < 0)
            throw new IllegalArgumentException("Отрицательный лимит");
        this.bal = b;
        this.limit = limit;
        this.id = id;
        this.name = name;
    }

    public void deposit(Balance sum) {
        if (sum == null)
            throw new IllegalArgumentException("Не указана сумма");
        if (sum.getSumma() <= 0)
            throw new IllegalArgumentException("Отрицательный сумма");
        bal = bal.add(sum);
    }

    public void withdraw(Balance sum) {
        if (sum == null)
            throw new IllegalArgumentException("Не указана сумма");
        if (sum.getSumma() <= 0)
            throw new IllegalArgumentException("Отрицательный сумма");
        if (!enoughSum(sum))
            throw new IllegalArgumentException("Недостаточно средств или превышен лимит");
        long newTodayLimit = Math.addExact(sum.getSumma(), limitTodayKop);
        Balance newBal = bal.subtract(sum);
        bal = newBal;
        limitTodayKop = newTodayLimit;
    }

    public void transfer(BankAccount receiver, Balance sum) {
        if (receiver == null)
            throw new IllegalArgumentException("Нет получателя");
        if (receiver == this)
            throw new IllegalArgumentException("Отправка самому себе");
        if (sum == null)
            throw new IllegalArgumentException("Не указана сумма");
        if (sum.getSumma() <= 0)
            throw new IllegalArgumentException("Отрицательная умма");
        if (!enoughSum(sum))
            throw new IllegalArgumentException("Недостаточно средств или превышен лимит");
        long newTodayLimit = Math.addExact(limitTodayKop, sum.getSumma());
        Balance newSendBal = bal.subtract(sum);
        Balance newReceiveBal = receiver.bal.add(sum);
        bal = newSendBal;
        receiver.bal = newReceiveBal;
        limitTodayKop = newTodayLimit;
    }

    public Balance getBalance() {
        return bal;
    }

    public boolean enoughSum(Balance sum) {
        if (sum == null)
            throw new IllegalArgumentException("Нет суммы");
        return bal.getSumma() >= sum.getSumma() && sum.getSumma() <= getRemainLimit();
    }

    public long getRemainLimit() {
        return Math.max(0L, limit - limitTodayKop);
    }

    public void resetLimitToday() {
        limitTodayKop = 0L;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    @Override
    public String toString() {
        return "Счёт: " + id +
                ", владелец: " + name +
                ", баланс: " + bal +
                ", дневной лимит: " + limit + " копеек" +
                ", использовано за день: " + limitTodayKop + " копеек" +
                ", осталось лимита: " + getRemainLimit() + " копеек";
    }
}
