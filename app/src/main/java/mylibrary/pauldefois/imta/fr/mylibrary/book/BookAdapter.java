package mylibrary.pauldefois.imta.fr.mylibrary.book;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import mylibrary.pauldefois.imta.fr.mylibrary.R;

/**
 * Adapter d'un book pour la view liste
 *
 * @author Paul Defois
 */
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.BookViewHolder> {
    private List<Book> books;
    private LayoutInflater inflater;
    private OnClickBookItemListener listener;

    public BookAdapter(List<Book> books, LayoutInflater inflater, OnClickBookItemListener listener) {
        this.books = books;
        this.inflater = inflater;
        this.listener = listener;
    }

    @Override
    public void onBindViewHolder(BookViewHolder holder, int position) {
        ((BookItemView) holder.itemView).bindView(books.get(position), listener);
    }

    @Override
    public BookViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new BookViewHolder(inflater.inflate(R.layout.custom_view_item_book, parent, false));
    }

    @Override
    public int getItemCount() {
        return books.size();
    }

    class BookViewHolder extends RecyclerView.ViewHolder {
        public BookViewHolder(View itemView) {
            super(itemView);
        }
    }
}
