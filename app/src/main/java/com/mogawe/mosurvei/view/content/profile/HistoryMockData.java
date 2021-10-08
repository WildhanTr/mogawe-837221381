package com.mogawe.mosurvei.view.content.profile;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.mogawe.mosurvei.model.network.response.HistoryResponse;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class HistoryMockData {
    private ArrayList<HistoryResponse> historyResponses;
    private HashMap<String, ArrayList<HistoryResponse>> hashMap;
    private String mockData =
            "[\n" +
            "  {\n" +
            "    \"amount\": 20000,\n" +
            "    \"timestamp\": \"2021-10-12\",\n" +
            "    \"description\": \"MO1922 - Teknisi EDC PT. Mahapay di Cibubur\",\n" +
            "    \"status\": \"Approved\",\n" +
            "    \"category\": \"incentive\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"amount\": 5000,\n" +
            "    \"timestamp\": \"2021-10-12\",\n" +
            "    \"description\": \"MO1922 - Teknisi EDC PT. Mahapay di Jatinegara\",\n" +
            "    \"status\": \"Completed\",\n" +
            "    \"category\": \"fee\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"amount\": -30000,\n" +
            "    \"timestamp\": \"2021-10-12\",\n" +
            "    \"description\": \"Bank Mandiri - Andini Septia\",\n" +
            "    \"status\": \"Transfer Out\",\n" +
            "    \"category\": \"price\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"amount\": 0,\n" +
            "    \"timestamp\": \"2021-10-12\",\n" +
            "    \"description\": \"MO1920 - Nobar EPL Week 6\",\n" +
            "    \"status\": \"Canceled\",\n" +
            "    \"category\": \"others\"\n" +
            "  },\n" +
            "  {\n" +
            "    \"amount\": 0,\n" +
            "    \"timestamp\": \"2021-10-11\",\n" +
            "    \"description\": \"MO1919 - Komando IDM Asem Baris\",\n" +
            "    \"status\": \"Revoked\",\n" +
            "    \"category\": \"incentive\"\n" +
            "  }\n" +
            "]";

    public HistoryMockData() {
        Gson gson = new Gson();
        historyResponses = gson.fromJson(mockData, new TypeToken<ArrayList<HistoryResponse>>(){}.getType());
        ArrayList<HistoryResponse> newHistoryResponses = new ArrayList<>();

        hashMap = new HashMap<>();
        for (HistoryResponse historyResponse: historyResponses) {
            if (!hashMap.containsKey(historyResponse.getTimestamp())) {
                System.out.println("HistoryMockData: date "+historyResponse.getTimestamp());
                historyResponse.setStartNewDate(true);
                hashMap.put(historyResponse.getTimestamp(), null);
            }

            newHistoryResponses.add(historyResponse);
        }

        historyResponses = newHistoryResponses;
    }

    public ArrayList<HistoryResponse> getMockData() {
        return historyResponses;
    }
}
