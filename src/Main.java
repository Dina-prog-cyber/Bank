public class Main {
    public static void main(String[] args) {
        Balance b1 = new Balance(10_000, Currency.RUB);
        Balance b2 = new Balance(10_000, Currency.USD); //Сумма передается в минимальных единицах
        System.out.println("Баланс 1 (100 рублей): " + b1);
        System.out.println("Баланс 2 (100 долларов): " + b2);


        Balance b3 = b2.add(b1);
        System.out.println("Прибавили ко второму балансу первый: " + b3);
        Balance b4 = b2.subtract(b1);
        System.out.println("Вычли из второго баланса первый: " + b4);

        BankAccount ac1 = new BankAccount(new Balance(50_000, Currency.RUB), 20_000, "14072006", "Dina");
        BankAccount ac2 = new BankAccount(new Balance(15_300, Currency.RUB), 25_000, "25091971", "Ilmira");

        System.out.println(ac1);
        System.out.println(ac2);

        ac1.deposit(new Balance(10_000, Currency.RUB));
        System.out.println("После пополнения:");
        System.out.println(ac1);
        ac2.withdraw(new Balance(100, Currency.EUR));
        System.out.println("После снятия:");
        System.out.println(ac2);

        System.out.println("Остаток дневного лимита для счета 2: " + ac2.getRemainLimit() + " копеек");

        ac1.transfer(ac2, new Balance(200, Currency.CNY));

        System.out.println("После перевода, счёт отправителя:");
        System.out.println(ac1);

        System.out.println("После перевода, счёт получателя:");
        System.out.println(ac2);

        ac1.resetLimitToday();
        System.out.println("Лимит счета 1 после обнуления: " + ac1.getRemainLimit());
    }
}