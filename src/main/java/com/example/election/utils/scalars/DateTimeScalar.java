package com.example.election.utils.scalars;

import com.netflix.graphql.dgs.DgsScalar;
import graphql.language.StringValue;
import graphql.language.Value;
import graphql.schema.Coercing;
import graphql.schema.CoercingParseLiteralException;
import graphql.schema.CoercingParseValueException;
import graphql.schema.CoercingSerializeException;
import org.jetbrains.annotations.NotNull;

import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

@DgsScalar(name = "DateTime")
public class DateTimeScalar implements Coercing<ZonedDateTime, String> {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ISO_ZONED_DATE_TIME;

    @Override
    public String serialize(@NotNull Object dataFetcherResult) throws CoercingSerializeException {
        if (dataFetcherResult instanceof ZonedDateTime) {
            return ((ZonedDateTime) dataFetcherResult).format(FORMATTER);
        } else {
            throw new CoercingSerializeException("Not a valid ZonedDateTime");
        }
    }

    @Override
    public ZonedDateTime parseValue(Object input) throws CoercingParseValueException {
        try {
            return ZonedDateTime.parse(input.toString(), FORMATTER);
        } catch (Exception e) {
            throw new CoercingParseValueException("Invalid input for ZonedDateTime");
        }
    }

    @Override
    public ZonedDateTime parseLiteral(@NotNull Object input) throws CoercingParseLiteralException {
        if (input instanceof StringValue) {
            try {
                return ZonedDateTime.parse(((StringValue) input).getValue(), FORMATTER);
            } catch (Exception e) {
                throw new CoercingParseLiteralException("Value is not a valid ISO ZonedDateTime");
            }
        }
        throw new CoercingParseLiteralException("Expected a StringValue for ZonedDateTime");
    }

    @Override
    public @NotNull Value valueToLiteral(@NotNull Object input) {
        return new StringValue(this.serialize(input));
    }
}
