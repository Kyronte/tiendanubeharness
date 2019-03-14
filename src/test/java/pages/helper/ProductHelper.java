package pages.helper;

import com.codeborne.selenide.SelenideElement;

public class ProductHelper {

    public static String convertCurrencyToString(String currency) {
        String s;
        s = currency.substring(1);
        return s.replace(",", ".");
    }

    public static boolean hasPublishProductBeenClicked(SelenideElement button) {
        if(button.getValue().equals("Publicar producto")) {
            button.click();
            return hasPublishProductBeenClicked(button);
        } else {
            return true;
        }
    }


}
