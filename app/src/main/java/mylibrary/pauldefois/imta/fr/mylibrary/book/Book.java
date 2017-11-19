package mylibrary.pauldefois.imta.fr.mylibrary.book;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Objet métier d'un livre
 *
 * @author Paul Defois
 */
public class Book implements Parcelable {
    private String title;
    private String price;
    private String cover;
    private String[] synopsis;

    public Book(String title, String price, String cover, String[] synopsis) {
        this.title = title;
        this.price = price;
        this.cover = cover;
        this.synopsis = synopsis;
    }

    protected Book(Parcel in) {
        title = in.readString();
        price = in.readString();
        cover = in.readString();
        in.readStringArray(synopsis);
    }

    public static final Creator<Book> CREATOR = new Creator<Book>() {
        @Override
        public Book createFromParcel(Parcel in) {
            return new Book(in);
        }

        @Override
        public Book[] newArray(int size) {
            return new Book[size];
        }
    };

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String[] getSynopsis() {
        return synopsis;
    }

    public void setSynopsis(String[] synopsis) {
        this.synopsis = synopsis;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (title != null ? !title.equals(book.title) : book.title != null) return false;
        return price != null ? price.equals(book.price) : book.price == null;
    }

    @Override
    public int hashCode() {
        int result = title != null ? title.hashCode() : 0;
        result = 31 * result + (price != null ? price.hashCode() : 0);
        return result;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(title);
        dest.writeString(price);
        dest.writeString(cover);
        dest.writeStringArray(synopsis);
    }
}
