package com.ws.hugs.data.remote;


import java.util.List;

public class BookChapterDto {

    private int bookId;
    private int chapterId;
    private int chapterIndex;
    private String chapterName;


    private List<ChapterText> chapterText;


    public int getChapterIndex() {
        return chapterIndex;
    }

    public void setChapterIndex(int chapterIndex) {
        this.chapterIndex = chapterIndex;
    }

    public String getChapterName() {
        return chapterName;
    }

    public void setChapterName(String chapterName) {
        this.chapterName = chapterName;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getChapterId() {
        return chapterId;
    }

    public void setChapterId(int chapterId) {
        this.chapterId = chapterId;
    }

    public List<ChapterText> getChapterText() {
        return chapterText;
    }

    public void setChapterText(List<ChapterText> chapterText) {
        this.chapterText = chapterText;
    }

}
