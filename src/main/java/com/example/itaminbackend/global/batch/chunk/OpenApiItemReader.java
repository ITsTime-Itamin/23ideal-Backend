package com.example.itaminbackend.global.batch.chunk;

import com.example.itaminbackend.global.batch.dto.PublicRentalHouseResponse;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.codehaus.jettison.json.JSONArray;
import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.beans.factory.annotation.Value;
import com.example.itaminbackend.domain.rentalhouse.constant.PublicRentalHouseConstants.ESignguCode;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class OpenApiItemReader implements ItemReader<List<PublicRentalHouseResponse>> {

    @Value("${open.api.public.publicRentalHouse.url}")
    private String openApiUrl;

    @Value("${open.api.public.data.servicekey}")
    private String openApiServiceKey;

    private final ESignguCode[] values;
    private int count = 0;

    public OpenApiItemReader() {
        values = ESignguCode.values();
    }

    @Override
    public List<PublicRentalHouseResponse> read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {
        return requestOpenApi();
    }

    private List<PublicRentalHouseResponse> requestOpenApi() throws IOException, JSONException {

        if (count == 25) return null;
        String signguCode = values[count].getCode();
        count++;
        StringBuilder urlBuilder = new StringBuilder(openApiUrl); /*URL*/
        urlBuilder.append("?" + URLEncoder.encode("serviceKey","UTF-8") + "="+openApiServiceKey); /*Service Key*/
        urlBuilder.append("&" + URLEncoder.encode("brtcCode","UTF-8") + "=" + URLEncoder.encode("11", "UTF-8")); /*광역시도 코드*/
        urlBuilder.append("&" + URLEncoder.encode("signguCode","UTF-8") + "=" + URLEncoder.encode(signguCode, "UTF-8")); /*시군구 코드*/
        urlBuilder.append("&" + URLEncoder.encode("numOfRows","UTF-8") + "=" + URLEncoder.encode("5000", "UTF-8")); /*조회될 목록의 페이지당 데이터 개수 (기본값:10)*/
        urlBuilder.append("&" + URLEncoder.encode("pageNo","UTF-8") + "=" + URLEncoder.encode("1", "UTF-8")); /*조회될 페이지의 번호 (기본값:1)*/
        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode());
        BufferedReader rd;
        if(conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        String jsonString = sb.toString();
        ObjectMapper objectMapper = new ObjectMapper();
        JSONObject jsonObject = new JSONObject(jsonString);
        JSONArray hsmpList = (JSONArray)jsonObject.get("hsmpList");
        List<PublicRentalHouseResponse> publicRentalHouseResponseList = new ArrayList<>();
        for (int i=0; i<hsmpList.length(); i++) {
            hsmpList.get(i);
            JSONObject list = (JSONObject)hsmpList.get(i);
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            publicRentalHouseResponseList.add(objectMapper.readValue(list.toString(), PublicRentalHouseResponse.class));
        }
        rd.close();
        conn.disconnect();
        return publicRentalHouseResponseList;
    }
}
