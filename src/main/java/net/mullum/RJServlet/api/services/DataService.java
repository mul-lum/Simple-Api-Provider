package net.mullum.RJServlet.api.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;
import java.util.Random;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.stereotype.Service;

import net.mullum.RJServlet.api.model.Data;
import net.mullum.RJServlet.utils.Config;

@Service
public class DataService {
    private static final Random random;
    private static final Config config;

    static {
        random = new Random();
        config = Config.getInstance();
    }

    public String getData(int dataID) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        String data = config.get(Integer.toString(dataID));
        //encryptor.decryptor(data);

        return data;
    }

    public Data addData(Data data) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        data.setID(random.nextInt(0, 999999));
        config.set(String.valueOf(data.getID()), data.getData());
        //encryptor.encrypt(data.getData());

        return data;
    }
}
