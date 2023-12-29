package com.juanma32.MiNotaEscolar.auth.filter;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SimpleGrantedAuthorityJsonCreator {
    @JsonCreator
    public SimpleGrantedAuthorityJsonCreator(@JsonProperty("authority")String role) {
    }
}
