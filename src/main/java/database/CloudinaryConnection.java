package database;

import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

import java.io.IOException;
import java.util.Map;

public class CloudinaryConnection {

    Cloudinary cloudinary;

    public CloudinaryConnection() {
         cloudinary = new Cloudinary(ObjectUtils.asMap(
                "cloud_name", "datai",
                "api_key", "539476191584584",
                "api_secret", "AkGSXw8YRfypHtibLpoa6A8_oRk"));
    }

    public String upload(String file) {

        Map params = ObjectUtils.asMap(
                "folder", "city_now/voting_app/avatar",
                "overwrite", true,
                "use_filename", true,
                "resource_type", "image"
        );
        Map result = null;

        try {
            result = cloudinary.uploader().upload(file,params);
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (result == null) return null;
        return (String) result.get("url");
    }
}
