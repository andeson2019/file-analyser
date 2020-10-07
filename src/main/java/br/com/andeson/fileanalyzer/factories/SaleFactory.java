package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;
import br.com.andeson.fileanalyzer.model.Sale;
import br.com.andeson.fileanalyzer.model.SaleItem;
import br.com.andeson.fileanalyzer.utils.ArraysUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SaleFactory implements IModelFactory {

    private static final Logger logger = LoggerFactory.getLogger(SaleFactory.class);

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
            try {
                list.add(buildItem(i));
            } catch (ConvertStringToArrayException e) {
                logger.error("[Sale Factory] {}", e.getMessage());
            }
        });
        return list;
    }

    private SaleItem buildItem(String lineItem) throws ConvertStringToArrayException {
        SaleItem saleItem = (SaleItem) new SaleItemFactory().create(lineItem);
        return saleItem;
    }
}
