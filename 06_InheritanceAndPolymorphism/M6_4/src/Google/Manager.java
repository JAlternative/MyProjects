package Google;

import java.util.concurrent.ThreadLocalRandom;

public class Manager extends AbstractEmployee implements Employe {
    private int maximumManager = 1000 * FIVE_PROCENT / HUNGRED_PROCENT * FIVE_PROCENT;
    private int salary;
    private String name;

    public Manager(String name, int salary) {
        name = "Менеджер:";
        this.name = name;
        int result = salary / maximumManager;
        int salaryManager = getRandomSalary(0, result);
        this.salary = salaryManager + (salaryManager / 100 * 5); // + 5% бонусов от зп
    }

    @Override
    public String toString() {
        return name + salary + "р";
    }

    @Override
    public int getMonthSalary() {
        return salary;
    }

    @Override
    public String getNamePosition() {
        return name;
    }
}
