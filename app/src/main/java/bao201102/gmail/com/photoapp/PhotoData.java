package bao201102.gmail.com.photoapp;

import android.content.Context;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

public class PhotoData {
    public static ArrayList<Photo> generatePhotoData(Context context) {
        ArrayList<Photo> photos = new ArrayList<>();
        try {
            InputStream inputStream = context.getAssets().open("photo_data.json");
            int size = inputStream.available();
            byte buffer[] = new byte[size];
            inputStream.read(buffer);
            inputStream.close();

            int id;
            String title, source, des;

            String json = new String(buffer, StandardCharsets.UTF_8);
            JSONArray jsonArray = new JSONArray(json);

            for (int i = 0; i < jsonArray.length(); i++) {
                JSONObject jsonObject = jsonArray.getJSONObject(i);
                id = jsonObject.getInt("id");
                title = jsonObject.getString("title");
                source = jsonObject.getString("source");
                des = jsonObject.getString("description");
                photos.add(new Photo(id, source, title, des));
            }

            return photos;
        } catch (IOException | JSONException e) {
            return null;
        }
    }

    public static Photo getPhotoFromId(int id, Context context) {
        ArrayList<Photo> phs = generatePhotoData(context);
        for (int i = 0; i < phs.size(); i++) {
            if (phs.get(i).getId() == id)
                return phs.get(i);
        }
        return null;
    }
}
