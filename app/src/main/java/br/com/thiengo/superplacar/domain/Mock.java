package br.com.thiengo.superplacar.domain;

import java.util.ArrayList;
import java.util.List;


public class Mock {

    public static Time gerarTime( int posicao ){
        String[] nomes = {"Rio Claro", "São Caetano", "S. J. Campos", "Nacional-SP"};
        String[] imagens = {
            "http://www.superplacar.com.br/images/escudos/f1eab3ac03d333dc76278b2f7989bace-68.png",
            "http://www.superplacar.com.br/images/escudos/173fb38f10e9a24e7cc665e513575bf2-68.png",
            "http://www.superplacar.com.br/images/escudos/a4cd88615deb2decbe7515b74849bee9-68.png",
            "http://www.superplacar.com.br/images/escudos/42ecf680e39db12f2ba513263694d1bc-68.PNG"
        };
        int[] goals = {0, 2, 1, 0};

        Time time = new Time();
        time.setNome( nomes[ posicao ] );
        time.setImagemUrl( imagens[ posicao ] );
        time.setGoals( goals[ posicao ] );

        return time;
    }

    public static Jogo gerarJogo( int posicao ){
        String[] status = {"Em andamento", "Em breve", "Encerrado"};
        String[] inicios = {"16:55", "19:00", "20:00"};
        Jogo jogo = new Jogo();
        jogo.setTime1( gerarTime( posicao ) );
        jogo.setTime2( gerarTime( posicao + 1 ) );
        jogo.setStatus( status[posicao] );
        jogo.setInicio( inicios[posicao] );

        return jogo;
    }

    public static List<Jogo> gerarJogos(){
        List<Jogo> jogos = new ArrayList<>();
        jogos.add( gerarJogo(0) );
        jogos.add( gerarJogo(2) );

        return jogos;
    }
}
