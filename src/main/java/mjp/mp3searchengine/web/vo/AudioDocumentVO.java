package mjp.mp3searchengine.web.vo;

import mjp.mp3searchengine.entity.AudioDocument;

import java.util.List;

/**
 * @author BadCode
 * @date 2019-04-13 21:01
 **/
public class AudioDocumentVO {

    private String id;
    private String title;
    private String artist;
    private String album;
    private String trackLength;
    private String url;
    private String format;
    private int track;
    private String originName;
    private String lyricist;
    private String composer;

    public AudioDocumentVO() {}

    public AudioDocumentVO(AudioDocument document) {
        this.title = document.getTitle();
        this.artist = combineArtist(document.getArtist());
        this.album = document.getAlbum();
        this.trackLength = transferTrackLength(document.getTrackLength());
        this.url = document.getUrl();
        this.format = document.getFormat();
        this.track = document.getTrack();
        this.id = document.getId();
        this.originName = document.getOriginName();
        this.composer = document.getComposer();
        this.lyricist = document.getLyricist();
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(String trackLength) {
        this.trackLength = trackLength;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    private String combineArtist(List<String> artists) {
        if (artists == null || artists.size() == 0) {
            return "";
        }
        StringBuilder sb = new StringBuilder();
        for (String artist : artists) {
            sb.append(artist).append("/");
        }
        String result = sb.toString();
        if (result.endsWith("/")) {
            result = result.substring(0, result.length() - 1);
        }
        return result;
    }

    private String transferTrackLength(int trackLength) {
        int minute = trackLength / 60;
        int second = trackLength % 60;
        return String.format("%1$02d:%2$02d", minute, second);
    }
}
