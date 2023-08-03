package com.frm.bot.backend.scripts;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Scripts {

    public boolean validarFormatoFecha(String fecha){

        String regexPattern = "^\\d{4}-\\d{2}-\\d{2}$";
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(fecha);
        boolean isValidFormat = matcher.matches();
        return isValidFormat;
    }
}
