package br.com.thiengo.superplacar;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;
import br.com.thiengo.superplacar.domain.Jogo;
import br.com.thiengo.superplacar.extras.SuperPlacarRequest;
import br.com.thiengo.superplacar.extras.Worker;


public class MainActivity extends AppCompatActivity {

    private JogosAdapter adapter;
    private ArrayList<Jogo> jogos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if( savedInstanceState != null ){
            jogos = savedInstanceState.getParcelableArrayList(Jogo.JOGOS_KEY);
            intiViews();
            retrieveJogosStream();
        }
        else{
            jogos = new ArrayList<>();
            intiViews();
            retrieveJogos();
        }
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        outState.putParcelableArrayList(Jogo.JOGOS_KEY, jogos);
        super.onSaveInstanceState(outState);
    }

    private void intiViews() {
        super.onStart();

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_jogos);
        recyclerView.setHasFixedSize(true);

        LinearLayoutManager mLayoutManager = new LinearLayoutManager( this );
        recyclerView.setLayoutManager(mLayoutManager);

        DividerItemDecoration divider = new DividerItemDecoration(
            this,
            mLayoutManager.getOrientation() );
        recyclerView.addItemDecoration( divider );

        adapter = new JogosAdapter( this, jogos );
        recyclerView.setAdapter( adapter );
    }

    private void retrieveJogos(){
        new SuperPlacarRequest(this).execute();
        retrieveJogosStream();
    }

    private void retrieveJogosStream(){
        new Worker(this).start();
    }

    public void updateLista( List<Jogo> j ){
        jogos.clear();
        jogos.addAll( j );
        adapter.notifyDataSetChanged();
    }
}
