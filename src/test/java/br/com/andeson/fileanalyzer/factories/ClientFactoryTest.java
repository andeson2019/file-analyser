package br.com.andeson.fileanalyzer.factories;

import br.com.andeson.fileanalyzer.exceptions.ConvertStringToArrayException;
import br.com.andeson.fileanalyzer.model.Client;
import static org.junit.Assert.assertEquals;
import org.junit.Test;

public class ClientFactoryTest {
    @Test
    public void clientShouldBeCreatedByLineTextFile() throws ConvertStringToArrayException {
        var client = new Client("2345675433444345","Eduardo Pereira","Rural");
        var lineClient = "002ç2345675433444345çEduardo PereiraçRural";

        var clientFactory = new ClientFactory();
        var newClient = (Client) clientFactory.create(lineClient);
        assertEquals(client, newClient);

    }
}
