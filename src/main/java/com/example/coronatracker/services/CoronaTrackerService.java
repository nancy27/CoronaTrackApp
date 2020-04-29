package com.example.coronatracker.services;

import com.example.coronatracker.domain.LocationStats;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVRecord;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.io.StringReader;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;

@Service
public class CoronaTrackerService {
    private String pathUri="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_confirmed_US.csv";
    List<LocationStats> locationStatsList= new ArrayList<LocationStats>();
    @PostConstruct
    @Scheduled(cron = "* * 1 * * *")
    public void FetchCoronaDetails() throws IOException, InterruptedException {
        HttpClient client=HttpClient.newHttpClient();
        HttpRequest request=HttpRequest.newBuilder().uri(URI.create(pathUri)).build();
        HttpResponse<String> response=client.send(request, HttpResponse.BodyHandlers.ofString());
        StringReader csvReader=new StringReader(response.body());

        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvReader);
        List<LocationStats> newStats= new ArrayList<>();
        for (CSVRecord record : records) {
            LocationStats locationStats = new LocationStats();
            locationStats.setState(record.get("Province_State"));
            locationStats.setCountry(record.get("Country_Region"));
            locationStats.setRegion(record.get("Admin2"));
            int totalCases=Integer.parseInt(record.get(record.size() - 1));
            locationStats.setTotalNoCases(totalCases);
            int prevDay=Integer.parseInt(record.get(record.size() - 2));
            locationStats.setDiffFromPrevDay(totalCases-prevDay);
            newStats.add(locationStats);
            System.out.println(locationStats);
        }
        this.locationStatsList=newStats;
    }

    public List<LocationStats> getLocationStatsList() {
        return locationStatsList;
    }
}
