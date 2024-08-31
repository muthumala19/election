package com.example.election.scalars;


import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@DgsScalar(name = "DateTime")
public class DateTimeScalar implements Coercing<LocalDateTime, String> {
    private final DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;

    @Override
    public String serialize(Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof LocalDateTime) {
            return ((LocalDateTime) dataFetcherResult).format(formatter);
        } else {
            throw new CoercingSerializeException("Expected a LocalDateTime object.");
        }
    }

    @Override
    public LocalDateTime parseValue(Object input) throws CoercingParseValueException {
        try {
            return LocalDateTime.parse(input.toString(), formatter);
        } catch (Exception e) {
            throw new CoercingParseValueException("Invalid DateTime format.");
        }
    }

    @Override
    public LocalDateTime parseLiteral(Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            try {
                return LocalDateTime.parse(((StringValue) input).getValue(), formatter);
            } catch (Exception e) {
                throw new CoercingParseLiteralException("Invalid DateTime format.");
            }
        }
        throw new CoercingParseLiteralException("Expected a StringValue object.");
    }
}

