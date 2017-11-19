package mylibrary.pauldefois.imta.fr.mylibrary;

import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import mylibrary.pauldefois.imta.fr.mylibrary.book.Book;
import mylibrary.pauldefois.imta.fr.mylibrary.book.ListBooksFragment;
import mylibrary.pauldefois.imta.fr.mylibrary.book.OnClickBookItemListener;
import mylibrary.pauldefois.imta.fr.mylibrary.bookDetail.DetailBookFragment;
import mylibrary.pauldefois.imta.fr.mylibrary.service.HenriPotierService;

/**
 * Activité principale de l'application
 *
 * @author Paul Defois
 */
public class LibraryActivity extends AppCompatActivity implements OnClickBookItemListener {
    private HenriPotierService henriPotierService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        ListBooksFragment listBooksFragment = new ListBooksFragment();
        DetailBookFragment detailBookFragment = new DetailBookFragment();

        henriPotierService = new HenriPotierService(this);
        henriPotierService.addObserver(listBooksFragment);

//        boolean isLandscape = getResources().getBoolean(R.bool.landscape);
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (isLandscape) {
            setContentView(R.layout.activity_library_landscape);
            detailBookFragment.setBook(new Book("", "", "", new String[]{""}));
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerListBookLandscape, listBooksFragment, ListBooksFragment.class.getSimpleName())
                    .commit();
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerDetailBookLandscape, detailBookFragment, DetailBookFragment.class.getSimpleName())
                    .commit();
        } else {
            setContentView(R.layout.activity_library);
            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMainActivity, listBooksFragment, ListBooksFragment.class.getSimpleName())
                    .commit();
        }
    }

    @Override
    public void onClickBook(Book book) {
        boolean isLandscape = getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE;

        if (isLandscape) {
            DetailBookFragment detailBookFragment = (DetailBookFragment) getSupportFragmentManager().findFragmentById(R.id.containerDetailBookLandscape);
            detailBookFragment.setBook(book);
        } else {
            DetailBookFragment detailBookFragment = new DetailBookFragment();
            detailBookFragment.setBook(book);

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.containerMainActivity, detailBookFragment, DetailBookFragment.class.getSimpleName())
                    .addToBackStack(DetailBookFragment.class.getSimpleName())
                    .commit();
        }


//        Toast.makeText(this, book.getTitle(), Toast.LENGTH_SHORT).show();
    }

    public HenriPotierService getHenriPotierService() {
        return henriPotierService;
    }
}
