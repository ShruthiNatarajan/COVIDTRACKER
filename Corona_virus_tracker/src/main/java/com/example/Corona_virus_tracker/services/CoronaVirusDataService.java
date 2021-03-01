package com.example.Corona_virus_tracker.services;
import com.example.Corona_virus_tracker.models.LocationStats;
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
    public class CoronaVirusDataService {


    public static String corona_virus_url="https://raw.githubusercontent.com/CSSEGISandData/COVID-19/master/csse_covid_19_data/csse_covid_19_time_series/time_series_covid19_recovered_global.csv";
    private List<LocationStats>  allStats = new ArrayList<>();

    public List<LocationStats> getAllStats() {
        return allStats;
    }

    @PostConstruct
    @Scheduled(cron="* * * * * *")
    public void fetchVirusData() throws IOException, InterruptedException {

        List<LocationStats> newStats = new ArrayList<>();
        HttpClient client = HttpClient.newHttpClient();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(corona_virus_url))
                .build();
        HttpResponse<String> response =
                client.send(request, HttpResponse.BodyHandlers.ofString());

        StringReader csvBodyreader = new StringReader(response.body());
        Iterable<CSVRecord> records = CSVFormat.DEFAULT.withFirstRecordAsHeader().parse(csvBodyreader);
        for (CSVRecord record : records) {
           LocationStats locationstat = new LocationStats();
           locationstat.setState(record.get("Province/State"));
           locationstat.setCountry(record.get("Country/Region"));
           int latestCases = Integer.parseInt(record.get(record.size()-1));
           int prevDayCases = Integer.parseInt(record.get(record.size()-2));
            locationstat.setLatestNoOfCases(latestCases);
            locationstat.setIncreaseFromPrevDay(latestCases - prevDayCases);
           newStats.add(locationstat);
        }
        this.allStats = newStats;
    }
}
