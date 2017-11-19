package mylibrary.pauldefois.imta.fr.mylibrary.service;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import mylibrary.pauldefois.imta.fr.mylibrary.R;
import mylibrary.pauldefois.imta.fr.mylibrary.book.Book;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import retrofit2.http.GET;

/**
 * Service pour récupérer les données JSON de la librairie
 *
 * @author Paul Defois
 */
public class HenriPotierService extends Observable {
    private Retrofit retrofit;
    private Context context;
    private List<Book> data;

    public HenriPotierService(Context context) {
        this.context = context;
        this.retrofit = new Retrofit.Builder()
                .baseUrl(context.getString(R.string.url_api))
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        this.data = new ArrayList<>();
    }

    /**
     * Charge les livres de la librairie. Si ça a déjà été fait, il les récupère. Pas bien dans le
     * cas d'une librairie dynamique ou des livres peuvent être ajoutés de l'extérieur. (Ajouter un bouton reload)
     * Mais pour l'exercice, ça évite de charger 30 fois les livres avec 30 appels réseau.
     */
    public void loadListBooks() {
        if (data.size() != 0) {
            setChanged();
            notifyObservers(data);
            return;
        }

        HenriPotierAPI api = retrofit.create(HenriPotierAPI.class);
        api.getListBooks().enqueue(new Callback<List<Book>>() {
            @Override
            public void onResponse(Call<List<Book>> call, Response<List<Book>> response) {
                setChanged();
                notifyObservers(response.body());
            }

            @Override
            public void onFailure(Call<List<Book>> call, Throwable t) {
                // Message toast pour simplifier, il se peut que l'erreur ne vienne pas de la connexion internet
                Toast.makeText(context, context.getString(R.string.error_message_api), Toast.LENGTH_LONG).show();
            }
        });
    }

    /**
     * Interface retrofit de récupération de données
     */
    private interface HenriPotierAPI {
        @GET("books")
        Call<List<Book>> getListBooks();
    }
}
