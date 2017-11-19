package mylibrary.pauldefois.imta.fr.mylibrary.book;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import mylibrary.pauldefois.imta.fr.mylibrary.LibraryActivity;
import mylibrary.pauldefois.imta.fr.mylibrary.R;
import mylibrary.pauldefois.imta.fr.mylibrary.service.HenriPotierService;

/**
 * Fragement pour la liste de livre
 *
 * @author Paul Defois
 */
public class ListBooksFragment extends Fragment implements Observer {
    private RecyclerView recyclerView;
    private Context context;
    private OnClickBookItemListener listener;

    @Override
    public void update(Observable o, Object arg) {
        if (!(arg instanceof List)) {
            throw new RuntimeException("Erreur : " + o.getClass() + " n'envoie pas la bonne donnée");
        }

        List<Book> response = (List<Book>) arg;

        feedViewWithBook(response);
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        this.context = context;

        if (context instanceof OnClickBookItemListener) {
            listener = (OnClickBookItemListener) context;
        } else {
            throw new RuntimeException("Mauvais listener");
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.liste_books_fragment, container, false);
        recyclerView = view.findViewById(R.id.bookListView);
        recyclerView.setLayoutManager(new LinearLayoutManager(context));

        ((LibraryActivity) context).getHenriPotierService().loadListBooks();

        return view;
    }

    private void feedViewWithBook(List<Book> livres) {
        recyclerView.setAdapter(
                new BookAdapter(
                        livres,
                        LayoutInflater.from(context),
                        listener
                )
        );
    }
}
