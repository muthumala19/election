package com.example.election.data_fetcher;

import com.netflix.graphql.dgs.DgsComponent;
import com.netflix.graphql.dgs.DgsQuery;

import java.util.List;
import java.util.logging.Logger;

@DgsComponent
public class ElectionDataFetcher {
    private static Logger logger = Logger.getLogger(ElectionDataFetcher.class.getName());

//    @DgsQuery
//    public List<Election> getAllElections() {
//        logger.info("ElectionDataFetcher.java:line-14: getElections() called");
//        return List.of(
//                new Election("1", "2020 US Presidential Election", "2020-11-03"),
//                new Election("2", "2021 UK General Election", "2021-05-06"),
//                new Election("3", "2021 German Federal Election", "2021-09-26")
//        );
//    }
}
