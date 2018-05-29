package utils.parsing.concrete;

import utils.parsing.Main.Parser;

import java.util.Scanner;

/**
 * Created by N.Babenkov on 11.05.2018.
 **/
public class VedParser extends Parser {
    public VedParser() {
        setKeyCharSequence("(небаланс, %)");
        setDelimiter("\\f");
    }

    /**
     * Finds contract and district numbers on input page by row number.
     *
     * @return true if district and number were found
     */
    @Override
    protected boolean pageContainsContractAndDistrict(String page) {
        if (!page.contains("В Е Д О М О С Т Ь   Э Л Е К Т Р О П О Т Р Е Б Л Е Н И Я")) {
            return false;
        }
        //если старый формат ведомости
        if (!(page.contains("ЦК,") || page.contains("Городское население,")
                || page.contains("Прир. к населению")
                || page.contains("Сельское население")
                || page.contains("К/П"))) {
            return super.pageContainsContractAndDistrict(page); //поиск по ключевому набору символов
        }

        int counter = 0;
        try (Scanner scanner = new Scanner(page)) {
            while (scanner.hasNext() && counter <= 11) {
                scanner.nextLine();
                counter++;
            }
            String line = scanner.nextLine(); //записываем 12-ю строку

            int index = line.indexOf("-");
            setDistrict(Integer.parseInt(line.substring(index - 2, index)));
            setContract(Integer.parseInt(line.substring(index + 1, line.indexOf(" ", index))));
            return true;
        } catch (StringIndexOutOfBoundsException s) {
            return false;
        }
    }
}
