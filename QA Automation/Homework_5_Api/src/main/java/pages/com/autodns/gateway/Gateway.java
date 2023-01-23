package pages.com.autodns.gateway;

import io.restassured.path.xml.element.Node;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * https://gateway.autodns.com/
 *
 * @author Сергей Хорошков
 */
public class Gateway {
    private ArrayList<Node> saveNodes = new ArrayList<>();

    /**
     * Сохранить в переменную всю иерархию узлов и вернуть их названия
     *
     * @param node первый узел со страницы
     * @return метод возвращает названия всех узлов со страницы
     * @see Gateway#getAllChildrenNodes(Node)
     */
    public List<String> getAllTagsNameOnPage(Node node) {
        getAllChildrenNodes(node);
        return saveNodes.stream().map(n -> n.name()).collect(Collectors.toList());
    }

    /**
     * Метод сохраняет всех потомков node в saveNodes
     *
     * @param node узел со страницы
     */
    private void getAllChildrenNodes(Node node) {
        saveNodes.add(node);
        if (!node.children().isEmpty()) {
            node.children().list().forEach(n -> {
                getAllChildrenNodes(n);
            });
        }
    }

}
