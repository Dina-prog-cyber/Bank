public class BankAccount {
    private Balance bal;
    private final long limit;
    private long limitTodayKop = 0L;
    private final String id;
    private final String name;

    public BankAccount(Balance b, long limit, String id, String name) {
        if (b == null)
            throw new IllegalArgumentException("Не указан баланс");
        if(id == null)
            throw new IllegalArgumentException("Пустой идентификатор");
        if(name == null)
            throw new IllegalArgumentException("Пустое имя");
        if(limit < 0)
            throw new IllegalArgumentException("Отрицательный лимит");
        bal = b;
        this.limit = limit;
        this.id = id;
        this.name = name;
    }
    public void deposit(Balance sum){
        bal = bal.add(sum);
    }
    public void withdraw(Balance sum){
        if (sum == null)
            throw new IllegalArgumentException("Не указана сумма");
        if (!enoughSum(sum))
            throw new IllegalArgumentException("Недостаточно средств или превышен лимит");
        long newTodayLimit = Math.addExact(sum.getSumma(), limitTodayKop);
        bal = bal.subtract(sum);
        limitTodayKop = newTodayLimit;
    }
    public void transfer(BankAccount reciever, Balance sum){
        if(reciever == null)
            throw new IllegalArgumentException("Нет получателя");
        if(reciever == this)
            throw new IllegalArgumentException("Отправка самому себе");
        if (sum == null)
            throw new IllegalArgumentException("Не указана сумма");
        withdraw(sum);
        reciever.deposit(sum);
    }
    public Balance getBalance() {
        return bal;
    }
    public boolean enoughSum(Balance sum){
        if(sum == null)
            throw new IllegalArgumentException("Нет суммы");
        return bal.getSumma() >= sum.getSumma() && sum.getSumma() <= getRemainLimit();
    }
    public long getRemainLimit(){
        return limit - limitTodayKop;
    }
    public void resetLimitToday() {
        limitTodayKop = 0L;
    }
}
