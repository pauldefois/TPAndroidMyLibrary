package mylibrary.pauldefois.imta.fr.mylibrary.bookDetail;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import mylibrary.pauldefois.imta.fr.mylibrary.R;
import mylibrary.pauldefois.imta.fr.mylibrary.book.Book;

/**
 * Fragment pour le détail d'un livre
 *
 * @author Paul Defois
 */
public class DetailBookFragment extends Fragment {
    private ImageView coverImageViewDetail;
    private TextView descriptionTextVIewDetail;
    private TextView priceTextViewDetail;
    private Book book;
    private Context context;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;

        if (getView() != null) {
            refreshDisplay();
        }
    }

    private void refreshDisplay() {
        Glide.with(this)
                .load(book.getCover())
                .into(coverImageViewDetail);
        descriptionTextVIewDetail.setText(book.getSynopsis()[0]);
        priceTextViewDetail.setText(context.getString(R.string.pricePlaceHolder, book.getPrice()));
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.detail_book_fragment, container, false);
        coverImageViewDetail = view.findViewById(R.id.coverImageViewDetail);
        descriptionTextVIewDetail = view.findViewById(R.id.descriptionTextViewDetail);
        priceTextViewDetail = view.findViewById(R.id.priceTextViewDetail);

        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        if (book != null) {
            refreshDisplay();
        }
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }
}
