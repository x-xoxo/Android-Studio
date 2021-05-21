package io.example.mylibrary;


import android.os.Parcel;
import android.os.Parcelable;

public class BookDTO implements Parcelable {
    private int _No;
    private String title;
    private String author;
    private String publisher;
    private String summary;
    private int rental;

    protected BookDTO(Parcel in) {
        _No = in.readInt();
        title = in.readString();
        author = in.readString();
        publisher = in.readString();
        summary = in.readString();
        rental = in.readInt();
    }

    public BookDTO(int _No, String title, String author, String publisher, String summary, int rental) {
        this._No = _No;
        this.title = title;
        this.author = author;
        this.publisher = publisher;
        this.summary = summary;
        this.rental = rental;
    }

    public static final Creator<BookDTO> CREATOR = new Creator<BookDTO>() {
        @Override
        public BookDTO createFromParcel(Parcel in) {
            return new BookDTO(in);
        }

        @Override
        public BookDTO[] newArray(int size) {
            return new BookDTO[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeInt(_No);
        dest.writeString(title);
        dest.writeString(author);
        dest.writeString(publisher);
        dest.writeString(summary);
        dest.writeInt(rental);
    }

    public int get_No() {
        return _No;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getSummary() {
        return summary;
    }

    public int getRental() {
        return rental;
    }
}
