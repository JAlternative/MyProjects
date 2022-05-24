package tests.search;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import pages.base.Waits;
import tests.base.BaseTest;

import static pages.constants.Constant.*;

public class SearchYandexMarketTest extends BaseTest {


    @ParameterizedTest
    @CsvSource(value = {
            "Компьютеры, Ноутбуки"})
    public void openSiteYandex(String sectionTitleLeft, String sectionTitleRight) {
        stepOpenCatalog.goPage(REALT_HOME_PAGE); //переходим на сайт яндекса
        stepOpenCatalog.enterProduct(sectionTitleLeft, sectionTitleRight); //передаём компьютер и ноутбуки
        Waits.fullPageWait();
        marketGoToSection.setPriceParameter(STARTING_PRICE, FINAL_PRICE); //цифры тоже в константу
        stepOpenCatalog.enterNoteBooks(MODEL, PAGE_ELEMENTS_TWELVE);
    }


}

