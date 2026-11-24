public final class Balance {
    private final long summa;
    private final Currency cur;

    public Balance(long summa, Currency curr) {
        if (summa < 0)
            throw new IllegalArgumentException("Отрицательная сумма");
        if (curr == null)
            throw new IllegalArgumentException("Не указана валюта");
        this.summa = convert(summa, curr, Currency.RUB);//Переводим сразу в рубли и храним в рублях
        this.cur = Currency.RUB;
    }

    private Balance(long summa) {
        if (summa < 0)
            throw new IllegalArgumentException("Отрицательная сумма");
        this.summa = summa;
        this.cur = Currency.RUB;
    }

    public Balance add(Balance b1) {
        if (b1 == null)
            throw new IllegalArgumentException("Нет суммы");
        long newSum = Math.addExact(this.summa, b1.summa);
        return new Balance(newSum);
    }

    public Balance subtract(Balance b1) {
        if (b1 == null)
            throw new IllegalArgumentException("Нет суммы");
        if (b1.summa > this.summa)
            throw new IllegalArgumentException("Отрицательный баланс");
        long newSum = Math.subtractExact(this.summa, b1.summa);
        return new Balance(newSum);
    }

    public long convert(long summa, Currency from, Currency to) {
        if (summa < 0)
            throw new IllegalArgumentException("Отрицательная сумма");
        if (from == null || to == null)
            throw new IllegalArgumentException("Нет валюты");
        long rubKopecks = Math.multiplyExact(
                summa, from.getPriceInRublesKopecks()
        ) / 100;
        return Math.multiplyExact(rubKopecks, 100)
                / to.getPriceInRublesKopecks();
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