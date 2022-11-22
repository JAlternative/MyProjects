package Google;

import java.util.concurrent.ThreadLocalRandom;

public class Operator extends AbstractEmployee implements Employe {
    private int maximumOperator = 1000 * 10 / 100 * 5;  //5 процентов, от пяти процентов дохода компании, тоесть 1 сотрудник получит максимум 25 тысяч рублей, если доход компании равен 10 миллионов рублей
    private int bonusAndSalary = maximumOperator / 100 * 5 + maximumOperator; //бонус в виде 5% от заработанных денег  + зарплата

    private String nameOperator;
    private int salaryOperator;


    public Operator(String name, int salaryOperator){
        name = "Оператор:";
        this.nameOperator = name;
        int result = salaryOperator / bonusAndSalary;
        this.salaryOperator = getRandomSalary(0, result);
    }
    @Override
    public String toString() {
        return nameOperator + salaryOperator+"р";
    }

    @Override
    public int getMonthSalary() {
        return salaryOperator;
    }

    @Override
    public String getNamePosition() {
        return nameOperator;
    }

}
