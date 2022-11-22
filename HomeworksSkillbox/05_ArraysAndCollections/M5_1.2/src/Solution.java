public class Solution {
    //static
    static double[] temperature = new double[30];

    public static void main(String[] args) {

        for (int a = 0; a < temperature.length; a++) {
            temperature[a] = 32 + (double) (8 * Math.random());
            String str = String.format("%.01f", temperature[a]);
            System.out.print("№" + (a + 1) + "-" + str + " ");
        }
        System.out.println("\nСредняя температура " + String.format("%.01f", averageTemp(temperature)));

        System.out.println("Количество здоровых пациентов: " + getHealtyPatientCount(temperature));
    }

    private static double averageTemp(double[] temperature) {
        double midTemperatute = 0;

        for (int a = 0; a < temperature.length; a++) {
            midTemperatute = midTemperatute + temperature[a];
        }
        midTemperatute = midTemperatute / temperature.length;
        return midTemperatute;
    }

    private static int getHealtyPatientCount(double[] temperature) {
        int healthyPatient = 0;
        for (int a = 0; a < temperature.length; a++) {
            if (temperature[a] >= 36.2 && temperature[a] <= 36.9) {
                healthyPatient++;
            }
        }
        return healthyPatient;
    }


}

