package utils.parsing.concrete;

import utils.parsing.Main.Parser;

/**
 * Class for parsing Acts file
 **/
public class ActsParser extends Parser {
    public ActsParser(){
        setDelimiter("\\f");
    }

    /**
     * Finds contract and district numbers on input page by "/" symbol placement.
     * @return true if district and number were found
     * */
    @Override
    protected boolean pageContainsContractAndDistrict(String page){
        int contractNumberEnds;
        int slashIndex = -1;
        //записываем индекс слеша в строке
        slashIndex = page.indexOf("/");
        if (slashIndex == -1) return false; //если слеш отсутствует
        else {
            try {
                String slashSubstring = page.substring(slashIndex, page.length());
                if (slashSubstring.contains(" "))  //если в подстроке после слеша присутствует пробел
                    contractNumberEnds = slashSubstring.indexOf(" "); //записываем его индекс
                else
                    contractNumberEnds = slashSubstring.length();
                //записываем номер договора
                slashSubstring = slashSubstring.substring(1, contractNumberEnds).replaceAll("[^x0-9]", ""); //от слеша до пробела
                //если строка не содержала цифр, возвращаем false
                if (slashSubstring.isEmpty()) return false;
                //записывает номер договора
                setContract(Integer.parseInt(slashSubstring));

                slashSubstring = page.substring(slashIndex - 2, slashIndex).replaceAll("[^x0-9]", "");
                //записываем номер района
                setDistrict(Integer.parseInt(slashSubstring));
                return (!slashSubstring.isEmpty());
            } catch (StringIndexOutOfBoundsException s) {
                return false;
            }
        }
    }
}
