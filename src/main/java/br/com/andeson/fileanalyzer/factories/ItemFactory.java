package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;
import br.com.andeson.fileanalyzer.model.SaleItem;
import br.com.andeson.fileanalyzer.utils.ArraysUtil;

public class ItemFactory implements IModelFactory{

    @Override
    public BaseModel create(String data) throws ConvertStringToArrayException {
        var array = ArraysUtil.stringToArray(data, "ç");
        return new SaleItem(Long.parseLong(array[1]),
                array[2],
                Double.parseDouble(array[3]));
    }
}
