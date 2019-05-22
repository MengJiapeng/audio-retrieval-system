package mjp.mp3searchengine.entity;

import org.apache.solr.client.solrj.beans.Field;

import java.time.Instant;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author BadCode
 * @date 2019-03-27 11:28
 **/
public class AudioDocument {

    @Field
    private String id;
    // 时长（秒）
    @Field
    private int trackLength;
    // 标题
    @Field
    private String title;
    // 副标题
    @Field
    private String subtitle;
    // 歌手名字
    @Field
    private List<String> artist;
    // 专辑名称
    @Field
    private String album;
    // 歌曲风格
    @Field
    private String genre;
    // 歌曲注释
    @Field
    private String comment;
    // 歌词
    @Field
    private String lyric;
    // 作曲
    @Field
    private String composer;
    // 作词
    @Field
    private String lyricist;
    // 年代
    @Field
    private int year;
    // 在专辑中的排名
    @Field
    private int track;
    // 文件格式
    @Field
    private String format;
    @Field
    private String url;
    @Field
    private String originName;
    @Field
    private Date uploadTime;

    private static List<String> searchFields;
    private static final Object LOCK = new Object();

    public static List<String> getSearchFields() {
        if (searchFields == null) {
            synchronized (LOCK) {
                if (searchFields == null) {
                    searchFields = new ArrayList<>();
                    searchFields.add("title");
                    searchFields.add("album");
                    searchFields.add("artist");
                    searchFields.add("gene");
                    searchFields.add("lyric");
                    searchFields.add("composer");
                    searchFields.add("lyricist");
                    searchFields.add("format");
                }
            }
        }
        return searchFields;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public int getTrackLength() {
        return trackLength;
    }

    public void setTrackLength(int trackLength) {
        this.trackLength = trackLength;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public List<String> getArtist() {
        return artist;
    }

    public void setArtist(List<String> artist) {
        this.artist = artist;
    }

    public String getAlbum() {
        return album;
    }

    public void setAlbum(String album) {
        this.album = album;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public String getLyric() {
        return lyric;
    }

    public void setLyric(String lyric) {
        this.lyric = lyric;
    }

    public String getComposer() {
        return composer;
    }

    public void setComposer(String composer) {
        this.composer = composer;
    }

    public String getLyricist() {
        return lyricist;
    }

    public void setLyricist(String lyricist) {
        this.lyricist = lyricist;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public int getTrack() {
        return track;
    }

    public void setTrack(int track) {
        this.track = track;
    }

    public String getFormat() {
        return format;
    }

    public void setFormat(String format) {
        this.format = format;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getOriginName() {
        return originName;
    }

    public void setOriginName(String originName) {
        this.originName = originName;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }
}
