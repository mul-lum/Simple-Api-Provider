package net.mullum.RJServlet.api.controller;


import java.io.UnsupportedEncodingException;
import java.security.InvalidKeyException;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.mullum.RJServlet.api.model.Data;
import net.mullum.RJServlet.api.services.DataService;

@RestController
@RequestMapping("/")
public class DataController {
    private final DataService DATASERVICE;

    @Autowired
    DataController(DataService dataService) {
        this.DATASERVICE = dataService;
    }

    /**
     * get Request to URL/api/{id}
     * @param ID
     * @return ResponseEntity
     */
    @GetMapping("api/{id}")
    public ResponseEntity<String> getData(@PathVariable("id") int dataID) {
        try {
            return ResponseEntity.ok(DATASERVICE.getData(dataID));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException e) {
            return ResponseEntity.badRequest().body("Could not retrieve data" + e.getMessage());
        }
    }

    /**
     * Post Request to URL/api/add
     * @param data
     * @return ResponseEntity
     */
    @PostMapping("api/add")
    public ResponseEntity<String> setData(@RequestBody String data) {
        try {
            Data obj = new Data(data);
            DATASERVICE.addData(obj);

            return ResponseEntity.ok("Sucessfully set data");
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | UnsupportedEncodingException e) {
            return ResponseEntity.badRequest().body("Could not retrieve data");
        }
    }
}
