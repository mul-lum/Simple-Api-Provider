package net.mullum.RJServlet.api.services;

import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.stereotype.Service;

import net.mullum.RJServlet.api.model.Data;

@Service
public class DataService {
    public String getData(int dataID) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException {
        // do something here to GET data

        return String.valueOf(dataID); // change this
    }

    public Data addData(Data data) throws InvalidKeyException, IllegalBlockSizeException, BadPaddingException, UnsupportedEncodingException {
        // do something here to POST data

        return data;
    }
}
