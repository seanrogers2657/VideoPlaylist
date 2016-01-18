package services;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

public class UrlConverter {
    private static final Logger log = LoggerFactory.getLogger(UrlConverter.class);

    public static String convert(String url) {
        // Convert the youtube url to the embedded url
        String videoId = "";
        log.info("Converting url");
        if (url.contains("youtube.com") && url.contains("watch?v=")) {
            log.info("Splitting: " + url);
            videoId = url.substring(url.indexOf('=') + 1);
            log.info("VideoId: " + videoId);
        } else {
            log.error("Video url does not contain youtube or watch?v=");
        }

        if (videoId.isEmpty()) {
            log.error("Video url convert failed");
        }
        return videoId;
//        return "http://youtube.com/embed/" + videoId + "?autoplay=1";
    }
}
