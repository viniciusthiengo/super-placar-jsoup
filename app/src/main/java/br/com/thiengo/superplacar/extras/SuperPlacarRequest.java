package br.com.thiengo.superplacar.extras;

import android.os.AsyncTask;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import java.io.IOException;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.List;

import br.com.thiengo.superplacar.MainActivity;
import br.com.thiengo.superplacar.domain.Goal;
import br.com.thiengo.superplacar.domain.Jogo;
import br.com.thiengo.superplacar.domain.Time;


public class SuperPlacarRequest extends AsyncTask<Void, Void, List<Jogo>> {
    private WeakReference<MainActivity> activity;

    public SuperPlacarRequest( MainActivity activity ){
        this.activity = new WeakReference<>( activity );
    }

    @Override
    protected List<Jogo> doInBackground(Void... voids) {

        Document html = null;
        List<Jogo> jogos = new ArrayList<>();

        try {
            html = Jsoup.connect("http://www.superplacar.com.br/").get();
            Elements time = html.select("div.time-status span.time");
            Elements status = html.select("div.time-status span.status");
            Elements times1 = html.select("div.team1");
            Elements times2 = html.select("div.team2");

            for( int i = 0; i < time.size(); i++ ){

                Time time1 = getTime( times1.get(i), true );
                Time time2 = getTime( times2.get(i), false );
                Jogo jogo = new Jogo();

                jogo.setInicio( time.get(i).text() );
                jogo.setStatus( status.get(i).text() );
                jogo.setTime1( time1 );
                jogo.setTime2( time2 );
                jogos.add( jogo );
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return jogos;
    }

    private Time getTime( Element timeTag, boolean casa ){
        int indice = casa ? 1 : 2;
        Time time = new Time();
        time.setNome( timeTag.select("span.team"+indice+"-name").text() );
        time.setImagemUrl( timeTag.select("img").attr("src") );

        String goalsString = timeTag.select("span.team"+indice+"-score").text();
        int goals = goalsString.isEmpty() ? 0 : Integer.parseInt( goalsString );
        time.setGoals( goals );

        time.getGoalsLista().addAll( getGoalsLista( timeTag ) );
        return time;
    }

    private List<Goal> getGoalsLista( Element timeTag ){
        Elements goalsLista = timeTag.select("ul.goal-players li");
        List<Goal> goals = new ArrayList<>();

        for( int j = 0; j < goalsLista.size(); j++ ){
            Goal goal = new Goal();
            goal.setNome( goalsLista.get(j).select(".name").text() );
            goal.setTime( goalsLista.get(j).select(".time").text() );
            goals.add( goal );
        }
        return goals;
    }

    @Override
    protected void onPostExecute(List<Jogo> jogos) {
        super.onPostExecute( jogos );

        if( activity.get() != null ){
            activity.get().updateLista( jogos );
        }
    }
}
