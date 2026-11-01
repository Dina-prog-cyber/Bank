public final class Balance {
    private final long summa;
    private final Currency cur = Currency.RUB;

    public Balance(long summa, Currency curr) {
        if(summa < 0)
            throw new IllegalArgumentException("Отрицательная сумма");
        if (curr == null)
            throw new IllegalArgumentException("Не указана валюта");
        if(curr != cur)
            summa = convert(summa,curr);
        this.summa = summa;
    }
    public Balance add(Balance b1){
        if (b1 == null)
            throw new IllegalArgumentException("Нет суммы");
        long newSum = Math.addExact(this.summa, b1.summa);
        return new Balance(newSum, cur);
    }
    public Balance subtract(Balance b1){
        if (b1 == null)
            throw new IllegalArgumentException("Нет суммы");
        if(b1.summa > this.summa)
            throw new IllegalArgumentException("Отрицательный баланс");
        long newSum = this.summa - b1.summa;
        return new Balance(newSum, Currency.RUB);
    }
    public long convert(long sum, Currency c1){
        if (c1 == null)
            throw new IllegalArgumentException("Не указана валюта");
        return Math.multiplyExact(sum, c1.getPriceInRublesKopecks());
    }
    public long getSumma() {
        return summa;
    }
    public Currency getCur() {
        return cur;
    }
    @Override
    public String toString() {
        return summa + " копеек " + cur.getCode();
    }
}