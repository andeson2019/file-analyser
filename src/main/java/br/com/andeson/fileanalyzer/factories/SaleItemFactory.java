package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;
import br.com.andeson.fileanalyzer.model.SaleItem;

public class SaleItemFactory implements IModelFactory{

    @Override
    public BaseModel create(String data) throws ConvertStringToArrayException {
        var array = data.split("-");
        return new SaleItem(Long.parseLong(array[0]),
               Integer.parseInt(array[1]),
                Double.parseDouble(array[2]));
    }
}
