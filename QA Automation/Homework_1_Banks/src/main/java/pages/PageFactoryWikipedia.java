package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Page Factory яндекса
 *
 * @author Сергей Хорошков
 */
public class PageFactoryWikipedia {

    /**
     * Конструктор по умолчанию
     */
    public PageFactoryWikipedia() {

    }
    @FindBy(xpath = "//table")
    private WebElement webElement;

    /**
     * Метод для взаимодействия со значениями таблицы
     * @param tableName название таблицы, с какой будем собирать данные
     * @return собирает данные с таблицы в Map (ключи - заголовки, значения - данные ниже первой строчки) и кладёт их в список
     */
    public List<Map<String, String>> getTableValue(String tableName) {
        String selectorLinesTable =  ".//caption[contains(text(), '" + tableName + "')]//..//tr[position()>1]";
        String selectorColumnsTAble = ".//caption[contains(text(), '" + tableName + "')]//..//th";
        List<WebElement> linesTable = webElement.findElements(By.xpath(selectorLinesTable));
        List<WebElement> columnsTable = webElement.findElements(By.xpath(selectorColumnsTAble));
        List<Map<String, String>> listValueTables = new ArrayList<>();
        for (int l = 0; l < linesTable.size(); l++) {
            Map<String, String> valueTables = new LinkedHashMap<>();
            for (int c = 0; c < columnsTable.size(); c++) {
                String selector = ".//td[" + (c + 1) + "]";
                valueTables.put(
                        columnsTable.get(c).getText(),
                        linesTable.get(l).findElement(By.xpath(selector)).getText()
                );
            }
            listValueTables.add(valueTables);
        }
        return listValueTables;
    }
}
