package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.BaseModel;
import br.com.andeson.fileanalyzer.model.Client;
import br.com.andeson.fileanalyzer.utils.ArraysUtil;

public class ClientFactory implements IModelFactory{

    @Override
    public BaseModel create(String data) throws ConvertStringToArrayException {
        var array = ArraysUtil.stringToArray(data, "ç");
        return new Client(array[1], array[2], array[3]);
    }
}
