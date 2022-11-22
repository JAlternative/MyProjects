import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;

public class CustomerStorage
{
    private HashMap<String, Customer> storage;

    public CustomerStorage()
    {
        storage = new HashMap<>();
    }

    public void addCustomer(String data) throws WrongFormatEmailExeption, WrongFormatTelephoneExeption{
        String[] components = data.split("\\s+");
        String name = components[0] + " " + components[1];
        if (validEmail(components[2]) == false) {
            throw new WrongFormatEmailExeption("Wrong format email. Correct format: vasily.petrov@gmail.com");
        }
        if (validTelephone(components[3]) == false){
            throw new WrongFormatTelephoneExeption("Wrong format telephone. Correct format: +79215637722");
        }
        storage.put(name, new Customer(name, components[3], components[2]));
    }
    public boolean validEmail(String email){
        if (email.indexOf("@") > 1 && email.indexOf(".") > 1) {
            return true;
        } else {
            return false;
        }
    }
    public boolean validTelephone(String telephone){
        if (telephone.indexOf("+") == 0 && telephone.indexOf("7") == 1 && telephone.indexOf("9") == 2 && telephone.length() == 12) {
            return true;
        } else {
            return false;
        }
    }

    public void listCustomers()
    {
        storage.values().forEach(System.out::println);
    }

    public void removeCustomer(String name)
    {
        storage.remove(name);
    }

    public int getCount()
    {
        return storage.size();
    }
}