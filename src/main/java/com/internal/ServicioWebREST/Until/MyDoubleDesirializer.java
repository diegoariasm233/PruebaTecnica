package com.internal.ServicioWebREST.Until;

import java.io.IOException;
import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.text.DecimalFormatSymbols;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class MyDoubleDesirializer extends JsonSerializer<Double> {


    @Override
    public void serialize(Double value, JsonGenerator gen, SerializerProvider serializers)
            throws IOException, JsonProcessingException {
        BigDecimal d = new BigDecimal(value);
        DecimalFormatSymbols separadoresPersonalizados = new DecimalFormatSymbols();
        separadoresPersonalizados.setDecimalSeparator('.');
        DecimalFormat formato = new DecimalFormat("#.######", separadoresPersonalizados);
        gen.writeNumber(formato.format(d));
    }


}