package utils;

import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

public abstract class AppiumUtils {

	public List<HashMap<String, String>> getJsonData(String jsonFilePath) throws IOException {

		// conver json file content to json string
		String jsonContent = FileUtils.readFileToString(new File(jsonFilePath), StandardCharsets.UTF_8);

		ObjectMapper mapper = new ObjectMapper();
		List<HashMap<String, String>> data = mapper.readValue(jsonContent,
				new TypeReference<List<HashMap<String, String>>>() {
				});

		return data;

	}
	
	
	// ==============================
    // 2. Read MULTIPLE JSON files
    // ==============================
    public List<HashMap<String, String>> getJsonDataFromFiles(String... jsonFilePaths) throws IOException {

        List<HashMap<String, String>> allData = new ArrayList<>();

        for (String path : jsonFilePaths) {
            allData.addAll(getJsonData(path));
        }

        return allData;
    }
    
 // ==============================
 // 3. Merge MULTIPLE JSON files into combined rows
 // ==============================
 public List<HashMap<String, String>> getMergedJsonData(String loginFilePath, String testDataFilePath) throws IOException {

     List<HashMap<String, String>> loginDataList = getJsonData(loginFilePath);
     List<HashMap<String, String>> testDataList  = getJsonData(testDataFilePath);

     // Use first row of testData as common data for all login rows
     HashMap<String, String> commonData = testDataList.get(0);

     List<HashMap<String, String>> mergedList = new ArrayList<>();

     for (HashMap<String, String> loginRow : loginDataList) {
         HashMap<String, String> merged = new HashMap<>();
         merged.putAll(commonData);   // test data first
         merged.putAll(loginRow);     // login data overrides if same key
         mergedList.add(merged);
     }

     return mergedList;
 }
}