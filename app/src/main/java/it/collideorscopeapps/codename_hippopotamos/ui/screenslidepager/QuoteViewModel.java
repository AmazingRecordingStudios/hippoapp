package it.collideorscopeapps.codename_hippopotamos.ui.screenslidepager;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import java.util.TreeMap;

import it.collideorscopeapps.codename_hippopotamos.database.DBManager;
import it.collideorscopeapps.codename_hippopotamos.database.QuotesProvider;
import it.collideorscopeapps.codename_hippopotamos.model.Playlist;
import it.collideorscopeapps.codename_hippopotamos.model.PlaylistIterator;
import it.collideorscopeapps.codename_hippopotamos.model.Schermata;

public class QuoteViewModel extends AndroidViewModel {
    // WIP: Implement the ViewModel

    TreeMap<Integer, Schermata> schermateById;
    TreeMap<Integer, Playlist> playlists;
    PlaylistIterator plItr;
    QuotesProvider quotesProvider;

    public QuoteViewModel(Application application) {
        super(application);

        this.quotesProvider = new QuotesProvider();
        this.quotesProvider.create(application);

        init();
    }

    private TreeMap<Integer, Schermata> getSchermateById() {

        return this.schermateById;
    }

    private void init() {

        //TODO FIXME retrieve language settings from shared preferences
        this.schermateById = quotesProvider.getSchermateById(QuotesProvider.Languages.EN);
        this.playlists = quotesProvider.getPlaylists();
        //TODO
        // keep current schermata in playlist
        this.plItr = new PlaylistIterator(this.schermateById, this.playlists);
    }

    public int getScreenCount() {

        return this.plItr.screensCount();
    }

    public Schermata getScreenAt(int position) {
        return this.plItr.getScreenAt(position);
    }

    @Override
    protected void onCleared() {
        super.onCleared();

        this.quotesProvider.close();
    }

    /*public Schermata getScreen(int screenId) {
        return this.getSchermateById().get(screenId);
    }

    private void loadUsers() {
        // Do an asynchronous operation to fetch users.
    }*/
}
