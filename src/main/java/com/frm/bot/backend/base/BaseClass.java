package com.frm.bot.backend.base;

import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import net.serenitybdd.core.Serenity;
import net.serenitybdd.rest.SerenityRest;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class BaseClass {

    protected String URL;
    protected String METODO;
    private RequestSpecification ReqManager = SerenityRest.given();
    public Response currentResponse;

    public void get(){currentResponse = ReqManager.get(URL).then().extract().response();}
    public void post()
    {
        currentResponse =  ReqManager.post(URL).then().extract().response();
    }
    public void put()
    {
        currentResponse =  ReqManager.put(URL).then().extract().response();
    }
    public void patch()
    {
        currentResponse =  ReqManager.patch(URL).then().extract().response();
    }
    public void delete()
    {
        currentResponse =  ReqManager.delete(URL).then().extract().response();
    }

    public void setURL(String url)
    {
        this.URL = url;
    }

    public void configureMethod(String Metodo)
    {
        this.METODO = Metodo;
    }

    public void sendRequest()
    {
        if (this.METODO.equals("GET"))
            get();
        else if (this.METODO.equals("POST"))
            post();
        else if (this.METODO.equals("PUT"))
            put();
        else if (this.METODO.equals("PATCH"))
            patch();
        else if (this.METODO.equals("DELETE"))
            delete();
    }

    public void setHeaders(String[] key, String[]values) {
        String report = "";
        for (int i = 0; i < key.length; i++)
        {
            ReqManager.header(key[i],values[i]);
            report += key[i]+": "+values[i]+"\n";
        }

    }
    public void addBody(String ruta) throws IOException {
        if(ruta!="") {
            String requestBody = new String(Files.readAllBytes(Paths.get(ruta)));
            ReqManager.body(requestBody);
        }else {
            ReqManager.body("");
        }
    }


}
