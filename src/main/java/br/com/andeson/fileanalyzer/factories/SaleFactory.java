package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;
import br.com.andeson.fileanalyzer.model.Sale;
import br.com.andeson.fileanalyzer.model.SaleItem;
import br.com.andeson.fileanalyzer.utils.ArraysUtil;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaleFactory implements IModelFactory {

    @Override
    public BaseModel create(String data) throws ConvertStringToArrayException {
        var array = ArraysUtil.stringToArray(data, "ç");
        var itemsString = array[2].replace("[", "")
                .replace("]", "");
        var itemsArray = processItems(itemsString.split(","));
        return new Sale(Long.parseLong(array[0]),
                Long.parseLong(array[1]),
                itemsArray,
                array[3]);
    }

    private List<SaleItem> processItems(String[] items) {
        List list = new ArrayList<>();
        Arrays.asList(items).forEach(i -> {
            var itemsArray = i.split("-");
            list.add(buildItem(itemsArray));
        });
        return list;
    }

    private SaleItem buildItem(String[] array) {
        var price = Double.parseDouble(array[2]);
        var item = new SaleItem(Long.parseLong(array[0]),
                array[1],
                price);
        return item;
    }
}
