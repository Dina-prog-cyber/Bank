
public class Main{
    public static void main(String[] args) {
        Balance b1 = new Balance(10_000, Currency.RUB);
        Balance b2 = new Balance(100, Currency.USD);
        System.out.println("Баланс 1 (100 рублей): " + b1);
        System.out.println("Баланс 2 (100 долларов): " + b2);

        Balance b3 = b2.add(b1);
        System.out.println("Прибавили ко второму балансу первый: " + b3);
        Balance b4 = b2.subtract(b1);
        System.out.println("Вычли из второго баланса первый: " + b4);

        BankAccount ac1 = new BankAccount(new Balance(50_000, Currency.RUB), 20_000, "14072006", "Dina");
        BankAccount ac2 = new BankAccount(new Balance(15_300, Currency.RUB), 25_000, "25091971", "Ilmira");

        System.out.println("Счёт 1: " + ac1.getBalance());
        System.out.println("Счёт 2: " + ac2.getBalance());

        ac1.deposit(new Balance(10_000, Currency.RUB));
        System.out.println("Счёт 1 после внесения 10_000 копеек: " + ac1.getBalance());
        ac2.withdraw(new Balance(1, Currency.EUR));
        System.out.println("Счёт 2 после снятия 1 евро: " + ac2.getBalance());

        System.out.println("Остаток дневного лимита для счета 2: " + ac2.getRemainLimit());

        ac1.transfer(ac2, new Balance(2, Currency.CNY));
        System.out.println("Счет 1 после перевода 2 юаней на счет 2: " + ac1.getBalance());
        System.out.println("Счет 2 после перевода 2 юаней на счет 2: " + ac2.getBalance());

        ac1.resetLimitToday();
        System.out.println("Лимит счета 1 после обнуления: " + ac1.getRemainLimit());
    }
}