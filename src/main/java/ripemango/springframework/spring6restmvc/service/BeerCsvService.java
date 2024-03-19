package ripemango.springframework.spring6restmvc.service;

import org.springframework.stereotype.Service;
import ripemango.springframework.spring6restmvc.model.BeerCSVRecord;

import java.io.File;
import java.util.List;


public interface BeerCsvService {
    List<BeerCSVRecord> convertCSV(File csvFile);
}
