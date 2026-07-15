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

    protected List<HashMap<String, String>> getMergedJsonData(String... filePaths) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        List<HashMap<String, String>> mergedList = new ArrayList<>();

        // Read first file as the base list
        List<HashMap<String, String>> baseList = mapper.readValue(
            new File(filePaths[0]),
            mapper.getTypeFactory().constructCollectionType(List.class, HashMap.class)
        );

        // For each base entry, merge data from the remaining files by index
        for (int i = 0; i < baseList.size(); i++) {
            HashMap<String, String> merged = new HashMap<>(baseList.get(i));

            for (int j = 1; j < filePaths.length; j++) {
                List<HashMap<String, String>> otherList = mapper.readValue(
                    new File(filePaths[j]),
                    mapper.getTypeFactory().constructCollectionType(List.class, HashMap.class)
                );
                if (i < otherList.size()) {
                    merged.putAll(otherList.get(i)); // merge by row index
                }
            }

            mergedList.add(merged);
        }

        return mergedList;
    }
}